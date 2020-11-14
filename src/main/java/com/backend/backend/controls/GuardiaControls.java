package com.backend.backend.controls;

import java.util.HashMap;
import java.util.Map;

import com.backend.backend.repositorys.Guardia;
import com.backend.backend.repositorys.Notificaciones;
import com.backend.backend.services.GuardiaServises;
import com.backend.backend.services.NotificacionServises;
import com.backend.backend.services.UsersServises;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/guardia")
public class GuardiaControls {
    private Map<String, Object> atributes = new HashMap<>();
    private Boolean redirect = false;

    @Autowired
    private GuardiaServises servises;

    @Autowired
    private UsersServises uServises;
    @Autowired
    private NotificacionServises notificacionServises;

    @GetMapping()
    private ModelAndView list() {
        String rol = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toArray()[0].toString();
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (redirect) {
            if ((Boolean) atributes.get("estado")) {
                atributes.put("notificaciones", notificacionServises.allNotificacionesByDestinatario(username));
            } else {
                atributes.put("notificaciones", notificacionServises.allNotificacionesByRemitente(username));
            }
            this.redirect = false;
            return new ModelAndView("Index.html", atributes);
        } else {
            atributes.put("url", "guardia");
            if (rol.equals("ROLE_PROFESOR")) {
                atributes.put("datos", servises.allGuardiaByUserName(username));
            } else if (rol.equals("ROLE_ESTUDIANTE")) {
                atributes.put("datos", servises.allGuardiaByIntegranteUserName(username));
            } else {
                atributes.put("datos", servises.allGuardia());
            }
            atributes.put("activo", false);
            atributes.put("estado", true);
            atributes.put("notificaciones", notificacionServises.allNotificacionesByDestinatario(username));
            atributes.put("notificacion", new Notificaciones());
            atributes.put("datosN", uServises.allUsers());
            atributes.put("datosU", uServises.allUsersProfesores());
            atributes.put("buscar", "");
            atributes.put("dataForm", new Guardia());
            atributes.put("modificar", false);
            atributes.put("ruta", "Marco");
            atributes.put("fragmento", "marco");
            atributes.put("formulario", "Forms/Guardia.html");
            atributes.put("tabla", "Tablas/Guardia.html");
            return new ModelAndView("Index.html", atributes);
        }
    }

    @PostMapping()
    private String saveAnUpdate(@ModelAttribute("dataForm") Guardia guardia) {
        if (guardia.getId() == null) {
            servises.saveGuardia(guardia);
        } else {
            servises.updateGuardia(guardia);
            ;
        }
        return "redirect:/guardia";
    }

    @PostMapping("/modificar")
    private String findById(@RequestParam("id") Integer id) {
        this.redirect = true;
        atributes.replace("modificar", true);
        atributes.replace("dataForm", servises.findGuardiaById(id));
        return "redirect:/guardia";
    }

    @PostMapping("/delete")
    private String delete(@RequestParam("ids") Integer ids[]) {
        servises.deleteGuardia(ids);
        return "redirect:/guardia";
    }

    @PostMapping("/buscar")
    private String searchUsers(@RequestParam("text") String text) {
        this.redirect = true;
        if (text.equals("")) {
            atributes.replace("datos", servises.allGuardia());
        } else {
            atributes.replace("datos", servises.searchGuardia(text));
        }
        atributes.replace("buscar", text);
        return "redirect:/guardia";
    }

    @PostMapping("/notificacion")
    private String addNotificacion(@ModelAttribute("notificacion") Notificaciones notificacion) {
        notificacionServises.saveNotificacion(notificacion);
        this.redirect = true;
        atributes.replace("activo", true);
        return "redirect:/guardia";
    }

    @PostMapping("/notificacion/delete")
    private String deleteNotificacion(@RequestParam("ids") Integer ids[]) {
        notificacionServises.deleteNotificacion(ids);
        this.redirect = true;
        atributes.replace("activo", true);
        return "redirect:/guardia";
    }

    @PostMapping("/notificacion/estado")
    private String estadoNotificacion(@RequestParam("estado") Boolean estado) {
        atributes.replace("estado", estado);
        this.redirect = true;
        atributes.replace("activo", true);
        return "redirect:/guardia";
    }
}

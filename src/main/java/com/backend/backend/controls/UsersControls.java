package com.backend.backend.controls;

import java.util.HashMap;
import java.util.Map;

import com.backend.backend.repositorys.Notificaciones;
import com.backend.backend.repositorys.Users;
import com.backend.backend.services.NotificacionServises;
import com.backend.backend.services.UsersServises;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/usuarios")
public class UsersControls {

    private Map<String, Object> atributes = new HashMap<>();
    private Boolean redirect = false;

    @Autowired
    private NotificacionServises notificacionServises;

    @Autowired
    private UsersServises servises;

    @GetMapping()
    private ModelAndView listar() throws Exception {
        String rol = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toArray()[0].toString();
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (rol.equals("ROLE_ADMINISTRADOR")) {
            if (redirect) {
                if ((Boolean) atributes.get("estado")) {
                    atributes.put("notificaciones", notificacionServises.allNotificacionesByDestinatario(username));
                } else {
                    atributes.put("notificaciones", notificacionServises.allNotificacionesByRemitente(username));
                }
                this.redirect = false;
                return new ModelAndView("Index.html", atributes);
            } else {
                atributes.put("estado", true);
                atributes.put("notificaciones", notificacionServises.allNotificacionesByDestinatario(username));
                atributes.put("activo", false);
                atributes.put("notificacion", new Notificaciones());
                atributes.put("datosN", servises.allUsers());
                atributes.put("url", "usuarios");
                atributes.put("users", servises.allUsers());
                atributes.put("buscar", "");
                atributes.put("userForm", new Users());
                atributes.put("modificar", false);
                atributes.put("ruta", "Marco");
                atributes.put("fragmento", "marco");
                atributes.put("formulario", "Forms/Usuario.html");
                atributes.put("tabla", "Tablas/Usuario.html");
                return new ModelAndView("Index.html", atributes);
            }
        } else {
            return null;
        }
    }

    @PostMapping()
    private String saveAnUpdate(@ModelAttribute("userForm") Users users) {
        if (users.getId() == null) {
            servises.saveUser(users);
        } else {
            servises.updateUsers(users);
        }
        return "redirect:/usuarios";
    }

    @PostMapping("/modificar")
    private String buscarPorId(@RequestParam Integer id) {
        this.redirect = true;
        atributes.replace("modificar", true);
        atributes.replace("userForm", servises.findUserById(id));
        return "redirect:/usuarios";
    }

    @PostMapping("/delete")
    private String delete(@RequestParam Integer ids[]) {
        servises.deleteUsers(ids);
        return "redirect:/usuarios";
    }

    @PostMapping("/buscar")
    private String searchUsers(@RequestParam String text) {
        this.redirect = true;
        if (text.equals("")) {
            atributes.replace("users", servises.allUsers());
        } else {
            atributes.replace("users", servises.searchUsers(text));
        }
        atributes.replace("buscar", text);
        return "redirect:/usuarios";
    }

    @PostMapping("/notificacion")
    private String addNotificacion(@ModelAttribute("notificacion") Notificaciones notificacion) {
        notificacionServises.saveNotificacion(notificacion);
        this.redirect = true;
        atributes.replace("activo", true);
        return "redirect:/usuarios";
    }

    @PostMapping("/notificacion/delete")
    private String deleteNotificacion(@RequestParam("ids") Integer ids[]) {
        notificacionServises.deleteNotificacion(ids);
        this.redirect = true;
        atributes.replace("activo", true);
        return "redirect:/usuarios";
    }

    @PostMapping("/notificacion/estado")
    private String estadoNotificacion(@RequestParam("estado") Boolean estado) {
        atributes.replace("estado", estado);
        this.redirect = true;
        atributes.replace("activo", true);
        return "redirect:/usuarios";
    }
}
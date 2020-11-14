package com.backend.backend.controls;

import java.util.HashMap;
import java.util.Map;

import com.backend.backend.repositorys.Notificaciones;
import com.backend.backend.services.NotificacionServises;
import com.backend.backend.services.UsersServises;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Main {

    private Map<String, Object> atributes = new HashMap<>();
    private Boolean redirect = false;

    @Autowired
    private NotificacionServises notificacionServises;

    @Autowired
    private UsersServises userSer;

    @GetMapping({ "/", "/home" })
    private ModelAndView getIndex() {
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
            atributes.put("estado", true);
            atributes.put("notificaciones", notificacionServises.allNotificacionesByDestinatario(username));
            atributes.put("activo", false);
            atributes.put("notificacion", new Notificaciones());
            atributes.put("datosN", userSer.allUsers());
            atributes.put("url", "home");
            atributes.put("ruta", "Home");
            atributes.put("fragmento", "home");
            return new ModelAndView("Index.html", atributes);
        }
    }

    @GetMapping("/login")
    private ModelAndView login() {
        ModelAndView login = new ModelAndView("Index.html");
        login.addObject("login", "");
        return login;
    }

    @PostMapping("/home/notificacion")
    private String addNotificacion(@ModelAttribute("notificacion") Notificaciones notificacion) {
        notificacionServises.saveNotificacion(notificacion);
        this.redirect = true;
        atributes.replace("activo", true);
        return "redirect:/home";
    }

    @PostMapping("/home/notificacion/delete")
    private String deleteNotificacion(@RequestParam("ids") Integer ids[]) {
        notificacionServises.deleteNotificacion(ids);
        this.redirect = true;
        atributes.replace("activo", true);
        return "redirect:/home";
    }

    @PostMapping("/home/notificacion/estado")
    private String estadoNotificacion(@RequestParam("estado") Boolean estado) {
        atributes.replace("estado", estado);
        this.redirect = true;
        atributes.replace("activo", true);
        return "redirect:/home";
    }
}

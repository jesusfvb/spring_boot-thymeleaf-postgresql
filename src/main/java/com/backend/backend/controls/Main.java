package com.backend.backend.controls;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Main {
    private final ModelAndView index = new ModelAndView("Index.html");

    @GetMapping({ "/", "/home" })
    private ModelAndView getIndex() {
        index.addObject("ruta", "Home");
        index.addObject("fragmento", "home");
        return index;
    }

    @GetMapping("/login")
    private ModelAndView login() {
        ModelAndView login = new ModelAndView("Index.html");
        login.addObject("login", "");
        return login;
    }
}

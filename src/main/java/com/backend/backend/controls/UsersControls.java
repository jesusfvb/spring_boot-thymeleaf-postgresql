package com.backend.backend.controls;

import java.util.HashMap;
import java.util.Map;

import com.backend.backend.repositorys.Users;
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
    private UsersServises servises;

    @GetMapping()
    private ModelAndView listar() throws Exception {
        String rol = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toArray()[0].toString();
        if (rol.equals("ROLE_ADMINISTRADOR")) {
            if (redirect) {
                this.redirect = false;
                return new ModelAndView("Index.html", atributes);
            } else {
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
}
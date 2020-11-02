package com.backend.backend.controls;

import java.util.HashMap;
import java.util.Map;

import com.backend.backend.repositorys.Integrante;
import com.backend.backend.services.GuardiaServises;
import com.backend.backend.services.UsersServises;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/integrante")
public class IntegrantesControls {
    private Map<String, Object> atributes = new HashMap<>();
    private Boolean redirect = false;

    @Autowired
    private GuardiaServises servises;

    @Autowired
    private UsersServises uServises;

    @GetMapping()
    private ModelAndView list() {
        if (redirect) {
            this.redirect = false;
            return new ModelAndView("Index.html", atributes);
        } else {
            atributes.put("id", 1);
            atributes.put("datos", servises.allIntegrantes((Integer) atributes.get("id")));
            atributes.put("guardia", servises.findGuardiaById((Integer) atributes.get("id")));
            atributes.put("datosU", uServises.allUsers());
            atributes.put("buscar", "");
            atributes.put("dataForm", new Integrante());
            atributes.put("modificar", false);
            atributes.put("ruta", "Marco");
            atributes.put("fragmento", "marco");
            atributes.put("formulario", "Forms/Integrante.html");
            atributes.put("tabla", "Tablas/Integrante.html");
            return new ModelAndView("Index.html", atributes);
        }
    }

    @PostMapping("/get")
    private String get(@RequestParam("id") Integer id) {
        atributes.put("id", id);
        return "redirect:/integrante";
    }

    @PostMapping()
    private String saveAnUpdate(@ModelAttribute("dataForm") Integrante integrante) {
        if (integrante.getId() == null) {
            servises.saveIntegrante((Integer) atributes.get("id"), integrante);
        } else {
            servises.updateIntegrate((Integer) atributes.get("id"), integrante);
        }
        return "redirect:/integrante";
    }

    @PostMapping("/modificar")
    private String findById(@RequestParam("id") Integer id) {
        this.redirect = true;
        atributes.replace("modificar", true);
        atributes.replace("dataForm", servises.findIntegrateById(id));
        return "redirect:/integrante";
    }

    @PostMapping("/delete")
    private String delete(@RequestParam("ids") Integer ids[]) {
        servises.deleteInegrante(ids);
        return "redirect:/integrante";
    }

    @PostMapping("/advertencia")
    private String advertencia(@RequestParam("advertencia") String advertencia) {
        servises.updateAdvertencia((Integer) atributes.get("id"), advertencia);;
        return "redirect:/integrante";
    }

}

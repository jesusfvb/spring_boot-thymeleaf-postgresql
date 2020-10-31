package com.backend.backend.controls;

import java.util.HashMap;
import java.util.Map;

import com.backend.backend.repositorys.Ubicacion;
import com.backend.backend.services.UbicacionServises;
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
@RequestMapping("/ubicacion")
public class UbicacionControls {

    private Map<String, Object> atributes = new HashMap<>();
    private Boolean redirect = false;

    @Autowired
    private UbicacionServises servises;

    @Autowired
    private UsersServises uServises;

    @GetMapping()
    private ModelAndView list() {
        if (redirect) {
            this.redirect = false;
            return new ModelAndView("Index.html", atributes);
        } else {
            atributes.put("datos", servises.allUbicacion());
            atributes.put("datosUsers", uServises.allUsers());
            atributes.put("buscar", "");
            atributes.put("dataForm", new Ubicacion());
            atributes.put("modificar", false);
            atributes.put("ruta", "Marco");
            atributes.put("fragmento", "marco");
            atributes.put("formulario", "Forms/Ubicacion.html");
            atributes.put("tabla", "Tablas/Ubicacion.html");
            return new ModelAndView("Index.html", atributes);
        }
    }

    @PostMapping()
    private String saveAnUpdate(@ModelAttribute("dataForm") Ubicacion ubicacion) {
        if (ubicacion.getId() == null) {
            servises.saveUbicacion(ubicacion);
        } else {
            servises.updateUbicacion(ubicacion);
        }
        return "redirect:/ubicacion";
    }

    @PostMapping("/modificar")
    private String findById(@RequestParam("id") Integer id) {
        this.redirect = true;
        atributes.replace("modificar", true);
        atributes.replace("dataForm", servises.findUbicacionById(id));
        return "redirect:/ubicacion";
    }

    @PostMapping("/delete")
    private String delete(@RequestParam("ids") Integer ids[]) {
        servises.deleteUbicacion(ids);
        return "redirect:/ubicacion";
    }

    @PostMapping("/buscar")
    private String searchUsers(@RequestParam("text") String text) {
        this.redirect = true;
        if (text.equals("")) {
            atributes.replace("datos", servises.allUbicacion());
        } else {
            atributes.replace("datos", servises.searchUbicacion(text));
        }
        atributes.replace("buscar", text);
        return "redirect:/ubicacion";
    }
}

package com.backend.backend.controls;

import java.util.HashMap;
import java.util.Map;

import com.backend.backend.repositorys.Cuarteleria;
import com.backend.backend.services.CuarteleriaServises;
import com.backend.backend.services.UbicacionServises;

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
@RequestMapping("/cuarteleria")
public class CuarteliaControls {

    private Map<String, Object> atributes = new HashMap<>();
    private Boolean redirect = false;

    @Autowired
    private CuarteleriaServises servises;

    @Autowired
    private UbicacionServises uServises;

    @GetMapping()
    private ModelAndView list() {
        if (redirect) {
            this.redirect = false;
            return new ModelAndView("Index.html", atributes);
        } else {
            String rol = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toArray()[0]
                    .toString();
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            if (rol.equals("ROLE_ESTUDIANTE")) {
                atributes.put("datos", servises.allCuarteleriaByUserName(username));
            } else {
                atributes.put("datos", servises.allCuarteleria());
            }
            atributes.put("url", "cuarteleria");
            atributes.put("datosU", uServises.allUbicacionEstudiante());
            atributes.put("buscar", "");
            atributes.put("dataForm", new Cuarteleria());
            atributes.put("modificar", false);
            atributes.put("ruta", "Marco");
            atributes.put("fragmento", "marco");
            atributes.put("formulario", "Forms/Cuarteleria.html");
            atributes.put("tabla", "Tablas/Cuarteleria.html");
            return new ModelAndView("Index.html", atributes);
        }
    }

    @PostMapping()
    private String saveAnUpdate(@ModelAttribute("dataForm") Cuarteleria cuarteleria) {
        if (cuarteleria.getId() == null) {
            servises.saveCuarteleria(cuarteleria);
        } else {
            servises.updateCuarteleria(cuarteleria);
            ;
        }
        return "redirect:/cuarteleria";
    }

    @PostMapping("/modificar")
    private String findById(@RequestParam("id") Integer id) {
        this.redirect = true;
        atributes.replace("modificar", true);
        atributes.replace("dataForm", servises.findCuarteleriaById(id));
        return "redirect:/cuarteleria";
    }

    @PostMapping("/delete")
    private String delete(@RequestParam("ids") Integer ids[]) {
        servises.deleteCuarteleria(ids);
        return "redirect:/cuarteleria";
    }

    @PostMapping("/buscar")
    private String searchUsers(@RequestParam("text") String text) {
        this.redirect = true;
        if (text.equals("")) {
            atributes.replace("datos", servises.allCuarteleria());
        } else {
            atributes.replace("datos", servises.searchCuarteleria(text));
        }
        atributes.replace("buscar", text);
        return "redirect:/cuarteleria";
    }
}

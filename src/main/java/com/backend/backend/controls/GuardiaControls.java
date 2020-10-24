package com.backend.backend.controls;

import java.util.List;

import com.backend.backend.repositorys.Guardia;
import com.backend.backend.services.GuardiaServises;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/guardia")
@CrossOrigin("*")
public class GuardiaControls {
    @Autowired
    private GuardiaServises servises;

    @GetMapping()
    private ResponseEntity<List<Guardia>> list() {
        return new ResponseEntity<>(servises.allGuardia(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Guardia> findById(@PathVariable Integer id) {
        return new ResponseEntity<>(servises.findGuardiaById(id), HttpStatus.OK);
    }

    @PutMapping()
    private ResponseEntity<Object> save(@RequestBody Guardia guardia) {
        servises.saveGuardia(guardia);
        return new ResponseEntity<>("Guardia Añadida", HttpStatus.OK);
    }

    @PostMapping()
    private ResponseEntity<Object> update(@RequestBody Guardia guardia) {
        servises.updateGuardia(guardia);
        return new ResponseEntity<>("Guardia Modificada", HttpStatus.OK);
    }

    @DeleteMapping()
    private ResponseEntity<Object> delete(@RequestBody Integer ids[]) {
        servises.deleteGuardia(ids);
        return new ResponseEntity<>("Guardia(es) Eliminada(s)", HttpStatus.OK);
    }

    @PatchMapping("/{text}")
    private ResponseEntity<List<Guardia>> searchUsers(@PathVariable String text) {
        return new ResponseEntity<>(servises.searchGuardia(text), HttpStatus.OK);
    }
}

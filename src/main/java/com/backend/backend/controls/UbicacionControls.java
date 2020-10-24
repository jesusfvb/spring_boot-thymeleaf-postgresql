package com.backend.backend.controls;

import java.util.List;

import com.backend.backend.repositorys.Ubicacion;
import com.backend.backend.services.UbicacionServises;

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
@RequestMapping("/ubicacion")
@CrossOrigin("*")
public class UbicacionControls {
    @Autowired
    private UbicacionServises servises;

    @GetMapping()
    private ResponseEntity<List<Ubicacion>> list() {
        return new ResponseEntity<>(servises.allUbicacion(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Ubicacion> findById(@PathVariable Integer id) {
        return new ResponseEntity<>(servises.findUbicacionById(id), HttpStatus.OK);
    }

    @PutMapping()
    private ResponseEntity<Object> save(@RequestBody Ubicacion ubicacion) {
        servises.saveUbicacion(ubicacion);
        return new ResponseEntity<>("Ubicacion Añadido", HttpStatus.OK);
    }

    @PostMapping()
    private ResponseEntity<Object> update(@RequestBody Ubicacion ubicacion) {
        servises.updateUbicacion(ubicacion);
        return new ResponseEntity<>("Ubicacion Modificado", HttpStatus.OK);
    }

    @DeleteMapping()
    private ResponseEntity<Object> delete(@RequestBody Integer ids[]) {
        servises.deleteUbicacion(ids);
        return new ResponseEntity<>("Ubicacion(es) Eliminada(s)", HttpStatus.OK);
    }

    @PatchMapping("/{text}")
    private ResponseEntity<List<Ubicacion>> searchUsers(@PathVariable String text) {
        return new ResponseEntity<>(servises.searchUbicacion(text), HttpStatus.OK);
    }
}

package com.backend.backend.controls;

import java.util.List;

import com.backend.backend.repositorys.Cuarteleria;
import com.backend.backend.services.CuarteleriaServises;

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
@RequestMapping("/cuarteleria")
@CrossOrigin("*")
public class CuarteliaControls {

    @Autowired
    private CuarteleriaServises servises;

    @GetMapping()
    private ResponseEntity<List<Cuarteleria>> list() {
        return new ResponseEntity<>(servises.allCuarteleria(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Cuarteleria> findById(@PathVariable Integer id) {
        return new ResponseEntity<>(servises.findCuarteleriaById(id), HttpStatus.OK);
    }

    @PutMapping()
    private ResponseEntity<Object> save(@RequestBody Cuarteleria cuarteleria) {
        servises.saveCuarteleria(cuarteleria);
        return new ResponseEntity<>("Cuarteleria Añadida", HttpStatus.OK);
    }

    @PostMapping()
    private ResponseEntity<Object> update(@RequestBody Cuarteleria cuarteleria) {
        servises.updateCuarteleria(cuarteleria);
        return new ResponseEntity<>("Cuarteleria Modificada", HttpStatus.OK);
    }

    @DeleteMapping()
    private ResponseEntity<Object> delete(@RequestBody Integer ids[]) {
        servises.deleteCuarteleria(ids);
        return new ResponseEntity<>("Cuarteleria(s) Eliminada(s)", HttpStatus.OK);
    }

    @PatchMapping("/{text}")
    private ResponseEntity<List<Cuarteleria>> searchUsers(@PathVariable String text) {
        return new ResponseEntity<>(servises.searchCuarteleria(text), HttpStatus.OK);
    }
}

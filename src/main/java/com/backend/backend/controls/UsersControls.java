package com.backend.backend.controls;

import com.backend.backend.repositorys.Users;
import com.backend.backend.services.UsersServises;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(value = "*")
public class UsersControls {

    @Autowired
    private UsersServises servises;

    @GetMapping()
    private ResponseEntity<List<Users>> list() {
        return new ResponseEntity<>(servises.allUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Users> findById(@PathVariable Integer id) {
        return new ResponseEntity<>(servises.findUserById(id), HttpStatus.OK);
    }

    @PutMapping()
    private ResponseEntity<Object> save(@RequestBody Users users) {
        servises.saveUser(users);
        return new ResponseEntity<>("Usuario Añadido", HttpStatus.OK);
    }

    @PostMapping()
    private ResponseEntity<Object> update(@RequestBody Users user) {
        servises.updateUsers(user);
        return new ResponseEntity<>("Usuario Modificado", HttpStatus.OK);
    }

    @DeleteMapping()
    private ResponseEntity<Object> delete(@RequestBody Integer ids[]) {
        servises.deleteUsers(ids);
        return new ResponseEntity<>("Usuario(s) Eliminado(s)", HttpStatus.OK);
    }

    @PatchMapping("/{text}")
    private ResponseEntity<List<Users>> searchUsers(@PathVariable String text) {
        return new ResponseEntity<>(servises.searchUsers(text), HttpStatus.OK);
    }
}
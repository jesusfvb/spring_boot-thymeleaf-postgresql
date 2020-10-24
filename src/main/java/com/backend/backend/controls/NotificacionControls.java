package com.backend.backend.controls;

import java.util.List;

import com.backend.backend.controls.exceptions.NotificacionException;
import com.backend.backend.repositorys.Notificaciones;
import com.backend.backend.services.NotificacionServises;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notificacion")
@CrossOrigin(value = "*")
public class NotificacionControls {

    @Autowired
    NotificacionServises servises;

    @GetMapping("/{opcion}/{id}")
    private ResponseEntity<List<Notificaciones>> list(
            @PathVariable(name = "opcion", required = true, value = "opcion") String opcion,
            @PathVariable(name = "id", required = true, value = "id") Integer id) {
        if (opcion.equals("remitente") || opcion.equals("1")) {
            return new ResponseEntity<>(servises.allNotificacionesByRemitente(id), HttpStatus.OK);
        } else if (opcion.equals("destinatario") || opcion.equals("1")) {
            return new ResponseEntity<>(servises.allNotificacionesByDestinatario(id), HttpStatus.OK);
        } else {
            throw new NotificacionException("Balor incorrecto para Listar");
        }
    }

    // @GetMapping("/{id}")
    // private ResponseEntity<Users> findById(@PathVariable Integer id) {
    // return new ResponseEntity<>(servises.findUserById(id), HttpStatus.OK);
    // }

    @PutMapping()
    private ResponseEntity<Object> save(@RequestBody Notificaciones notificaciones) {
        servises.saveNotificacion(notificaciones);
        return new ResponseEntity<>("Notificacion Añadido", HttpStatus.OK);
    }

    @PostMapping()
    private ResponseEntity<Object> update(@RequestBody Notificaciones notificaciones) {
        servises.updateNotificacion(notificaciones);
        return new ResponseEntity<>("Notificacion Modificado", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Object> delete(@RequestBody Integer ids[], @PathVariable Integer id) {
        servises.deleteNotificacion(ids, id);;
        return new ResponseEntity<>("Notificacion(es) Eliminada(s)", HttpStatus.OK);
    }

    // @PatchMapping("/{text}")
    // private ResponseEntity<List<Users>> searchUsers(@PathVariable String text) {
    // return new ResponseEntity<>(servises.searchUsers(text), HttpStatus.OK);
    // }
}
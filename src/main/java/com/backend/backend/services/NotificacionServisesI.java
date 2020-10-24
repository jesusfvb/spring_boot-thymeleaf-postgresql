package com.backend.backend.services;

import java.util.Arrays;
import java.util.List;

import com.backend.backend.controls.exceptions.NotificacionException;
import com.backend.backend.repositorys.Notificaciones;
import com.backend.backend.repositorys.NotificacionesI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificacionServisesI implements NotificacionServises {

    @Autowired
    private NotificacionesI repository;

    @Override
    public List<Notificaciones> allNotificacionesByRemitente(Integer id) {
        if (id == null || id < 0) {
            throw new NotificacionException("Dato incorrecto");
        } else {
            return repository.findAllByRemitenteID(id);
        }
    }

    @Override
    public List<Notificaciones> allNotificacionesByDestinatario(Integer id) {
        if (id == null || id < 0) {
            throw new NotificacionException("Dato incorrecto");
        } else {
            return repository.findAllByDestinatarioID(id);
        }
    }

    @Override
    public void saveNotificacion(Notificaciones notificacion) {
        if (notificacion.getId() != null) {
            throw new NotificacionException("Dato incorrecto para Guardar");
        } else {
            repository.save(notificacion);
        }
    }

    @Override
    public void updateNotificacion(Notificaciones notificacion) {
        if (notificacion.getId() == null) {
            throw new NotificacionException("Dato incorrecto para Modificar");
        } else {
            repository.save(notificacion);
        }
    }

    @Override
    public void deleteNotificacion(Integer idNs[], Integer id) {

        if (idNs == null || idNs.length < 0 || id == null || id < 0) {
            throw new NotificacionException("Error al Eliminar");
        } else {
            List<Notificaciones> lista = repository.findAllById(Arrays.asList(idNs));
            lista.forEach(pivote -> {
                Boolean modificado = false;
                if (pivote.getRemitente() != null) {
                    if (id == pivote.getRemitente().getId()) {
                        pivote.setRemitente(null);
                        modificado = true;
                    }
                }
                if (pivote.getDestinatario() != null) {
                    if (id == pivote.getDestinatario().getId()) {
                        pivote.setDestinatario(null);
                        modificado = true;
                    }
                }

                if (modificado) {
                    if (pivote.getRemitente() == null && pivote.getDestinatario() == null) {
                        repository.delete(pivote);
                    } else {
                        repository.save(pivote);
                    }
                }
            });
        }
    }
}

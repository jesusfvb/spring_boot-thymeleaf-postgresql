package com.backend.backend.services;

import java.util.Arrays;
import java.util.List;

import com.backend.backend.repositorys.Notificaciones;
import com.backend.backend.repositorys.NotificacionesI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class NotificacionServisesI implements NotificacionServises {

    @Autowired
    private NotificacionesI repository;

    @Autowired
    private UsersServises service;

    @Override
    public List<Notificaciones> allNotificacionesByRemitente(String userName) {
        if (userName == null) {
            throw new RuntimeException("Dato incorrecto");
        } else {
            return repository.findAllByRemitenteUserName(userName);
        }
    }

    @Override
    public List<Notificaciones> allNotificacionesByDestinatario(String userName) {
        if (userName == null) {
            throw new RuntimeException("Dato incorrecto");
        } else {
            return repository.findAllByDestinatarioUserName(userName);
        }
    }

    @Override
    public void saveNotificacion(Notificaciones notificacion) {
        if (notificacion.getId() != null) {
            throw new RuntimeException("Dato incorrecto para Guardar");
        } else {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            notificacion.setDestinatario(service.findUserById(notificacion.getDestinatario().getId()));
            notificacion.setRemitente(service.findUserByUseName(username));
            notificacion.setNombreDestinatario(notificacion.getDestinatario().getName());
            notificacion.setNombreRemitente(notificacion.getRemitente().getName());
            repository.save(notificacion);
        }
    }

    @Override
    public void updateNotificacion(Notificaciones notificacion) {
        if (notificacion.getId() == null) {
            throw new RuntimeException("Dato incorrecto para Modificar");
        } else {
            repository.save(notificacion);
        }
    }

    @Override
    public void deleteNotificacion(Integer idNs[]) {

        if (idNs == null || idNs.length < 0) {
            throw new RuntimeException("Error al Eliminar");
        } else {
            List<Notificaciones> lista = repository.findAllById(Arrays.asList(idNs));
            Integer id = service.findUserByUseName(SecurityContextHolder.getContext().getAuthentication().getName())
                    .getId();
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

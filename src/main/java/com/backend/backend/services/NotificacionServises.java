package com.backend.backend.services;

import java.util.List;

import com.backend.backend.repositorys.Notificaciones;

import org.springframework.stereotype.Service;

@Service
public interface NotificacionServises {

    List<Notificaciones> allNotificacionesByRemitente(Integer id);

    List<Notificaciones> allNotificacionesByDestinatario(Integer id);

    void saveNotificacion(Notificaciones notificacion);

    void updateNotificacion(Notificaciones notificacion);

    void deleteNotificacion(Integer idNs [], Integer id);

}

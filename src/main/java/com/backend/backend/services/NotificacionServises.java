package com.backend.backend.services;

import java.util.List;

import com.backend.backend.repositorys.Notificaciones;

import org.springframework.stereotype.Service;

@Service
public interface NotificacionServises {

    List<Notificaciones> allNotificacionesByRemitente(String userName);

    List<Notificaciones> allNotificacionesByDestinatario(String userName);

    void saveNotificacion(Notificaciones notificacion);

    void updateNotificacion(Notificaciones notificacion);

    void deleteNotificacion(Integer idNs[]);

}

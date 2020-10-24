package com.backend.backend.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificacionesI extends JpaRepository<Notificaciones, Integer> {
    
    @Query("select n from Notificaciones n where n.remitente.id = ?1")
    List<Notificaciones> findAllByRemitenteID(Integer id);
   
    @Query("select n from Notificaciones n where n.destinatario.id = ?1")
    List<Notificaciones> findAllByDestinatarioID(Integer id);

}

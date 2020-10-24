package com.backend.backend.services;

import java.util.List;

import com.backend.backend.repositorys.Ubicacion;

import org.springframework.stereotype.Service;

@Service
public interface UbicacionServises {

    List<Ubicacion> allUbicacion();

    List<Ubicacion> searchUbicacion(String text);

    Ubicacion findUbicacionById(Integer id);

    void saveUbicacion(Ubicacion ubicacion);

    void updateUbicacion(Ubicacion ubicacion);

    void deleteUbicacion(Integer ids[]);
}

package com.backend.backend.services;

import java.util.Arrays;
import java.util.List;

import com.backend.backend.controls.exceptions.UbicacionException;
import com.backend.backend.controls.exceptions.UserException;
import com.backend.backend.repositorys.Ubicacion;
import com.backend.backend.repositorys.UbicacionI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UbicacionServisesI implements UbicacionServises {

    @Autowired
    private UbicacionI repository;

    @Autowired
    private UsersServises serviseUser;

    @Override
    public List<Ubicacion> allUbicacion() {
        return repository.findAllUbicacionOrderByUserName();
    }

    @Override
    public List<Ubicacion> searchUbicacion(String text) {
        if (text == null || text.length() == 0) {
            throw new UbicacionException("Falta el texto de Busqueda");
        } else {
            return repository.searchUbicacion(text);
        }
    }

    @Override
    public Ubicacion findUbicacionById(Integer id) {
        if (id == null || id < 0) {
            throw new UbicacionException("Balor de Busqueda Incorrecto");
        } else {
            return repository.findById(id).get();
        }
    }

    @Override
    public void saveUbicacion(Ubicacion ubicacion) {
        if (ubicacion == null || ubicacion.getId() != null) {
            throw new UserException("Dato Incorecto en saveUbicacion");
        } else {
            ubicacion.setUser(serviseUser.findUserById(ubicacion.getUser().getId()));
            ubicacion.addOrUpdateSerch();
            repository.save(ubicacion);

        }
    }

    @Override
    public void updateUbicacion(Ubicacion ubicacion) {
        if (ubicacion == null || ubicacion.getId() == null) {
            throw new UserException("Dato Incorecto en updateUbicacion");
        } else {
            ubicacion.setUser(serviseUser.findUserById(ubicacion.getUser().getId()));
            ubicacion.addOrUpdateSerch();
            repository.save(ubicacion);
        }
    }

    @Override
    public void deleteUbicacion(Integer[] ids) {
        if (ids.length == 0 || ids == null) {
            throw new UserException("Datos incorrectos para deleteUbicacion");
        } else {
            repository.deleteAll(repository.findAllById(Arrays.asList(ids)));
        }
    }
}

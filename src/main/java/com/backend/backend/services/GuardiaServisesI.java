package com.backend.backend.services;

import java.util.Arrays;
import java.util.List;

import com.backend.backend.controls.exceptions.GuardiaException;
import com.backend.backend.repositorys.Guardia;
import com.backend.backend.repositorys.GuardiaI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuardiaServisesI implements GuardiaServises {

    @Autowired
    private GuardiaI repository;

    @Autowired
    private UsersServises service;

    @Override
    public List<Guardia> allGuardia() {
        return repository.findAllOrderByRepresentanteName();
    }

    @Override
    public List<Guardia> searchGuardia(String text) {
        return repository.seachAll(text);
    }

    @Override
    public Guardia findGuardiaById(Integer id) {
        if (id == null || id < 0) {
            throw new GuardiaException("Balor invalido al realizar la busqueda");
        } else {
            return repository.findById(id).get();
        }
    }

    @Override
    public void saveGuardia(Guardia guardia) {
        if (guardia == null || guardia.getId() != null) {
            throw new GuardiaException("Balor invalido para salbar");
        } else {
            guardia.setRepresentante(service.findUserById(guardia.getRepresentante().getId()));
            guardia.addOrUpdateSerch();
            repository.save(guardia);
        }
    }

    @Override
    public void updateGuardia(Guardia guardia) {
        if (guardia == null || guardia.getId() == null) {
            throw new GuardiaException("Balor invalido para salbar");
        } else {
            guardia.setRepresentante(service.findUserById(guardia.getRepresentante().getId()));
            guardia.addOrUpdateSerch();
            repository.save(guardia);
        }
    }

    @Override
    public void deleteGuardia(Integer[] ids) {
        repository.deleteAll(repository.findAllById(Arrays.asList(ids)));
    }
}

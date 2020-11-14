package com.backend.backend.services;

import java.util.Arrays;
import java.util.List;

import com.backend.backend.repositorys.Guardia;
import com.backend.backend.repositorys.GuardiaI;
import com.backend.backend.repositorys.Integrante;
import com.backend.backend.repositorys.IntegranteI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuardiaServisesI implements GuardiaServises {

    @Autowired
    private GuardiaI repository;

    @Autowired
    private IntegranteI repositoryI;

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
            throw new RuntimeException("Balor invalido al realizar la busqueda");
        } else {
            return repository.findById(id).get();
        }
    }

    @Override
    public void saveGuardia(Guardia guardia) {
        if (guardia == null || guardia.getId() != null) {
            throw new RuntimeException("Balor invalido para salbar");
        } else {
            guardia.setRepresentante(service.findUserById(guardia.getRepresentante().getId()));
            guardia.addOrUpdateSerch();
            repository.save(guardia);
        }
    }

    @Override
    public void updateGuardia(Guardia guardia) {
        if (guardia == null || guardia.getId() == null) {
            throw new RuntimeException("Balor invalido para salbar");
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

    @Override
    public void saveIntegrante(Integer idG, Integrante integrante) {
        if (integrante.getId() != null) {
            throw new RuntimeException("Balor invalido para salbar Integrante");
        } else {
            integrante.setGuardia(repository.findById(idG).get());
            repositoryI.save(integrante);
        }
    }

    @Override
    public List<Integrante> allIntegrantes(Integer idG) {
        return repositoryI.findAllOrderByName(idG);
    }

    @Override
    public Integrante findIntegrateById(Integer id) {
        if (id == null) {
            throw new RuntimeException("Balor invalido para buscar Integrante por id");
        } else {
            return repositoryI.findById(id).get();
        }
    }

    @Override
    public void updateIntegrate(Integer idG, Integrante integrante) {
        if (integrante.getId() == null) {
            throw new RuntimeException("Balor invalido para actualizar Integrante");
        } else {
            integrante.setGuardia(repository.findById(idG).get());
            repositoryI.save(integrante);
        }
    }

    @Override
    public void deleteInegrante(Integer[] ids) {
        repositoryI.deleteAll(repositoryI.findAllById(Arrays.asList(ids)));
    }

    @Override
    public void updateAdvertencia(Integer idG, String advertencia) {
        if (idG == null || advertencia == null) {
            throw new RuntimeException("Balor invalido para actualizar Advertencia");
        } else {
            Guardia g = repository.findById(idG).get();
            g.setAdvertencia(advertencia);
            repository.save(g);
        }
    }

    @Override
    public List<Guardia> allGuardiaByUserName(String userName) {
        return repository.findAllByUseName(userName);
    }

    @Override
    public List<Guardia> allGuardiaByIntegranteUserName(String userName) {
        return repository.findAllByIntegranteUseName(userName);
    }
}

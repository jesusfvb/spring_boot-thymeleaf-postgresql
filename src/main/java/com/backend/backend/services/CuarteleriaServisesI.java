package com.backend.backend.services;

import java.util.Arrays;
import java.util.List;

import com.backend.backend.controls.exceptions.CuarteleriaException;
import com.backend.backend.repositorys.Cuarteleria;
import com.backend.backend.repositorys.CuarteleriaI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CuarteleriaServisesI implements CuarteleriaServises {

    @Autowired
    CuarteleriaI repository;
    @Autowired
    UbicacionServises service;

    @Override
    public List<Cuarteleria> allCuarteleria() {
        return repository.findAllOrderByCuarteleriaUserName();
    }

    @Override
    public List<Cuarteleria> searchCuarteleria(String text) {
        if(text==null||text.length()==0){
            throw new CuarteleriaException("Balor invalido para realizar la Busqueda");
        }
        else{
            return repository.seachAll(text);
        }
    }

    @Override
    public Cuarteleria findCuarteleriaById(Integer id) {
        if (id == null || id < 0) {
            throw new CuarteleriaException("Balor invalido");
        } else {
            return repository.findById(id).get();
        }
    }

    @Override
    public void saveCuarteleria(Cuarteleria cuarteleria) {
        if(cuarteleria==null||cuarteleria.getId()!=null){
            throw new CuarteleriaException("Datos invalido para añadir");
        }
        else{
            cuarteleria.setUbicacion(service.findUbicacionById(cuarteleria.getUbicacion().getId()));
            cuarteleria.addOrUpdateSerch();
            repository.save(cuarteleria);
        }
    }

    @Override
    public void updateCuarteleria(Cuarteleria cuarteleria) {
        if(cuarteleria==null||cuarteleria.getId()==null){
            throw new CuarteleriaException("Datos invalido para Modificar");
        }
        else{
            cuarteleria.setUbicacion(service.findUbicacionById(cuarteleria.getUbicacion().getId()));
            cuarteleria.addOrUpdateSerch();
            repository.save(cuarteleria);
        }
    }

    @Override
    public void deleteCuarteleria(Integer[] ids) {
        if(ids==null|| ids.length==0){
            throw new CuarteleriaException("Balor Incorrecto para Borrar");
        }
        else{
            repository.deleteAll(repository.findAllById(Arrays.asList(ids)));
        }
    }
}

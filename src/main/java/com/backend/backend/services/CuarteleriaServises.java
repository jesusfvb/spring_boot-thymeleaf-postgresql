package com.backend.backend.services;

import java.util.List;

import com.backend.backend.repositorys.Cuarteleria;

import org.springframework.stereotype.Service;

@Service
public interface CuarteleriaServises {

    List<Cuarteleria> allCuarteleria();

    List<Cuarteleria> searchCuarteleria(String text);

    Cuarteleria findCuarteleriaById(Integer id);

    void saveCuarteleria(Cuarteleria cuarteleria);

    void updateCuarteleria(Cuarteleria cuarteleria);

    void deleteCuarteleria(Integer ids[]);

}

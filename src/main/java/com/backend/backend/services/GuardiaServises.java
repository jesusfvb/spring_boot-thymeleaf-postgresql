package com.backend.backend.services;

import java.util.List;

import com.backend.backend.repositorys.Guardia;

import org.springframework.stereotype.Service;

@Service
public interface GuardiaServises {

    List<Guardia> allGuardia();

    List<Guardia> searchGuardia(String text);

    Guardia findGuardiaById(Integer id);

    void saveGuardia(Guardia guardia);

    void updateGuardia(Guardia guardia);

    void deleteGuardia(Integer ids[]);
}

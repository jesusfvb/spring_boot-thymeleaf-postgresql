package com.backend.backend.services;

import java.util.List;

import com.backend.backend.repositorys.Guardia;
import com.backend.backend.repositorys.Integrante;

import org.springframework.stereotype.Service;

@Service
public interface GuardiaServises {

    List<Guardia> allGuardia();
    
    List<Guardia> allGuardiaByUserName(String userName);

    List<Guardia> allGuardiaByIntegranteUserName(String userName);

    List<Integrante> allIntegrantes(Integer idG);

    List<Guardia> searchGuardia(String text);

    Guardia findGuardiaById(Integer id);

    Integrante findIntegrateById(Integer id);

    void saveGuardia(Guardia guardia);

    void saveIntegrante(Integer idG, Integrante integrante);

    void updateGuardia(Guardia guardia);

    void updateIntegrate(Integer idG, Integrante integrante);

    void updateAdvertencia(Integer idG, String advertencia);

    void deleteGuardia(Integer ids[]);

    void deleteInegrante(Integer ids[]);
}

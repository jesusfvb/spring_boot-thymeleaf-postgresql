package com.backend.backend.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface IntegranteI extends JpaRepository<Integrante, Integer> {

    @Query("select i from Integrante i where i.guardia.id=?1 order by i.integrante.name")
    List<Integrante> findAllOrderByName(Integer idG);

}

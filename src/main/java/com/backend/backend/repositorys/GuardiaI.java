package com.backend.backend.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GuardiaI extends JpaRepository<Guardia,Integer> {

    @Query("select g from Guardia g order by g.representante.name")
    List<Guardia> findAllOrderByRepresentanteName();
   
    @Query("select g from Guardia g where g.seach like %?1% order by g.representante.name")
    List<Guardia> seachAll(String text);

}

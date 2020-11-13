package com.backend.backend.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GuardiaI extends JpaRepository<Guardia, Integer> {

    @Query("select g from Guardia g order by g.representante.name")
    List<Guardia> findAllOrderByRepresentanteName();

    @Query(value = "select gu.* from guardia gu join users u on u.id = gu.representante_id where u.user_name=?1", nativeQuery = true)
    List<Guardia> findAllByUseName(String userName);

    @Query(value = "select gu.* from guardia gu join integrante i on i.guardia_id = gu.id join users u on u.id = i.integrante_id where u.user_name=?1", nativeQuery = true)
    List<Guardia> findAllByIntegranteUseName(String userName);

    @Query("select g from Guardia g where g.seach like %?1% order by g.representante.name")
    List<Guardia> seachAll(String text);

}

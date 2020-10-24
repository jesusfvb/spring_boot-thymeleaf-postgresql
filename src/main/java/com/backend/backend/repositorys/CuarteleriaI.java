package com.backend.backend.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CuarteleriaI extends JpaRepository<Cuarteleria,Integer> {

    @Query("select c from Cuarteleria c order by c.ubicacion.user.name")
    List<Cuarteleria> findAllOrderByCuarteleriaUserName();

    @Query("select c from Cuarteleria c where c.seach like %?1% order by c.ubicacion.user.name")
    List<Cuarteleria> seachAll(String text);
}

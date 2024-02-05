package com.example.demo.data.repositories;

import com.example.demo.data.modelo.VisitaEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitasRepository extends ListCrudRepository<VisitaEntity, Long>{


    List<VisitaEntity> findAllByUser_Name(String userName);


    @Query("""
             SELECT v FROM VisitaEntity v
             JOIN FETCH v.pois p
             JOIN FETCH p.ciudad c
             WHERE v.user.name = :userName
             """)
    List<VisitaEntity> findAllByUser_NameWithAll(String userName);



}

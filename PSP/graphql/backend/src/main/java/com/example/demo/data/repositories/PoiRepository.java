package com.example.demo.data.repositories;

import com.example.demo.data.modelo.PoiEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface PoiRepository extends ListCrudRepository<PoiEntity, Long> {
    @EntityGraph(attributePaths = {"ciudad"})
    List<PoiEntity> findAllByCiudad_id(Long idCiudad);

    @EntityGraph(attributePaths = {"ciudad"})
    List<PoiEntity> findAllByVisitas_Id(Long id);


    @Query("""
             SELECT p FROM PoiEntity p
             JOIN FETCH p.ciudad c
             """)
    List<PoiEntity> findAllWithCiudad();
}

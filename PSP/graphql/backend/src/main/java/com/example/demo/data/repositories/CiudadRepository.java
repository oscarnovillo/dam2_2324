package com.example.demo.data.repositories;

import com.example.demo.data.modelo.CiudadEntity;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CiudadRepository extends ListCrudRepository<CiudadEntity, Long> {
}

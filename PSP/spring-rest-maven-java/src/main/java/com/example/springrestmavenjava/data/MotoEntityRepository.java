package com.example.springrestmavenjava.data;

import com.example.springrestmavenjava.data.modelo.MotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotoEntityRepository extends CrudRepository<MotoEntity, Integer> {

}

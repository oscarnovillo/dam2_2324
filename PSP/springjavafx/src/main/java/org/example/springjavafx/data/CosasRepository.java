package org.example.springjavafx.data;

import org.example.springjavafx.data.modelo.Cosa;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CosasRepository extends ListCrudRepository<Cosa, UUID> {

    List<Cosa> findByUserId(UUID userId);
}

package org.example.springjavafx.data;

import org.example.springjavafx.data.modelo.User;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends ListCrudRepository<User, UUID> {
}

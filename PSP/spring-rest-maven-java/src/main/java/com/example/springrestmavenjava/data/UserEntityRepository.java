package com.example.springrestmavenjava.data;

import com.example.springrestmavenjava.data.modelo.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Integer> {
    @Transactional
    @Modifying
    @Query("update UserEntity u set u.username = ?1, u.password = ?2 where u.id = ?3")
    int updateUsernameAndPasswordById(String username, String password, Integer id);

    @Query("SELECT u FROM UserEntity u LEFT JOIN u.motos WHERE u.id = :id")
    Optional<UserEntity> findByIdWithMotos(@Param("id") Long id);

    @Override
    @Transactional
    @Modifying
    void deleteById(Integer integer);
}

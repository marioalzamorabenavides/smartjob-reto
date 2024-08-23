package com.smartjob.reto.adapter.repository;

import com.smartjob.reto.adapter.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByIdAndToken(UUID id, String token);

    List<UserEntity> findByIsActiveTrue();

    List<UserEntity> findByIsActiveFalse();
}

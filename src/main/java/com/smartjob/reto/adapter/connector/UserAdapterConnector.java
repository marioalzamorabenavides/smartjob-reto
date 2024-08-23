package com.smartjob.reto.adapter.connector;

import com.smartjob.reto.adapter.entity.UserEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserAdapterConnector {
    Optional<UserEntity> findByEmail(String email);

    UserEntity save(UserEntity userEntity);

    Optional<UserEntity> findByIdAndToken(UUID id, String token);

    List<UserEntity> findByIsActiveTrue();

    List<UserEntity> findByIsActiveFalse();

    void delete(UserEntity userEntity);
}

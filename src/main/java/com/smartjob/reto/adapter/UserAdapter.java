package com.smartjob.reto.adapter;

import com.smartjob.reto.adapter.connector.UserAdapterConnector;
import com.smartjob.reto.adapter.entity.UserEntity;
import com.smartjob.reto.adapter.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
class UserAdapter implements UserAdapterConnector {

    private final UserRepository userRepository;

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserEntity save(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    public Optional<UserEntity> findByIdAndToken(UUID id, String token) {
        return userRepository.findByIdAndToken(id, token);
    }

    @Override
    public List<UserEntity> findByIsActiveTrue() {
        return userRepository.findByIsActiveTrue();
    }

    @Override
    public List<UserEntity> findByIsActiveFalse() {
        return userRepository.findByIsActiveFalse();
    }

    @Override
    public void delete(UserEntity userEntity) {
        userRepository.delete(userEntity);
    }
}

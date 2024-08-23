package com.smartjob.reto.business;

import com.smartjob.reto.adapter.connector.UserAdapterConnector;
import com.smartjob.reto.business.connector.AuthBusinessConnector;
import com.smartjob.reto.adapter.config.JwtConfig;
import com.smartjob.reto.adapter.config.PasswordConfig;
import com.smartjob.reto.business.exception.ApiResponseErrorException;
import com.smartjob.reto.adapter.entity.UserEntity;
import com.smartjob.reto.resource.structure.UserAuthStructure;
import com.smartjob.reto.resource.structure.UserReqStructure;
import com.smartjob.reto.resource.structure.UserRespStructure;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
class AuthBusiness implements AuthBusinessConnector {

    private final UserAdapterConnector userAdapter;
    private final JwtConfig jwtConfig;
    private final PasswordConfig passwordConfig;

    @Transactional
    public UserRespStructure registerUser(UserReqStructure user) {
        if (passwordConfig.isInvalidPassword(user.password())) {
            throw new ApiResponseErrorException(passwordConfig.getPasswordMessage());
        }

        if (userAdapter.findByEmail(user.email()).isPresent()) {
            throw new ApiResponseErrorException("El correo ya se encuentra registrado");
        }

        String token = jwtConfig.generateToken(user.name(), user.email());
        UserEntity userEntity = UserEntity.buildUserEntity(user);
        userEntity.setToken(token);
        userEntity.setPassword(passwordConfig.encryptPassword(user.password()));
        userEntity = userAdapter.save(userEntity);
        return UserRespStructure.buildUserResp(userEntity);
    }

    @Transactional
    public UserRespStructure login(UserAuthStructure user) {
        Optional<UserEntity> userEntity = userAdapter.findByEmail(user.email());

        if (userEntity.isEmpty()) {
            throw new ApiResponseErrorException("El email o password es incorrecto");
        }

        if (!passwordConfig.matchesPassword(user.password(), userEntity.get().getPassword())) {
            throw new ApiResponseErrorException("El email o password es incorrecto");
        }

        String token = jwtConfig.generateToken(userEntity.get().getName(), userEntity.get().getEmail());
        userEntity.get().setLastLogin(LocalDateTime.now());
        userEntity.get().setToken(token);
        UserEntity userResp = userAdapter.save(userEntity.get());
        return UserRespStructure.buildUserResp(userResp);
    }

}

package com.smartjob.reto.business;

import com.smartjob.reto.adapter.connector.UserAdapterConnector;
import com.smartjob.reto.business.connector.UserBusinessConnector;
import com.smartjob.reto.adapter.config.PasswordConfig;
import com.smartjob.reto.business.exception.ApiResponseErrorException;
import com.smartjob.reto.adapter.entity.UserEntity;
import com.smartjob.reto.resource.structure.UserReqStructure;
import com.smartjob.reto.resource.structure.UserRespStructure;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
class UserBusiness implements UserBusinessConnector {

    private final UserAdapterConnector userAdapter;
    private final PasswordConfig passwordConfig;

    public List<UserEntity> listUsers(boolean isActive) {
        return isActive
                ? userAdapter.findByIsActiveTrue()
                : userAdapter.findByIsActiveFalse();
    }

    @Transactional
    public UserRespStructure actualizarUser(UUID id, UserReqStructure user, String token) {
        if (user.password() != null && passwordConfig.isInvalidPassword(user.password())) {
            throw new ApiResponseErrorException(passwordConfig.getPasswordMessage());
        }
        token = token.replace("Bearer ", "");
        Optional<UserEntity> userSearch = userAdapter.findByIdAndToken(id, token);
        if (userSearch.isEmpty()) {
            throw new ApiResponseErrorException("El token no corresponde al usuario");
        }

        UserEntity userEntity = UserEntity.buildUserEntityFromEntity(user, userSearch.get());
        userEntity.setPassword(user.password() != null && !user.password().isBlank() ? passwordConfig.encryptPassword(user.password()) : userSearch.get().getPassword());
        userEntity = userAdapter.save(userEntity);
        return UserRespStructure.buildUserResp(userEntity);
    }

    @Transactional
    public void statusUser(UUID id, String token, boolean status) {
        token = token.replace("Bearer ", "");
        Optional<UserEntity> userSearch = userAdapter.findByIdAndToken(id, token);
        if (userSearch.isEmpty()) {
            throw new ApiResponseErrorException("El token no corresponde al usuario");
        }
        userSearch.get().setActive(status);
        userSearch.get().setModified(LocalDateTime.now());
        userAdapter.save(userSearch.get());
    }

    @Transactional
    public void deleteUser(UUID id, String token) {
        token = token.replace("Bearer ", "");
        Optional<UserEntity> userSearch = userAdapter.findByIdAndToken(id, token);
        if (userSearch.isEmpty()) {
            throw new ApiResponseErrorException("El token no corresponde al usuario o no existe");
        }
        userAdapter.delete(userSearch.get());
    }


}

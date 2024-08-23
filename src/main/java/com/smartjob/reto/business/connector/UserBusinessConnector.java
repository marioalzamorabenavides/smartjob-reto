package com.smartjob.reto.business.connector;

import com.smartjob.reto.adapter.entity.UserEntity;
import com.smartjob.reto.resource.structure.UserReqStructure;
import com.smartjob.reto.resource.structure.UserRespStructure;

import java.util.List;
import java.util.UUID;

public interface UserBusinessConnector {
    List<UserEntity> listUsers(boolean isActive);
    UserRespStructure actualizarUser(UUID id, UserReqStructure user, String token);
    void statusUser(UUID id, String token, boolean status);
    void deleteUser(UUID id, String token);
}

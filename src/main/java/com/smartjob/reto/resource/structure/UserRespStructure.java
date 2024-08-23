package com.smartjob.reto.resource.structure;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.smartjob.reto.adapter.entity.UserEntity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserRespStructure {
    private UUID id;
    private LocalDateTime created;
    private LocalDateTime modified;
    @JsonProperty("last_login")
    private LocalDateTime lastLogin;
    private String token;
    private boolean isActive;

    public static UserRespStructure buildUserResp(UserEntity userEntity) {
        return new UserRespStructure(
                userEntity.getId(),
                userEntity.getCreated(),
                userEntity.getModified(),
                userEntity.getLastLogin(),
                userEntity.getToken(),
                userEntity.isActive()
        );
    }
}
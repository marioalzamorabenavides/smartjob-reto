package com.smartjob.reto.adapter.entity;

import com.smartjob.reto.resource.structure.UserReqStructure;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private LocalDateTime created;
    private LocalDateTime modified;
    @Column(name = "last_login")
    private LocalDateTime lastLogin;
    @Column(name = "token", length = 750)
    private String token;
    @Column(name = "is_active")
    private boolean isActive;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<PhoneEntity> phones;

    public static UserEntity buildUserEntity(UserReqStructure user) {
        return UserEntity.builder()
                .name(user.name())
                .email(user.email())
                .created(LocalDateTime.now())
                .lastLogin(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .isActive(true)
                .phones(user.phones() == null ? List.of() : user.phones()
                        .stream()
                        .parallel()
                        .map(phone -> PhoneEntity.builder()
                                .cityCode(phone.cityCode())
                                .countryCode(phone.countryCode())
                                .number(phone.number())
                                .build()).toList())
                .build();
    }

    public static UserEntity buildUserEntityFromEntity(UserReqStructure userReqStructure, UserEntity userEntity) {
        return UserEntity.builder()
                .id(userEntity.getId())
                .name(userReqStructure.name() != null && !userReqStructure.name().isBlank() ? userReqStructure.name() : userEntity.getName())
                .email(userReqStructure.email() != null && !userReqStructure.email().isBlank() ? userReqStructure.email() : userEntity.getEmail())
                .created(userEntity.getCreated())
                .lastLogin(userEntity.getLastLogin())
                .modified(LocalDateTime.now())
                .token(userEntity.getToken())
                .isActive(userEntity.isActive())
                .phones(userReqStructure.phones() == null ? userEntity.getPhones() : userReqStructure.phones()
                        .stream()
                        .parallel()
                        .map(phone -> PhoneEntity.builder()
                                .cityCode(phone.cityCode())
                                .countryCode(phone.countryCode())
                                .number(phone.number())
                                .build()).toList())
                .build();
    }
}
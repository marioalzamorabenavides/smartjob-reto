package com.smartjob.reto.adapter.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PhoneEntity {
    @Id
    @GeneratedValue
    private UUID id;
    private String number;
    @JsonProperty("cityCode")
    private String cityCode;
    @JsonProperty("countryCode")
    private String countryCode;
}

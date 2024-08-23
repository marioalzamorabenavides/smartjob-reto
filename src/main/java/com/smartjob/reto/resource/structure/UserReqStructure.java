package com.smartjob.reto.resource.structure;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public record UserReqStructure(
        @NotNull
        @NotBlank
        String name,
        @NotNull
        @Pattern(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$", message = "The email format is not valid")
        String email,
        @NotNull
        @NotBlank
        String password,
        @NotNull
        @Valid
        List<PhoneReq> phones
) {
    public record PhoneReq(
            @NotNull
            @NotBlank
            String number,
            @NotNull
            @NotBlank
            String cityCode,
            @NotNull
            @NotBlank
            String countryCode
    ) {
    }
}

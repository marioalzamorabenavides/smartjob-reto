package com.smartjob.reto.resource.structure;

import jakarta.validation.constraints.NotNull;

public record UserStatusReqStructure(
        @NotNull
        boolean status
) {
}

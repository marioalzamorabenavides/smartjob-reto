package com.smartjob.reto.resource;

import com.smartjob.reto.business.connector.UserBusinessConnector;
import com.smartjob.reto.adapter.entity.UserEntity;
import com.smartjob.reto.resource.structure.UserReqStructure;
import com.smartjob.reto.resource.structure.UserRespStructure;
import com.smartjob.reto.resource.structure.UserStatusReqStructure;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserResource {

    private final UserBusinessConnector userBusiness;

    @GetMapping
    public ResponseEntity<?> listUser() {
        List<UserEntity> registeredUser = userBusiness.listUsers(true);
        return ResponseEntity.status(HttpStatus.OK).body(registeredUser);
    }

    @GetMapping("/deactivate")
    public ResponseEntity<?> listUserDeleted() {
        List<UserEntity> registeredUser = userBusiness.listUsers(false);
        return ResponseEntity.status(HttpStatus.OK).body(registeredUser);
    }

    @PutMapping("/{id}/all")
    public ResponseEntity<?> updateUser(@PathVariable UUID id, @Valid @RequestBody UserReqStructure user, @RequestHeader("Authorization") String authorization) {
        UserRespStructure registeredUser = userBusiness.actualizarUser(id, user, authorization);
        return ResponseEntity.ok(registeredUser);
    }

    @PatchMapping("/{id}/fields")
    public ResponseEntity<?> updateAllFieldUser(@PathVariable UUID id, @RequestBody UserReqStructure user, @RequestHeader("Authorization") String authorization) {
        UserRespStructure registeredUser = userBusiness.actualizarUser(id, user, authorization);
        return ResponseEntity.ok(registeredUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletedUser(@PathVariable UUID id, @RequestHeader(name = "Authorization") String authorization) {
        userBusiness.deleteUser(id, authorization);
        return ResponseEntity.ok(Map.of("mensaje", "Se elimino el usuario correctamente."));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<?> statusUser(@PathVariable UUID id, @Valid @RequestBody UserStatusReqStructure user, @RequestHeader("Authorization") String authorization) {
        userBusiness.statusUser(id, authorization, user.status());
        return ResponseEntity.ok(Map.of("mensaje", "Se " + (user.status() ? "activo" : "desactivo") + " el usuario correctamente."));
    }
}
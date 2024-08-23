package com.smartjob.reto.resource;

import com.smartjob.reto.business.connector.AuthBusinessConnector;
import com.smartjob.reto.resource.structure.UserAuthStructure;
import com.smartjob.reto.resource.structure.UserReqStructure;
import com.smartjob.reto.resource.structure.UserRespStructure;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthResource {

    private final AuthBusinessConnector authBusiness;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserReqStructure user) {
        UserRespStructure registeredUser = authBusiness.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserAuthStructure user) {
        UserRespStructure registeredUser = authBusiness.login(user);
        return ResponseEntity.status(HttpStatus.OK).body(registeredUser);
    }
}

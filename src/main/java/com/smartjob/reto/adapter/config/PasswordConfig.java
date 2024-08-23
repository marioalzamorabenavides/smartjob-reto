package com.smartjob.reto.adapter.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class PasswordConfig {

    @Value("${app.security.password.regexp}")
    private String passwordRegex;
    @Getter
    @Value("${app.security.password.message}")
    private String passwordMessage;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String encryptPassword(String plainPassword) {
        return passwordEncoder.encode(plainPassword);
    }

    public boolean matchesPassword(String plainPassword, String encryptedPassword) {
        return passwordEncoder.matches(plainPassword, encryptedPassword);
    }

    public boolean isInvalidPassword(String password) {
        if (password == null || password.isBlank()) return true;
        Pattern pattern = Pattern.compile(passwordRegex);
        return !pattern.matcher(password).matches();
    }
}

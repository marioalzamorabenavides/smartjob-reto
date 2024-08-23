
package com.smartjob.reto.business.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;
import java.util.Objects;

@ControllerAdvice
public class GenericExceptionFilter {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, Object>> handleValidationException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        String field = Objects.requireNonNull(ex.getBindingResult().getFieldError()).getField();
        Map<String, Object> resp = Map.of("mensaje", String.join(
                field.equals("camposDiferentes") ? "" : " ",
                field.equals("camposDiferentes") ? "" : "El atributo",
                field.equals("camposDiferentes") ? "" : field,
                ex.getBindingResult().getFieldError().getDefaultMessage()
        ));
        return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ApiResponseErrorException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, Object>> handleValidationException(ApiResponseErrorException ex, HttpServletRequest request) {
        Map<String, Object> resp = Map.of("mensaje", ex.getMessage());
        return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleHibernateConstraintViolation(ConstraintViolationException ex) {
        return new ResponseEntity<>("Error de violación de restricción de Hibernate: " + ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler({RuntimeException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Map<String, Object>> handleConstraintViolation(Exception ex, HttpServletRequest request) {
        Map<String, Object> resp = Map.of("mensaje", ex.getMessage());
        return new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

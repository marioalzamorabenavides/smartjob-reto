package com.smartjob.reto.business.exception;

public class ApiResponseErrorException extends IllegalArgumentException {
    public ApiResponseErrorException(String message) {
        super(message);
    }
}

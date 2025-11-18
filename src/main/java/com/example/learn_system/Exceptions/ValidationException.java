package com.example.learn_system.Exceptions;

import org.springframework.http.HttpStatus;

// 400 - Bad Request
public class ValidationException extends BusinessException {


    public ValidationException(String message) {
        super(message, HttpStatus.BAD_REQUEST, "VALIDATION_ERROR");
    }
}

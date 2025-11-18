package com.example.learn_system.Exceptions;

import org.springframework.http.HttpStatus;


// 409 - Conflict
public class ConflictException extends BusinessException{
    public ConflictException(String message) {
        super(message, HttpStatus.CONFLICT, "CONFLICT");
    }
}

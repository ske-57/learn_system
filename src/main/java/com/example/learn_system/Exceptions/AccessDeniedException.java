package com.example.learn_system.Exceptions;

import org.springframework.http.HttpStatus;

// 403 - Forbidden
public class AccessDeniedException extends BusinessException {

    public AccessDeniedException(String message) {
        super(message, HttpStatus.FORBIDDEN, "ACCESS_DENIED");
    }
}

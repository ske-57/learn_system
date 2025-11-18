package com.example.learn_system.Exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public abstract class BusinessException extends RuntimeException {
    @Getter
    private final HttpStatus httpStatus;
    @Getter
    private final String errorCode;

    public BusinessException(String message, HttpStatus httpStatus, String errorCode) {
        super(message);
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
    }

}

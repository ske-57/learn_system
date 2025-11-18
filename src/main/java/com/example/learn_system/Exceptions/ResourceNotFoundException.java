package com.example.learn_system.Exceptions;

import org.springframework.http.HttpStatus;

// 404 - Not Found
public class ResourceNotFoundException extends BusinessException {

    public ResourceNotFoundException(String resourceName, Long id) {
        super(
                String.format("%s with id %d not found", resourceName, id),
                HttpStatus.NOT_FOUND,
                "RESOURCE_NOT_FOUND"
        );
    }

    public ResourceNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND, "RESOURCE_NOT_FOUNd");
    }
}

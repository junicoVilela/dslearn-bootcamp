package com.devsuperior.dslearn.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ForbiddenException extends RuntimeException {
    private static final long serialVersionUID = -5218386182589450194L;

    public ForbiddenException(String message) {
        super(message);
    }
}

package com.devsuperior.dslearn.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UnauthorizedException extends RuntimeException {
    private static final long serialVersionUID = -7432063198750096628L;

    public UnauthorizedException(String message) {
        super(message);
    }
}

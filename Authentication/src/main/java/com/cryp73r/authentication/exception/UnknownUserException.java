package com.cryp73r.authentication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnknownUserException extends RuntimeException{
    public UnknownUserException(String message) {
        super(message);
    }
}

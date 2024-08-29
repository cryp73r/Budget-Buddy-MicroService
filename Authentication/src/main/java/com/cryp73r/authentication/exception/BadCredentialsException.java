package com.cryp73r.authentication.exception;

import org.springframework.security.core.AuthenticationException;

public class BadCredentialsException extends RuntimeException {
    public BadCredentialsException(String errorMessage) {
        super(errorMessage);
    }
}

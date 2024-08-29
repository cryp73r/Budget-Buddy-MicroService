package com.cryp73r.authentication.exception;

public class InternalServerErrorException extends RuntimeException {
    public InternalServerErrorException(String errorMessage) {
        super(errorMessage);
    }
}

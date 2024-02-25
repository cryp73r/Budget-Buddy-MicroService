package com.cryp73r.authentication.exception;

import com.cryp73r.authentication.sdo.ExceptionSDO;
import com.cryp73r.authentication.sdo.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleUerNotFoundException(UserNotFoundException userNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionSDO(new Status(HttpStatus.NOT_FOUND, userNotFoundException.getMessage())));
    }

    @ExceptionHandler(UnknownUserException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ResponseEntity<Object> handleUnknownUserException(UnknownUserException unknownUserException) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ExceptionSDO(new Status(HttpStatus.UNAUTHORIZED, unknownUserException.getMessage())));
    }
}

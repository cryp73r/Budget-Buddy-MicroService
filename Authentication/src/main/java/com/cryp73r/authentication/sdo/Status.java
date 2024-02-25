package com.cryp73r.authentication.sdo;

import org.springframework.http.HttpStatus;

public class Status {
    private HttpStatus status;
    private String message;

    public Status(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status.value();
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

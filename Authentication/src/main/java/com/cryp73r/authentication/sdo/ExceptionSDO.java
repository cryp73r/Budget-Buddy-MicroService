package com.cryp73r.authentication.sdo;

public class ExceptionSDO {
    Status status;

    public ExceptionSDO(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}

package com.cryp73r.authentication.sdo;

public class VoidReturnTypeResponseSDO {
    Status status;

    public VoidReturnTypeResponseSDO(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}

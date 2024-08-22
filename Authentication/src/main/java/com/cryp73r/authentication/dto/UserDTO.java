package com.cryp73r.authentication.sdo;

import com.cryp73r.authentication.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

public class UserSDO {
    private Status status;
    private User user;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String token;

    public UserSDO(Status status, User user) {
        this.status = status;
        this.user = user;
    }

    public UserSDO(Status status, User user, String token) {
        this.status = status;
        this.user = user;
        this.token = token;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

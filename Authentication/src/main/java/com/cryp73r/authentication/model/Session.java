package com.cryp73r.authentication.model;

import jakarta.persistence.*;

@Entity
@Table(name = "UserSession")
public class Session {
    @Id
    @GeneratedValue
    private Long sessionId;

    @Column(nullable = false)
    private String token;

    @ManyToOne
    @JoinColumn(name = "userId", insertable = false, updatable = false, nullable = false)
    private User user;

    public Session() {

    }

    public Session(String token, User user) {
        this.token = token;
        this.user = user;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

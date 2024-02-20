package com.cryp73r.authentication.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "UserProfile")
public class User {

    @Id
    @GeneratedValue
    private Long userId;
    @Column(nullable = false)
    private String firstName;
    @Column
    private String lastName;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(unique = true)
    private String username;
    @JsonIgnore
    @Column
    private String password;

    @JsonIgnore
    @Column(nullable = false, unique = true)
    private String identifier;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", nullable = false)
    private List<Session> sessionList;

    public User() {
    }

    public User(String firstName, String username, String password) {
        this.firstName = firstName;
        this.username = username;
        this.password = password;
    }

    public User(String firstName, String email, String username, String password) {
        this.firstName = firstName;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public User(String firstName, String lastName, String email, String username, String password, String identifier) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.identifier = identifier;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public List<Session> getSessionList() {
        return sessionList;
    }

    public void setSessionList(List<Session> sessionList) {
        this.sessionList = sessionList;
    }
}

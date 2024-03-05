package com.cryp73r.expense.model;

public class Participant {
    private String firstName;
    private String lastName;
    private String username;

    public Participant(String firstName, String username) {
        this.firstName = firstName;
        this.username = username;
    }

    public Participant(String firstName, String lastName, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

package com.cryp73r.expense.model;

public class Owner {
    private String firstName;
    private String lastName;
    private String username;
    private Long amount;

    public Owner(String firstName, String username, Long amount) {
        this.firstName = firstName;
        this.username = username;
        this.amount = amount;
    }

    public Owner(String firstName, String lastName, String username, Long amount) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.amount = amount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
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
}

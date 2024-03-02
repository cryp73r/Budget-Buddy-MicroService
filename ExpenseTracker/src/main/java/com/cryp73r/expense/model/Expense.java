package com.cryp73r.expense.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Document
public class Expense {
    @MongoId
    private String id;
    private String name;
    private String description;
    private Long amount;
    private List<Owner> ownedBy;

    public Expense() {

    }
    public Expense(String name, String description, Long amount) {
        this.name = name;
        this.description = description;
        this.amount = amount;
    }

    public Expense(String name, String description, Long amount, List<Owner> ownedBy) {
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.ownedBy = ownedBy;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public List<Owner> getOwnedBy() {
        return ownedBy;
    }

    public void setOwnedBy(List<Owner> ownedBy) {
        this.ownedBy = ownedBy;
    }
}

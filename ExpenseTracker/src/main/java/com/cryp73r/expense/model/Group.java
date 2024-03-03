package com.cryp73r.expense.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Document
public class Group {
    @MongoId
    private String id;
    private String name;
    private String description;
    private String currency;
    private List<String> expenseList;

    public Group() {

    }

    public Group(String name, String description, String currency) {
        this.name = name;
        this.description = description;
        this.currency = currency;
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<String> getExpenseList() {
        return expenseList;
    }

    public void setExpenseList(List<String> expenseList) {
        this.expenseList = expenseList;
    }

    public void addExpense(String expense) {
        expenseList.add(expense);
    }
}

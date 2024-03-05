package com.cryp73r.expense.service;

import com.cryp73r.expense.model.Expense;
import com.cryp73r.expense.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExpenseService {

    @Autowired
    ExpenseRepository expenseRepository;


    public String addExpense(Expense expense) {
        expenseRepository.save(expense);
        return expense.getId();
    }

    public Optional<Expense> getExpense(String id) {
        return expenseRepository.findById(id);
    }
}

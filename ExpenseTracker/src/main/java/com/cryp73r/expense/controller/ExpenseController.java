package com.cryp73r.expense.controller;

import com.cryp73r.expense.model.Expense;
import com.cryp73r.expense.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/")
public class ExpenseController {

    @Autowired
    ExpenseService expenseService;

    @PostMapping(value = "/expense/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createExpense(@RequestBody Expense expense) {
        expenseService.createExpense(expense);
    }

    @GetMapping(value = "/expense/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Optional<Expense> getExpense(@PathVariable String id) {
        return expenseService.getExpense(id);
    }
}

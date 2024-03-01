package com.cryp73r.expense.repository;

import com.cryp73r.expense.model.Expense;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ExpenseRepository extends MongoRepository<Expense, String> {
}

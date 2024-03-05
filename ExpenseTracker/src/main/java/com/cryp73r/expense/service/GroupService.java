package com.cryp73r.expense.service;

import com.cryp73r.expense.model.Expense;
import com.cryp73r.expense.model.Group;
import com.cryp73r.expense.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class GroupService {

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    ExpenseService expenseService;
    public void createGroup(Group group) {
        group.setExpenseList(new ArrayList<>());
        groupRepository.save(group);
    }

    public Optional<Group> getGroup(String groupId) {
        return groupRepository.findById(groupId);
    }

    public void addExpenseInGroup(String groupId, Expense expense) {
        groupRepository.findById(groupId).ifPresent((group) -> {
            expense.setGroupId(groupId);
            String expenseId = expenseService.addExpense(expense);
            group.addExpense(expenseId);
            groupRepository.save(group);
        });
    }
}

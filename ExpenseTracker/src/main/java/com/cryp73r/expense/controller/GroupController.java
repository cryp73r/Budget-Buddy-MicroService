package com.cryp73r.expense.controller;

import com.cryp73r.expense.model.Expense;
import com.cryp73r.expense.model.Group;
import com.cryp73r.expense.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/")
public class GroupController {

    @Autowired
    GroupService groupService;

    @PostMapping(value = "/group/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createGroup(@RequestBody Group group) {
        groupService.createGroup(group);
    }

    @GetMapping(value = "/group/{groupId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Optional<Group> getGroup(@PathVariable String groupId) {
        return groupService.getGroup(groupId);
    }

    @PostMapping(value = "/group/{groupId}/addExpense", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void addExpenseInGroup(@PathVariable String groupId, @RequestBody Expense expense) {
        groupService.addExpenseInGroup(groupId, expense);
    }
}

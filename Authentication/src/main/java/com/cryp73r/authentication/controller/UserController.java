package com.cryp73r.authentication.controller;

import com.cryp73r.authentication.model.User;
import com.cryp73r.authentication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/user/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody User user) {
        userService.createUser(user);
    }

    @PostMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void loginUser(@RequestBody User user) {
        userService.loginUser(user);
    }

    @GetMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public User getUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        return userService.getUser(token);
    }

    @PostMapping(value = "/user")
    @ResponseStatus(HttpStatus.OK)
    public void logoutUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        userService.logoutUser(token);
    }

    @DeleteMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        userService.deleteUser(token);
    }
}

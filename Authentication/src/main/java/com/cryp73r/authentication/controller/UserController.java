package com.cryp73r.authentication.controller;

import com.cryp73r.authentication.model.User;
import com.cryp73r.authentication.sdo.UserSDO;
import com.cryp73r.authentication.sdo.VoidReturnTypeResponseSDO;
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

    @PostMapping(value = "/user/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UserSDO createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PostMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public UserSDO loginUser(@RequestBody User user) {
        return userService.loginUser(user);
    }

    @GetMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public UserSDO getUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        return userService.getUser(token);
    }

    @PostMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public VoidReturnTypeResponseSDO logoutUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        return userService.logoutUser(token);
    }

    @DeleteMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public VoidReturnTypeResponseSDO deleteUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        return userService.deleteUser(token);
    }
}

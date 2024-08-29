package com.cryp73r.authentication.controller;

import com.cryp73r.authentication.dto.UserDTO;
import com.cryp73r.authentication.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/user/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createUser(@RequestBody UserDTO userDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", userService.signUp(userDTO));
        return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(null);
    }

    @PostMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> loginUser(@RequestBody UserDTO userDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", userService.loginUser(userDTO));
        return ResponseEntity.noContent().headers(headers).build();
    }

    @PreAuthorize("hasRole('GENERAL_USER')")
    @GetMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> getUser() {
        return ResponseEntity.ok(userService.getCurrentUser(getAuthenticatedUsername()));
    }

    @PreAuthorize("hasRole('GENERAL_USER')")
    @PostMapping(value = "/user/logout", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> logoutUser() {
        SecurityContextHolder.clearContext();
        // TODO: Implement logic to blacklist the token
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('GENERAL_USER')")
    @DeleteMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteUser() {
        userService.deleteUser(getAuthenticatedUsername());
        return ResponseEntity.noContent().build();
    }

    private String getAuthenticatedUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }
}

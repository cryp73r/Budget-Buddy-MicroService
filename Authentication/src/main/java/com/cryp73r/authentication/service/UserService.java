package com.cryp73r.authentication.service;

import com.cryp73r.authentication.exception.UnknownUserException;
import com.cryp73r.authentication.exception.UserNotFoundException;
import com.cryp73r.authentication.model.Session;
import com.cryp73r.authentication.model.User;
import com.cryp73r.authentication.repository.SessionRepository;
import com.cryp73r.authentication.repository.UserRepository;
import com.cryp73r.authentication.sdo.Status;
import com.cryp73r.authentication.sdo.UserSDO;
import com.cryp73r.authentication.security.IdentifierManager;
import com.cryp73r.authentication.security.PasswordManager;
import com.cryp73r.authentication.security.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionRepository sessionRepository;

    public UserService() {

    }
    public UserSDO createUser(User user) {
        PasswordManager passwordManager = new PasswordManager();
        IdentifierManager identifierManager = new IdentifierManager();
        SessionManager sessionManager = new SessionManager();
        String encodedPassword = passwordManager.encode(user.getPassword());
        user.setPassword(encodedPassword);
        String identifier = identifierManager.generateIdentifier();
        user.setIdentifier(identifier);
        String token = sessionManager.generateToken(identifier);
        Session session = new Session(token, user);
        List<Session> sessionList = new ArrayList<>();
        sessionList.add(session);
        user.setSessionList(sessionList);
        userRepository.save(user);
        Status status = new Status(HttpStatus.CREATED, user.getUsername() + " is created successfully");
        return new UserSDO(status, user, token);
    }

    public UserSDO loginUser(User user) {
        User userWithUsername = null;
        for (User iterUser : userRepository.findAll()) {
            if (iterUser.getUsername().equals(user.getUsername())) {
                userWithUsername = iterUser;
                break;
            }
        }
        if (userWithUsername == null) {
            throw new UserNotFoundException(user.getUsername() + " doesn't exist, please check the username once");
        }
        PasswordManager passwordManager = new PasswordManager();
        boolean isValid = passwordManager.matches(user.getPassword(), userWithUsername.getPassword());
        if (isValid) {
            SessionManager sessionManager = new SessionManager();
            String token = sessionManager.generateToken(userWithUsername.getIdentifier());
            Session session = new Session(token, userWithUsername);
            List<Session> sessionList = new ArrayList<>();
            sessionList.add(session);
            userWithUsername.setSessionList(sessionList);
            userRepository.save(userWithUsername);
            Status status = new Status(HttpStatus.OK, userWithUsername.getUsername() + " logged in successfully");
            return new UserSDO(status, userWithUsername, token);
        } else throw new UnknownUserException("Invalid username/password");
    }

    public UserSDO getUser(String token) {
        User userToGet = validateTokenAndFindUser(token);
        if (userToGet == null) throw new UserNotFoundException("User doesn't exist, please check the authorization once");
        Status status = new Status(HttpStatus.OK, userToGet.getUsername()+"'s profile fetched successfully");
        return new UserSDO(status, userToGet);
    }

    public void logoutUser(String token) {
        User userToLogout = validateTokenAndFindUser(token);
        if (userToLogout == null) return;
        Session sessionToRemove = null;
        for (Session session : userToLogout.getSessionList()) {
            if (session.getToken().equals(token)) sessionToRemove = session;
        }
        List<Session> updatedSessionList = userToLogout.getSessionList();
        updatedSessionList.remove(sessionToRemove);
        userToLogout.setSessionList(updatedSessionList);
        userRepository.save(userToLogout);
        assert sessionToRemove != null;
        sessionRepository.delete(sessionToRemove);
    }

    public void deleteUser(String token) {
        User userToDelete = validateTokenAndFindUser(token);
        if (userToDelete == null) return; // later handle this
        userRepository.delete(userToDelete);
    }

    private User validateTokenAndFindUser(String token) {
        SessionManager sessionManager = new SessionManager();
        boolean isExpired = sessionManager.isTokenExpired(token);
        if (isExpired) return null;
        String identifier = sessionManager.getIdentifier(token);
        User userToReturn = null;
        for (User user : userRepository.findAll()) {
            if (user.getIdentifier().equals(identifier)) {
                userToReturn = user;
                break;
            }
        }
        if (userToReturn == null) return null;
        for (Session session : userToReturn.getSessionList()) {
            if (session.getToken().equals(token)) return userToReturn;
        }
        return null;
    }
}

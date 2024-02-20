package com.cryp73r.authentication.service;

import com.cryp73r.authentication.model.Session;
import com.cryp73r.authentication.model.User;
import com.cryp73r.authentication.repository.SessionRepository;
import com.cryp73r.authentication.repository.UserRepository;
import com.cryp73r.authentication.security.IdentifierManager;
import com.cryp73r.authentication.security.PasswordManager;
import com.cryp73r.authentication.security.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void createUser(User user) {
        PasswordManager passwordManager = new PasswordManager();
        IdentifierManager identifierManager = new IdentifierManager();
        SessionManager sessionManager = new SessionManager();
        String encodedPassword = passwordManager.encode(user.getPassword());
        user.setPassword(encodedPassword);
        String identifier = identifierManager.generateIdentifier();
        user.setIdentifier(identifier);
        Session session = new Session(sessionManager.generateToken(identifier), user);
        List<Session> sessionList = new ArrayList<>();
        sessionList.add(session);
        user.setSessionList(sessionList);
        userRepository.save(user);
    }

    public void loginUser(User user) {
        User userWithUsername = null;
        for (User iterUser : userRepository.findAll()) {
            if (iterUser.getUsername().equals(user.getUsername())) {
                userWithUsername = iterUser;
                break;
            }
        }
        if (userWithUsername == null) {
            System.out.println("*** User doesn't exist ***");
            return;
        }
        PasswordManager passwordManager = new PasswordManager();
        boolean isValid = passwordManager.matches(user.getPassword(), userWithUsername.getPassword());
        if (isValid) {
            SessionManager sessionManager = new SessionManager();
            Session session = new Session(sessionManager.generateToken(userWithUsername.getIdentifier()), userWithUsername);
            List<Session> sessionList = new ArrayList<>();
            sessionList.add(session);
            userWithUsername.setSessionList(sessionList);
            userRepository.save(userWithUsername);
        }
        else System.out.println("*** Invalid username/password ***");
    }

    public User getUser(String token) {
        User userToGet = validateTokenAndFindUser(token);
        if (userToGet == null) return null; // later handle this
        return userToGet;
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

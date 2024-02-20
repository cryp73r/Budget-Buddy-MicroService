package com.cryp73r.authentication.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordManager {

    final private BCryptPasswordEncoder bCryptPasswordEncoder;

    public PasswordManager() {
        bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    public String encode(String rawPassword) {
        return bCryptPasswordEncoder.encode(rawPassword);
    }

    public boolean matches(String rawPassword, String encodedPassword) {
        return bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
    }

    public boolean upgradeEncoding(String encodedPassword) {
        return bCryptPasswordEncoder.upgradeEncoding(encodedPassword);
    }
}

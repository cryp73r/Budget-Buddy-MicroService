package com.cryp73r.authentication.security;

import org.springframework.stereotype.Component;

import java.util.Random;

@Deprecated
@Component
public class IdentifierManager {

    public String generateIdentifier() {
        String epochInMilliStart = Long.toString(System.currentTimeMillis());
        String randomString = randomString().toString();
        String epochInMilliEnd = Long.toString(System.currentTimeMillis());
        return (epochInMilliStart + randomString + epochInMilliEnd);
    }

    private StringBuilder randomString() {
        String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String specialCharacters = "!@#$%^&()*-_";
        String combinedCharacters = capitalCaseLetters + lowerCaseLetters + numbers + specialCharacters;
        StringBuilder randomResultantString = new StringBuilder();
        for (int i=0; i<10; i++) {
            Random random = new Random();
            randomResultantString.append(combinedCharacters.charAt(random.nextInt(8)));
        }
        return  randomResultantString;
    }
}

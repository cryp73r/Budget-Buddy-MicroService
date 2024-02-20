package com.cryp73r.authentication.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class SessionManager {
    private static final String SECRET = "19K5353D67566B597033L73367639792F423F45N2LK848902B4DJ62516554P685769JI6K5A71S34743739I64";
    private static final Long EXPIRATION_TIME = 864000000L;

    public String generateToken(String identifier) {
        return Jwts.builder()
                .subject(identifier)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSignKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    public boolean isTokenExpired(String token) {
        Claims payload = getPayload(token);
        return payload.getExpiration().before(new Date());
    }

    public String getIdentifier(String token) {
        Claims payload = getPayload(token);
        return payload.getSubject();
    }

    private Claims getPayload(String token) {
        return Jwts.parser()
                .setSigningKey(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}

package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtService {
    @Value("${application.security.jwt.secret-key}")
    private String secretKey;

    @Value("${application.security.jwt.expiration}")
    private int jwtExpiration;

    @Value("${application.security.jwt.refresh-token.expiration}")
    private int refreshExpiration;

    @Value("${application.security.jwt.reset-token.expiration}")
    private int resetExpiration;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private<T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return (Claims) Jwts
                .parser()
                .verifyWith(getSignInKey())
                .build()
                .parse(token)
                .getPayload();
    }

    public String generateJwtToken(UserDetails user) {
        return buildToken(new HashMap<>(), user, jwtExpiration);
    }

    public String generateResetToken(UserDetails user) {
        return buildToken(new HashMap<>(), user, resetExpiration);
    }

    public String generateRefreshToken(UserDetails user) {
        return buildToken(new HashMap<>(), user, refreshExpiration);
    }

    private String buildToken(
            Map<String, Object> extraClaim,
            UserDetails user,
            long timeExpiration
    ) {
        return Jwts
                .builder()
                .subject(user.getUsername())
                .claims(extraClaim)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + timeExpiration))
                .signWith(getSignInKey())
                .compact();
    }

    private SecretKey getSignInKey() {
        byte[] keyInBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyInBytes);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
}

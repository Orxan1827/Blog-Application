package com.example.blogapp.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static io.jsonwebtoken.SignatureAlgorithm.HS256;

@Service
public class TokenService {

    @Value("${jwt-variables.KEY}")
    private String KEY;

    @Value("${jwt-variables.ISSUER}")
    private String ISSUER;

    @Value("${jwt-variables.EXPIRES_ACCESS_TOKEN_MINUTE}")
    private Integer EXPIRES_ACCESS_TOKEN_MINUTE;


    private Jwt getJwt(String token) {
        return Jwts.parserBuilder()
                .requireAudience("string")
                .build()
                .parse(token);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).getBody();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).after(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && isTokenExpired(token);
    }

    public String generateToken(Authentication auth) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, auth);
    }

//    public String generateToken(String username) {
//        Map<String, Object> claims = new HashMap<>();
//        return createToken(claims, username);
//    }

    private String createToken(Map<String, Object> claims, Authentication auth) {
        String usernameByDetails = ((UserDetails) auth.getPrincipal()).getUsername();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(usernameByDetails)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + (EXPIRES_ACCESS_TOKEN_MINUTE * 60 * 1000)))
                .signWith(getKey(), HS256)
                .setIssuer(ISSUER)
                .compact();
    }

    private Key getKey() {
        byte[] keyBytes = Base64.getDecoder().decode(KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}

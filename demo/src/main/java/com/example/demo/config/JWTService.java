package com.example.demo.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {

    private static final String SECRET_KEY = "2b6430c78437a6a0cfb6ac5b8508842201074f2cc9605431e4002c4ec1ccbe3c"; // Replace with a secure secret key

    // Method to extract username from JWT
    public String extractUserName(String jwt) {
    	System.out.println("token"+jwt);
        return extractClaims(jwt, Claims::getSubject);
    }

    // Method to extract claims using a resolver function
    public <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Method to generate JWT with extra claims
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // Token valid for 1 day
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Method to generate JWT without extra claims
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    // Method to validate if JWT is valid for a given user
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return userName.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    // Method to check if token is expired
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Method to extract token expiration date
    private Date extractExpiration(String token) {
        return extractClaims(token, Claims::getExpiration);
    }

    // Method to extract all claims from JWT
    private Claims extractAllClaims(String token) {
    	
    	   if (token == null || token.split("\\.").length != 3) {
    	        throw new MalformedJwtException("JWT strings must contain exactly 2 period characters.");
    	    }
    	  Claims claims=null;
    	try {
    	     claims = Jwts.parserBuilder()
    	        .setSigningKey(getSigningKey())
    	        .build()
    	        .parseClaimsJws(token)
    	        .getBody();
    	} catch (MalformedJwtException e) {
    	    System.err.println("Invalid JWT Token: " + e.getMessage());
    	}
    	return claims;
    	
    }
    
    public String extractUserId(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    // Method to get the signing key from the secret key string
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
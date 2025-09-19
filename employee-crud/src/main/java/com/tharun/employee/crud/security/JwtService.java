package com.tharun.employee.crud.security;

import com.tharun.employee.crud.model.Role;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class JwtService {

    private static final String SECRET = "mysecretkeymysecretkeymysecretkeymysecretkey"; // keep 32+ chars
    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour

    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    public String generateToken(String username, Role role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("roles", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody().getSubject();
    }


    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    //    public String extractRoles(String token) {
//        return Jwts.parserBuilder().setSigningKey(key).build()
//                .parseClaimsJws(token).getBody().get("roles", String.class);
//    }


}

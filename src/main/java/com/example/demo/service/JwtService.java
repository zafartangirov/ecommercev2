package com.example.demo.service;

import com.example.demo.entity.Role;
import com.example.demo.entity.RoleName;
import com.example.demo.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.*;

@Service
public class JwtService {

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("username", user.getUsername());
        StringJoiner sj = new StringJoiner(",");
        for (Role role : user.getRoles()) {
            sj.add(role.getRoleName().toString());
        }
        claims.put("roles", sj.toString());
        return Jwts.builder()
                .subject(user.getUsername())
                .claims(claims)
                .signWith(getSecretKey())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 10))
                .compact();
    }

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor("01234567890123456789012345678912".getBytes());
    }

    public boolean validate(String token) {
        try {
            Claims claims = getClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getUsername(String token) {
        Claims claims = getClaims(token);
        return claims.getSubject();
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public List<SimpleGrantedAuthority> getRoles(String token) {
        Claims claims = getClaims(token);
        String roles = (String) claims.get("roles");
        return Arrays.stream(roles.split(",")).map(SimpleGrantedAuthority::new).toList();
    }

}

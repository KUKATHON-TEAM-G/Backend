package com.kukathon.teamg.auth.utils;

import com.kukathon.teamg.auth.service.CustomUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import java.util.ArrayList;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtAuthenticator {

    private final CustomUserDetailsService customUserDetailsService;

    @Value("${jwt.secret-key}")
    private String secretKey;

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token)
            .getBody();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = getAllClaimsFromToken(token);

        String email = claims.getSubject();

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(
            email);
        return new UsernamePasswordAuthenticationToken(userDetails, "", new ArrayList<>());
    }

    public boolean isTokenExpired(String token) {
        try {
            getExpirationDateFromToken(token);
            return false;
        } catch (ExpiredJwtException e) {
            return true;
        }
    }

    private Date getExpirationDateFromToken(String token) {
        return getAllClaimsFromToken(token).getExpiration();
    }

}

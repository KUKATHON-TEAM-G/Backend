package com.kukathon.teamg.auth.utils;

import com.kukathon.teamg.auth.dto.TokenInfoResponse;
import com.kukathon.teamg.auth.dto.TokenResponse;
import com.kukathon.teamg.auth.service.CustomUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.ArrayList;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private static final String BEARER_MESSAGE = "Bearer";
    private final CustomUserDetailsService customUserDetailsService;

    @Value("${jwt.secret-key}")
    private String secretKey;
    @Value("${jwt.access.expiration}")
    private long accessTokenExpiration;
    @Value("${jwt.access.header}")
    private String accessTokenHeader;
    @Value("${jwt.refresh.expiration}")
    private long refreshTokenExpiration;
    @Value("${jwt.refresh.header}")
    private String refreshTokenHeader;
    @Value("${jwt.issuer}")
    private String issuer;

    public TokenResponse createTokenInfo(final String subject) {
        long now = new Date().getTime();
        String accessToken = createToken(subject, accessTokenExpiration, now);
        String refreshToken = createToken(subject, refreshTokenExpiration, now);

        return TokenResponse.builder()
            .accessToken(accessToken)
            .refreshToken(refreshToken)
            .build();
    }

    private String createToken(String subject, long expires, long now) {
        return Jwts.builder()
            .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
            .setSubject(subject)
            .setExpiration(new Date(now + expires * 1000L))
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .setIssuer(issuer)
            .compact();
    }

    public TokenInfoResponse parse(String token) {
        try {
            String email = getAllClaimsFromToken(token).getSubject();
            return new TokenInfoResponse(email);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid Token");
        } catch (ExpiredJwtException e) {
            throw new IllegalArgumentException("Expired Token");
        }
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token)
            .getBody();
    }
}

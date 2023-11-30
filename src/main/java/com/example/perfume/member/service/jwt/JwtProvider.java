package com.example.perfume.member.service.jwt;

import com.example.perfume.member.exception.TokenInvalidException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Base64;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {

    private static final String IDENTIFIER = "https://inhyang.netlify.app/";
    private static final long tokenValidTime = 3600000L;
    @Value("${jwt.secret}")
    private String secretKey;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String generateAccessToken(String userPk) {
        Claims claims = Jwts.claims().setSubject(userPk);
        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setIssuer(IDENTIFIER)
                .setExpiration(new Date(now.getTime() + tokenValidTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String generateRefreshToken(String uerPk) {
        return Jwts.builder()
                .setId(uerPk)
                .setExpiration(new Date(System.currentTimeMillis() + tokenValidTime * 24 * 14))
                .setIssuer(IDENTIFIER)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    public String getUserPk(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveAccessToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    public String resolveRefreshToken(HttpServletRequest request) {
        return request.getHeader("X-REFRESH-TOKEN");
    }

    public Jws<Claims> getClaims(String jwtToken) {
        try {
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
        } catch (JwtException e) {
            throw new TokenInvalidException(e.getMessage());
        }
    }
}

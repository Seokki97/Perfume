package com.example.perfume.member.service.jwt;

import com.example.perfume.member.exception.TokenExpiredException;
import com.example.perfume.member.exception.TokenInvalidException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import java.util.Date;
import org.springframework.stereotype.Service;

@Service
public class TokenValidator {

    private static final String IDENTIFIER = "https://inhyang.netlify.app/";
    private final JwtProvider jwtProvider;

    public TokenValidator(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    public void validateToken(String jwtToken) {
        if (jwtToken == null) {
            throw new TokenInvalidException("토큰 값이 입력되지 않았습니다.");
        }
        if (isTokenExpired(jwtToken)) {
            throw new TokenExpiredException();
        }
        if (isIssuerCorrect(jwtToken)) {
            throw new TokenInvalidException("토큰 발행자가 일치하지 않습니다.");
        }
    }

    private boolean isTokenExpired(String jwtToken) {
        Jws<Claims> claims = jwtProvider.getClaims(jwtToken);
        return !claims.getBody().getExpiration().before(new Date());
    }

    private boolean isIssuerCorrect(String jwtToken) {
        return jwtProvider.getClaims(jwtToken).getBody().getIssuer().equals(IDENTIFIER);
    }
}

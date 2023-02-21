package com.example.perfume.member.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtProvider {
    private String secretKey;

    private Long tokenValidSecond = 60 * 60 * 1000L;

    private final CustomUserDetailService userDetailService;

    public JwtProvider(CustomUserDetailService userDetailService){
        this.userDetailService = userDetailService;
    }
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String userPk){

        Claims claims = Jwts.claims().setSubject(userPk);

        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(now.getTime() + tokenValidSecond))
                .signWith(SignatureAlgorithm.ES256, secretKey)
                .compact();
    }

//    public Authentication getAuthentication(String token){}

    public String getUserPk(String token){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest httpServletRequest){
        return httpServletRequest.getHeader("X-AUTH-TOKEN");
    }

    public boolean validationToken(String token){
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claimsJws.getBody().getExpiration().before(new Date());
        } catch (Exception e){
            return false;
        }
    }

}

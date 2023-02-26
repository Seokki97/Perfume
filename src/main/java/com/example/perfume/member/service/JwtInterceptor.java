package com.example.perfume.member.service;


import com.example.perfume.advice.BadRequestException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtInterceptor implements HandlerInterceptor {


    private final JwtProvider jwtProvider;

    public JwtInterceptor(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws IOException {
        String refreshToken = httpServletRequest.getHeader("X-REFRESH-TOKEN");
        try {
            if (jwtProvider.validateToken(refreshToken)) {
                return true;
            }
            throw new BadRequestException("권한이 없는 토큰입니다");
        } catch (MalformedJwtException e) {
            throw new BadRequestException("위조된 토큰입니다.");
        } catch (ExpiredJwtException e) {
            throw new BadRequestException("만료된 토큰입니다.");
        }
    }
}

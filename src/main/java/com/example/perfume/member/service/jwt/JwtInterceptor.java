package com.example.perfume.member.service.jwt;


import com.example.perfume.advice.BadRequestException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.stereotype.Component;
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
        String refreshToken = httpServletRequest.getHeader("X-AUTH-TOKEN");
        try {
            if (!jwtProvider.validateToken(refreshToken)) {
                httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                httpServletResponse.getWriter().write("만료됐다자식아");
                return false;
            }
        } catch (MalformedJwtException e) {
            throw new BadRequestException("위조된 토큰입니다.");
        } catch (ExpiredJwtException e) {
            throw new BadRequestException("만료된 토큰입니다.");
        }
        return true;
    }
}

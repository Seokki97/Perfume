package com.example.perfume.member.service.jwt;


import com.example.perfume.advice.BadRequestException;
import com.example.perfume.member.exception.TokenExpiredException;
import com.example.perfume.member.exception.TokenInvalidException;
import com.example.perfume.member.exception.UserNotFoundException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    private final JwtProvider jwtProvider;

    public JwtInterceptor(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;

        LoginCheck loginCheck = handlerMethod.getMethodAnnotation(LoginCheck.class);
        String accessToken = httpServletRequest.getHeader("Authorization");

        if (loginCheck == null) {
            return true;
        }
        if (!jwtProvider.validateToken(accessToken) || accessToken.isEmpty()) {
            throw new TokenInvalidException();
        }
        return true;
    }
}

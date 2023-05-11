package com.example.perfume.member.service.jwt;

import com.example.perfume.member.exception.TokenInvalidException;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        if (!jwtProvider.validateToken(accessToken)|| accessToken.isEmpty()) {
            throw new TokenInvalidException();
        }
        return true;
    }
}

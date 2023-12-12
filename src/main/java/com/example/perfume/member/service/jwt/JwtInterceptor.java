package com.example.perfume.member.service.jwt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    private final TokenValidator tokenValidator;

    public JwtInterceptor(TokenValidator tokenValidator) {
        this.tokenValidator = tokenValidator;
    }

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                             Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;

        LoginCheck loginCheck = handlerMethod.getMethodAnnotation(LoginCheck.class);
        String accessToken = httpServletRequest.getHeader("Authorization");

        if (loginCheck == null) {
            return true;
        }
        tokenValidator.validateToken(accessToken);
        return true;
    }
}

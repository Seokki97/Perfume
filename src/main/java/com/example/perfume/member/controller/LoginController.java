package com.example.perfume.member.controller;

import com.example.perfume.member.dto.memberDto.LoginResponse;
import com.example.perfume.member.service.LoginService;
import com.example.perfume.member.service.jwt.JwtInterceptor;
import com.example.perfume.member.service.jwt.JwtProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@RestController
@RequestMapping("/member")
public class LoginController {

    private final LoginService loginService;
    private final JwtInterceptor jwtInterceptor;

    private final JwtProvider jwtProvider;

    public LoginController(LoginService loginService, JwtInterceptor jwtInterceptor, JwtProvider jwtProvider) {
        this.loginService = loginService;
        this.jwtInterceptor = jwtInterceptor;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/response")
    public ResponseEntity<LoginResponse> responseEntity(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws IOException {

        if (!jwtInterceptor.preHandle(httpServletRequest, httpServletResponse, handler)) {
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            PrintWriter writer = httpServletResponse.getWriter();
            writer.write("만료된 토큰입니다.");
            writer.flush();
            writer.close();
            return (ResponseEntity<LoginResponse>) ResponseEntity.status(httpServletResponse.getStatus());
        }
        return ResponseEntity.ok(loginService.responseToken(httpServletRequest));
    }

    @PostMapping("/regenerate")
    public ResponseEntity<LoginResponse> regenerateEntity(HttpServletRequest httpServletRequest) {
        String accessToken = jwtProvider.resolveToken(httpServletRequest);
        String refreshToken = jwtProvider.resolveRefreshToken(httpServletRequest);
        return ResponseEntity.ok(loginService.generateNewAccessToken(accessToken, refreshToken));
    }
}

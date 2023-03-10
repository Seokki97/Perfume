package com.example.perfume.member.controller;

import com.example.perfume.member.dto.loginDto.LoginResponse;
import com.example.perfume.member.exception.TokenInvalidException;
import com.example.perfume.member.service.LoginService;
import com.example.perfume.member.service.jwt.JwtInterceptor;
import com.example.perfume.member.service.jwt.JwtProvider;
import com.example.perfume.member.service.jwt.LoginCheck;
import org.springframework.http.HttpStatus;
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

    @LoginCheck
    @PostMapping("/response")
    public ResponseEntity responseEntity(HttpServletRequest httpServletRequest) {
        String accessToken = jwtProvider.resolveToken(httpServletRequest);

        return ResponseEntity.ok(loginService.permitClientRequest(accessToken));
    }

    @PostMapping("/regenerate")
    public ResponseEntity<LoginResponse> regenerateEntity(HttpServletRequest httpServletRequest) {

        String accessToken = jwtProvider.resolveToken(httpServletRequest);
        String refreshToken = jwtProvider.resolveRefreshToken(httpServletRequest);

        return ResponseEntity.ok(loginService.generateNewAccessToken(refreshToken));
    }

}

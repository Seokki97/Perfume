package com.example.perfume.member.controller;

import com.example.perfume.member.dto.LoginResponse;
import com.example.perfume.member.service.JwtInterceptor;
import com.example.perfume.member.service.JwtProvider;
import com.example.perfume.member.service.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/member")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/response")
    public ResponseEntity<LoginResponse> responseEntity(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        return ResponseEntity.ok(loginService.responseToken(httpServletRequest,httpServletResponse));
    }

    @PostMapping("/regenerate")
    public ResponseEntity<LoginResponse> regenerateEntity(HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(loginService.generateNewAccessToken(httpServletRequest));
    }
}

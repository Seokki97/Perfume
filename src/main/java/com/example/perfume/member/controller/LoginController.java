package com.example.perfume.member.controller;

import com.example.perfume.member.dto.LoginResponse;
import com.example.perfume.member.service.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/member")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/response")
    public ResponseEntity<LoginResponse> responseEntity(HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(loginService.responseToken(httpServletRequest));
    }
}

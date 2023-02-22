package com.example.perfume.member.controller;


import com.example.perfume.member.domain.Member;
import com.example.perfume.member.dto.LoginResponse;
import com.example.perfume.member.dto.MemberRequestDto;
import com.example.perfume.member.service.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> signIn(@RequestBody MemberRequestDto memberRequestDto) {
        return ResponseEntity.ok(loginService.permitLogin(memberRequestDto));
    }
}

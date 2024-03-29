package com.example.perfume.member.controller;

import com.example.perfume.member.controller.docs.LoginControllerDocs;
import com.example.perfume.member.dto.loginDto.LoginResponse;
import com.example.perfume.member.service.LoginService;
import com.example.perfume.member.service.jwt.JwtProvider;
import com.example.perfume.member.service.jwt.LoginCheck;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api//member")
public class LoginController implements LoginControllerDocs {

    private final LoginService loginService;

    private final JwtProvider jwtProvider;

    public LoginController(LoginService loginService, JwtProvider jwtProvider) {
        this.loginService = loginService;
        this.jwtProvider = jwtProvider;
    }

    @LoginCheck
    @PostMapping("/response")
    public ResponseEntity<LoginResponse> resolveToken(final HttpServletRequest httpServletRequest) {
        String accessToken = jwtProvider.resolveAccessToken(httpServletRequest);

        return ResponseEntity.ok(loginService.permitClientRequest(accessToken));
    }

    @PostMapping("/regenerate")
    public ResponseEntity<LoginResponse> regenerateEntity(final HttpServletRequest httpServletRequest) {
        String refreshToken = jwtProvider.resolveRefreshToken(httpServletRequest);

        return ResponseEntity.ok(loginService.generateNewAccessToken(refreshToken));
    }
}

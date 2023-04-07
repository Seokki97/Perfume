package com.example.perfume.member.controller;

import com.example.perfume.member.dto.logoutDto.LogoutRequestDto;
import com.example.perfume.member.service.LogoutService;
import com.example.perfume.member.service.jwt.JwtProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/member")
public class LogoutController {

    private final LogoutService logoutService;
    private final JwtProvider jwtProvider;

    public LogoutController(LogoutService logoutService, JwtProvider jwtProvider) {
        this.logoutService = logoutService;
        this.jwtProvider = jwtProvider;
    }

    @DeleteMapping("/logout")
    public ResponseEntity<Void> permitLogout(@RequestBody LogoutRequestDto logoutRequestDto, HttpServletRequest httpServletRequest) {
        String accessToken = jwtProvider.resolveToken(httpServletRequest);
        logoutService.permitLogout(logoutRequestDto, accessToken);
        return ResponseEntity.noContent().build();
    }
}

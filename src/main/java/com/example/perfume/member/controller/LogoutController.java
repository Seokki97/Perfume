package com.example.perfume.member.controller;

import com.example.perfume.member.controller.docs.LogoutControllerDocs;
import com.example.perfume.member.dto.logoutDto.LogoutRequestDto;
import com.example.perfume.member.service.LogoutService;
import com.example.perfume.member.service.jwt.JwtProvider;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class LogoutController implements LogoutControllerDocs {

    private final LogoutService logoutService;
    private final JwtProvider jwtProvider;

    public LogoutController(LogoutService logoutService, JwtProvider jwtProvider) {
        this.logoutService = logoutService;
        this.jwtProvider = jwtProvider;
    }

    @DeleteMapping("/logout")
    public ResponseEntity<Void> permitLogout(@RequestBody final LogoutRequestDto logoutRequestDto,
                                             final HttpServletRequest httpServletRequest) {
        String accessToken = jwtProvider.resolveAccessToken(httpServletRequest);
        logoutService.permitLogout(logoutRequestDto, accessToken);
        return ResponseEntity.noContent().build();
    }
}

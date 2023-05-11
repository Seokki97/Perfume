package com.example.perfume.oauth.controller;

import com.example.perfume.member.domain.Member;
import com.example.perfume.member.dto.loginDto.LoginResponse;
import com.example.perfume.member.service.LoginService;
import com.example.perfume.oauth.controller.docs.OauthControllerDocs;
import com.example.perfume.oauth.service.OauthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequestMapping("/oauth")
public class OauthController implements OauthControllerDocs {

    private final OauthService oauthService;

    private final LoginService loginService;

    public OauthController(OauthService oauthService, LoginService loginService) {
        this.oauthService = oauthService;
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public ResponseEntity<LoginResponse> signUp(@RequestParam String code, HttpSession httpSession) {
        Member member = oauthService.loadUserProfile(code, httpSession);
        log.info("카카오에서 받은 회원 Id : {} 회원가입 요청",member.getId());
        return ResponseEntity.ok(loginService.generateToken(member.getId()));
    }
}

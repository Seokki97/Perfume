package com.example.perfume.oauth.controller;

import com.example.perfume.member.domain.Member;
import com.example.perfume.member.dto.loginDto.LoginResponse;
import com.example.perfume.member.dto.memberDto.MemberRequestDto;
import com.example.perfume.member.service.LoginService;
import com.example.perfume.oauth.controller.docs.OauthControllerDocs;
import com.example.perfume.oauth.service.OauthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

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

        return ResponseEntity.ok(loginService.generateToken(member.getMemberId()));
    }

}

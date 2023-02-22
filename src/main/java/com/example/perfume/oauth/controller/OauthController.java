package com.example.perfume.oauth.controller;

import com.example.perfume.member.domain.Member;
import com.example.perfume.member.dto.LoginResponse;
import com.example.perfume.member.service.LoginService;
import com.example.perfume.oauth.service.OauthService;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RestController
@RequestMapping("/oauth")
public class OauthController {

    private final OauthService oauthService;

    private final LoginService loginService;
    public OauthController(OauthService oauthService,LoginService loginService) {
        this.oauthService = oauthService;
        this.loginService = loginService;
    }


    //프론트로부터 인가코드 받아오기
    @GetMapping("/login")
    public ResponseEntity<LoginResponse> signUp(@RequestParam String code, HttpSession httpSession) throws ParseException {

        Member member = oauthService.loadUserProfile(code,httpSession);

        return ResponseEntity.ok(loginService.permitLogin(member.getMemberId()));
    }

}

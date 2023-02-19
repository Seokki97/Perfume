package com.example.perfume.oauth.controller;

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


    public OauthController(OauthService oauthService) {
        this.oauthService = oauthService;
    }


    //프론트로부터 인가코드 받아오기
    @GetMapping("/kakao/callback")
    public String getRedirectUrl(@RequestParam String code, HttpSession httpSession) throws ParseException {


        return oauthService.getUserProfile(code,httpSession);
    }

}

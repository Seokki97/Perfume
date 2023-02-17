package com.example.perfume.oauth.controller;

import com.example.perfume.oauth.service.OauthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/oauth")
public class OauthController {

    private final OauthService oauthService;


    public OauthController(OauthService oauthService){
        this.oauthService = oauthService;
    }


    //프론트로부터 인가코드 받아오기
    @GetMapping("/redirect-url/")
    public ResponseEntity<String> getRedirectUrl(@PathVariable("code") String code){

        return null;
    }
}

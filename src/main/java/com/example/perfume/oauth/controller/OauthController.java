package com.example.perfume.oauth.controller;

import com.example.perfume.member.domain.Member;
import com.example.perfume.member.dto.loginDto.LoginResponse;
import com.example.perfume.member.dto.memberDto.MemberRequestDto;
import com.example.perfume.member.service.LoginService;
import com.example.perfume.oauth.TokenUrl;
import com.example.perfume.oauth.service.KakaoToken;
import com.example.perfume.oauth.service.OauthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequestMapping("/oauth")
public class OauthController {

    private final KakaoToken kakaoToken;

    private final OauthService oauthService;

    private final LoginService loginService;


    public OauthController(KakaoToken KakaoToken, OauthService oauthService, LoginService loginService) {
        this.kakaoToken = KakaoToken;
        this.oauthService = oauthService;
        this.loginService = loginService;
    }

    @GetMapping("/response/token")
    public ResponseEntity<String> responseToken(@RequestParam final String code) {
        HttpEntity<MultiValueMap<String, String>> request = kakaoToken.requestTokenFromResourceServer(code);

        return new RestTemplate()
                .exchange(TokenUrl.TOKEN_REQUEST.getTokenUrl(), HttpMethod.POST, request, String.class);
    }

    @GetMapping("/load/profile")
    public ResponseEntity<String> responseUserProfile(
            @RequestHeader("Authorization") String token) {
        HttpEntity<Void> request = kakaoToken.requestUserProfileFromResourceServer(token);

        return new RestTemplate()
                .exchange(TokenUrl.USER_INFORMATION.getUserInformation(), HttpMethod.GET, request,
                        String.class);
    }

    @PostMapping("save/profile")
    public ResponseEntity<LoginResponse> saveUserProfile(@RequestBody MemberRequestDto memberRequestDto) {
        Member member = oauthService.saveUserProfile(memberRequestDto);
        return ResponseEntity.ok(loginService.generateToken(member.getMemberId()));
    }
}

package com.example.perfume.oauth.service;

import com.example.perfume.member.domain.Member;
import com.example.perfume.member.repository.MemberRepository;
import com.example.perfume.oauth.OauthType;
import com.example.perfume.oauth.exception.MemberAlreadyExistException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static org.apache.el.util.MessageFactory.get;

@Service
public class OauthService {

    private final MemberRepository memberRepository;

    public OauthService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }


    public HttpHeaders setHttpHeaders() {

        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add(OauthType.CONTENT_TYPE.getName(), OauthType.CONTENT_TYPE.getType());
        return httpHeaders;
    }

    public LinkedMultiValueMap<String, String> setHttpBody(String code) {

        LinkedMultiValueMap<String, String> accessTokenParams = new LinkedMultiValueMap<>();
        accessTokenParams.add(OauthType.GRANT_TYPE.getName(), OauthType.GRANT_TYPE.getType());
        accessTokenParams.add(OauthType.CLIENT_ID.getName(), OauthType.CLIENT_ID.getType());
        accessTokenParams.add(OauthType.REDIRECT_URI.getName(), OauthType.REDIRECT_URI.getType());
        accessTokenParams.add("code", code);
        return accessTokenParams;
    }

    public RestTemplate createRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }

    //여기까지 토큰 값 받기
    public ResponseEntity<String> getResponseFromServer(String url, String code,HttpHeaders httpHeaders) {
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>((setHttpBody(code)), httpHeaders);


        ResponseEntity<String> response = createRestTemplate().exchange(url, HttpMethod.POST, request, String.class);

        return response;
    }

    public Member loadUserProfile(String code, HttpSession httpSession) {
        try {
            JSONParser jsonParser = new JSONParser();
            HttpHeaders httpHeaders = setHttpHeaders();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(getResponseFromServer("https://kauth.kakao.com/oauth/token", code,httpHeaders).getBody());
            httpSession.setAttribute("Authorization", jsonObject.get("access_token"));

            httpHeaders.add("Authorization", "Bearer "+jsonObject.get("access_token"));

            ResponseEntity<String> responseEntity = getResponseFromServer("https://kapi.kakao.com/v2/user/me",code,httpHeaders);

            JSONObject profile = (JSONObject) jsonParser.parse(responseEntity.getBody());
            JSONObject properties = (JSONObject) profile.get("properties");
            JSONObject kakaoAccount = (JSONObject) profile.get("kakao_account");

            Long memberId= (Long) profile.get("id");
            String nickname = (String) properties.get("nickname");
            String email = (String) kakaoAccount.get("kakao_account");

            return saveUserProfile(memberId,email, nickname);

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public Member saveUserProfile(Long memberId,String email, String nickname){
        Member member = Member.builder()
                .memberId(memberId)
                .email(email)
                .nickname(nickname)
                .build();
        if(memberRepository.existsByMemberId(member.getMemberId())){
            throw new MemberAlreadyExistException();
        }
        return memberRepository.save(member);
    }

}
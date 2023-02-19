package com.example.perfume.oauth.service;

import com.example.perfume.oauth.OauthType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Service
public class OauthService {


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

    public RestTemplate getRestTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }

    public ResponseEntity<String> getResponse(String code){
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>((setHttpBody(code)),setHttpHeaders());

        String url = "https://kauth.kakao.com/oauth/token";

        ResponseEntity<String> response = getRestTemplate().postForEntity(url, request, String.class);

        return response;
    }



}

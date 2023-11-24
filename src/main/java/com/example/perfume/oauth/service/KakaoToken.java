package com.example.perfume.oauth.service;

import com.example.perfume.oauth.OauthType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Service
public class KakaoToken {

    public HttpHeaders setHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(OauthType.CONTENT_TYPE.getName(), OauthType.CONTENT_TYPE.getType());
        return httpHeaders;
    }

    private MultiValueMap<String, String> setHttpBody(String code) {
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add(OauthType.GRANT_TYPE.getName(), OauthType.GRANT_TYPE.getType());
        requestBody.add(OauthType.CLIENT_ID.getName(), OauthType.CLIENT_ID.getType());
        requestBody.add(OauthType.REDIRECT_URI.getName(), OauthType.REDIRECT_URI.getType());
        requestBody.add("code", code);

        return requestBody;
    }

    public HttpEntity<MultiValueMap<String, String>> requestTokenFromResourceServer(String code) {

        return new HttpEntity<>((setHttpBody(code)), setHttpHeaders());
    }

    public HttpHeaders requestUserProfile(String token) {
        HttpHeaders httpHeaders = setHttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + token);
        return httpHeaders;
    }

    public HttpEntity<Void> requestUserProfileFromResourceServer(String token) {
        return new HttpEntity<>(null, requestUserProfile(token));
    }
}

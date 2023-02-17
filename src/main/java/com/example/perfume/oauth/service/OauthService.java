package com.example.perfume.oauth.service;

import com.example.perfume.oauth.OauthType;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;

@Service
public class OauthService {


    public HttpHeaders setHttpHeaders() {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(OauthType.CONTENT_TYPE.getName(), OauthType.CONTENT_TYPE.getType());

        return httpHeaders;
    }

    public LinkedMultiValueMap<String, String> setHttpBody(String code) {

        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add(OauthType.GRANT_TYPE.getName(), OauthType.GRANT_TYPE.getType());
        params.add(OauthType.CLIENT_ID.getName(), OauthType.CLIENT_ID.getType());
        params.add(OauthType.REDIRECT_URI.getName(), OauthType.REDIRECT_URI.getType());
        params.add("code", code);
        return params;
    }




}

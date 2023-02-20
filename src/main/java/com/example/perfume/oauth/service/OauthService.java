package com.example.perfume.oauth.service;

import com.example.perfume.oauth.OauthType;
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

    public RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }

    //여기까지 토큰 값 받기
    public ResponseEntity<String> getResponse(String url, String code,HttpHeaders httpHeaders) {
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>((setHttpBody(code)), httpHeaders);


        ResponseEntity<String> response = getRestTemplate().exchange(url, HttpMethod.POST, request, String.class);

        return response;
    }

    public String getUserProfile(String code, HttpSession httpSession) {
        try {
            JSONParser jsonParser = new JSONParser();
            HttpHeaders httpHeaders = setHttpHeaders();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(getResponse("https://kauth.kakao.com/oauth/token", code,httpHeaders).getBody());
            httpSession.setAttribute("Authorization", jsonObject.get("access_token"));


            httpHeaders.add("Authorization", "Bearer "+jsonObject.get("access_token"));

            ResponseEntity<String> responseEntity = getResponse("https://kapi.kakao.com/v2/user/me",code,httpHeaders);

            JSONObject profile = (JSONObject) jsonParser.parse(responseEntity.getBody());
            JSONObject properties = (JSONObject) profile.get("properties");
            JSONObject kakaoAccount = (JSONObject) profile.get("kakao_account");
            return (String) properties.get("nickname");

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
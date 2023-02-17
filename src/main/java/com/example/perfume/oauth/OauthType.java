package com.example.perfume.oauth;

import lombok.Getter;

@Getter
public enum OauthType {

    CONTENT_TYPE("Content-type","application/x-www-form-urlencoded;charset=utf-8"),
    GRANT_TYPE("grant-type","authorization_code"),
    CLIENT_ID("client_id","fc231655583778a23c2eba6fcbd54a3f"),
    REDIRECT_URI("redirect_rui","http://localhost:8080/oauth/redirect-url");

    private String name;
    private String type;

    OauthType(String name, String type) {
        this.name = name;
        this.type = type;
    }

}

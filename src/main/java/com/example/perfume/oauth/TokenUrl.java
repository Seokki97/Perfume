package com.example.perfume.oauth;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public enum TokenUrl {
    TOKEN_REQUEST("https://kauth.kakao.com/oauth/token"),
    PROPERTY("[\"kakao_account.email\", \"kakao_account.name\", \"kakao_account.profile\"]"),
    USER_INFORMATION("https://kapi.kakao.com/v2/user/me");

    private final String url;

    TokenUrl(String url) {
        this.url = url;
    }

    public String getTokenUrl() {
        return url;
    }

    public String getUserInformation() {
        return USER_INFORMATION.getTokenUrl() + "?property_keys=" + URLEncoder.encode(PROPERTY.getTokenUrl(),
                StandardCharsets.UTF_8);
    }
}

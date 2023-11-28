package com.example.perfume.search;

public enum Search {

    API_URL("https://openapi.naver.com/v1/search/blog?query="),
    DISPLAY("&display=10"),
    SORT("&sort=sim"),

    CLIENT_ID("X-Naver-Client-Id"),
    API_KEY("X-Naver-Client-Secret");

    private final String value;

    Search(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

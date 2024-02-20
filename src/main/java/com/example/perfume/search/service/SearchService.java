package com.example.perfume.search.service;

import com.example.perfume.search.Search;
import java.nio.charset.StandardCharsets;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

    @Value("${naver.clientId}")
    private String clientId;
    @Value("${naver.apiKey}")
    private String secretKey;

    public HttpEntity<HttpHeaders> generateRequestEntity() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(Search.CLIENT_ID.getValue(), clientId);
        httpHeaders.add(Search.API_KEY.getValue(), secretKey);
        return new HttpEntity<>(httpHeaders);
    }

    public String generateQueryMessage(String perfumeName) {
        final String query = new String(perfumeName.getBytes(StandardCharsets.UTF_8));
        return Search.API_URL.getValue() + query + Search.DISPLAY.getValue() + Search.SORT.getValue();
    }
}

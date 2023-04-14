package com.example.perfume.perfume.service;

import com.example.perfume.config.ChatGptConfig;
import com.example.perfume.perfume.dto.story.ChatGptRequest;
import com.example.perfume.perfume.dto.story.ChatGptResponse;
import com.example.perfume.perfume.dto.story.PerfumeStoryRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PerfumeStoryService {
    @Value("${gpt.apiKey}")
    private String API_KEY;
    private static RestTemplate restTemplate = new RestTemplate();


    public HttpEntity<ChatGptRequest> createHttpEntity(ChatGptRequest chatGptRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(ChatGptConfig.MEDIA_TYPE));
        headers.add(ChatGptConfig.AUTHORIZATION, ChatGptConfig.BEARER + API_KEY);
        return new HttpEntity<>(chatGptRequest, headers);
    }

    public ChatGptResponse getResponse(HttpEntity<ChatGptRequest> chatGptRequest) {
        ResponseEntity<ChatGptResponse> responseEntity = restTemplate.postForEntity(
                ChatGptConfig.URL,
                chatGptRequest,
                ChatGptResponse.class);
        return responseEntity.getBody();
    }

    public ChatGptResponse askQuestionToChatGpt(PerfumeStoryRequest perfumeStoryRequest) {
        return this.getResponse(
                this.createHttpEntity(
                        ChatGptRequest.builder()
                                .model(ChatGptConfig.MODEL)
                                .prompt(perfumeStoryRequest.toPromptString())
                                .maxToken(ChatGptConfig.MAX_TOKEN)
                                .temperature(ChatGptConfig.TEMPERATURE)
                                .topP(ChatGptConfig.TOP_P)
                                .build()));
    }
}

package com.example.perfume.chatGpt.service;

import com.example.perfume.chatGpt.dto.GptRequestInterface;
import com.example.perfume.config.ChatGptConfig;
import com.example.perfume.chatGpt.dto.gptDto.ChatGptRequest;
import com.example.perfume.chatGpt.dto.gptDto.ChatGptResponse;
import com.example.perfume.chatGpt.dto.storyDto.PerfumeStoryRequest;
import com.example.perfume.chatGpt.exception.GptCannotMakeStoryException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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

    private HttpEntity<ChatGptRequest> createHttpEntity(ChatGptRequest chatGptRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(ChatGptConfig.MEDIA_TYPE));
        headers.add(ChatGptConfig.AUTHORIZATION, ChatGptConfig.BEARER + API_KEY);
        return new HttpEntity<>(chatGptRequest, headers);
    }

    private ChatGptResponse getResponse(HttpEntity<ChatGptRequest> chatGptRequest) {
        ResponseEntity<ChatGptResponse> responseEntity = restTemplate.postForEntity(
                ChatGptConfig.URL,
                chatGptRequest,
                ChatGptResponse.class);
        if (isGptCannotResponse(responseEntity)) {
            throw new GptCannotMakeStoryException();
        }
        return responseEntity.getBody();
    }

    public ChatGptResponse askQuestionToChatGpt(GptRequestInterface request) {
        return this.getResponse(
                this.createHttpEntity(
                        ChatGptRequest.builder()
                                .model(ChatGptConfig.MODEL)
                                .messages(GptMessageGenerator.makeMessageJSONArray(request))
                                .build()));
    }

    private boolean isGptCannotResponse(HttpEntity<ChatGptResponse> chatGptResponseEntity) {
        if (chatGptResponseEntity.getBody().getChoices().isEmpty() || chatGptResponseEntity.getBody().getChoices() == null) {
            return true;
        }
        return false;
    }
}

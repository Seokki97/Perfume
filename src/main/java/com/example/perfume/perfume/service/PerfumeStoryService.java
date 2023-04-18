package com.example.perfume.perfume.service;

import com.example.perfume.config.ChatGptConfig;
import com.example.perfume.perfume.dto.story.ChatGptRequest;
import com.example.perfume.perfume.dto.story.ChatGptResponse;
import com.example.perfume.perfume.dto.story.PerfumeStoryRequest;
import com.example.perfume.perfume.exception.GptNotResponseException;
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
    private String MY_KEY;

    public HttpEntity<ChatGptRequest> createHttpEntity(ChatGptRequest chatGptRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(ChatGptConfig.MEDIA_TYPE));
        headers.add(ChatGptConfig.AUTHORIZATION, ChatGptConfig.BEARER + MY_KEY);
        return new HttpEntity<>(chatGptRequest, headers);
    }

    //해당 부분을 post맨으로 request 보내보기
    //만약 gpt 에서 null이 들어오는거면 내가 데이터 보내는 것이 잘못됐을수도
    //gpt에서 데이터를 잘 보내주는데 응답으로 null이 들어오는거면 내가 데이터 파싱을 잘못한걸수도
    public ChatGptResponse getResponse(HttpEntity<ChatGptRequest> chatGptRequest) {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<ChatGptResponse> responseEntity = restTemplate.postForEntity(
                ChatGptConfig.URL,
                chatGptRequest,
                ChatGptResponse.class);
        return responseEntity.getBody();
    }

    public ChatGptResponse askQuestionToChatGpt(PerfumeStoryRequest perfumeStoryRequest) {
        ChatGptRequest chatGptRequest = makeGptRequest(perfumeStoryRequest);

        ChatGptResponse chatGptResponse = getResponse(this.createHttpEntity(chatGptRequest));

        if (isAnswerNull(chatGptResponse)) {
            throw new GptNotResponseException();
        }

        return chatGptResponse;
    }

    private ChatGptRequest makeGptRequest(PerfumeStoryRequest perfumeStoryRequest) {
        return ChatGptRequest.builder()
                .model(ChatGptConfig.MODEL)
                .prompt(perfumeStoryRequest.toPromptString())
                .maxToken(ChatGptConfig.MAX_TOKEN)
                .temperature(ChatGptConfig.TEMPERATURE)
                .topP(ChatGptConfig.TOP_P)
                .build();
    }
    private boolean isAnswerNull(ChatGptResponse chatGptResponse) {
        if (chatGptResponse.getChoiceList() == null) {
            return true;
        }
        return false;
    }


}

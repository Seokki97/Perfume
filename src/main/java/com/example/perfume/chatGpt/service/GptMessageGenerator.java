package com.example.perfume.chatGpt.service;

import com.example.perfume.chatGpt.dto.storyDto.PerfumeStoryRequest;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class GptMessageGenerator {
    public static JSONArray makeMessageJSONArray(PerfumeStoryRequest perfumeStoryRequest) {
        JSONArray messagesArray = new JSONArray();

        JSONObject messageObject = new JSONObject();
        messageObject.put("role", "user");
        messageObject.put("content", perfumeStoryRequest.toPromptString());

        messagesArray.add(messageObject);

        return messagesArray;
    }
}

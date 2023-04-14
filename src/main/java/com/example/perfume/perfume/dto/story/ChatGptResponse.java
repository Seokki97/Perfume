package com.example.perfume.perfume.dto.story;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
public class ChatGptResponse implements Serializable {
    private String id;
    private String object;
    private LocalDate createdTime;
    private List<Choice> choiceList;

    @Builder
    public ChatGptResponse(String id, String object, LocalDate createdTime, List<Choice> choiceList) {
        this.id = id;
        this.object = object;
        this.createdTime = createdTime;
        this.choiceList = choiceList;
    }
}

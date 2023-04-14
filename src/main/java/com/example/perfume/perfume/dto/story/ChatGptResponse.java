package com.example.perfume.perfume.dto.story;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class ChatGptResponse implements Serializable {
    private String id;
    private String object;
    private LocalDate createdTime;
    private String model;
    private List<Choice> choiceList;


    @Builder
    public ChatGptResponse(String id, String object, LocalDate createdTime,String model, List<Choice> choiceList) {
        this.id = id;
        this.object = object;
        this.createdTime = createdTime;
        this.choiceList = choiceList;
        this.model = model;
    }
}

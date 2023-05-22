package com.example.perfume.chatGpt.dto.gptDto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
public class ChatGptResponse implements Serializable {
    private String id;
    private String object;
    private LocalDate created;
    private String model;
    private List<Choice> choices;
    private Usage usage;


    @Builder
    public ChatGptResponse(String id, String object, LocalDate created, String model, List<Choice> choices, Usage usage) {
        this.id = id;
        this.object = object;
        this.created = created;
        this.choices = choices;
        this.model = model;
        this.usage = usage;
    }
}

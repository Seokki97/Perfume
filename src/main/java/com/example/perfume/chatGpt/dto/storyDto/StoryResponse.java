package com.example.perfume.chatGpt.dto.storyDto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class StoryResponse {

    private String perfumeStory;

    public StoryResponse() {

    }

    @Builder
    public StoryResponse(String perfumeStory) {
        this.perfumeStory = perfumeStory;
    }

}

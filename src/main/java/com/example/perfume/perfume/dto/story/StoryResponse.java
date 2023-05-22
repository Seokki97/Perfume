package com.example.perfume.perfume.dto.story;

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

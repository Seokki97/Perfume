package com.example.perfume.perfume.dto.perfumeDto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class StoryResponse {

    private String perfumeStory;

    @Builder
    public StoryResponse(String perfumeStory) {
        this.perfumeStory = perfumeStory;
    }

}

package com.example.perfume.chatGpt.dto.storyDto;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class PerfumeStoryRequest implements Serializable {
    private String name;

    private String scentAnswer;

    private String moodAnswer;

    private String seasonAnswer;

    private String styleAnswer;

    public PerfumeStoryRequest(){}

    @Builder
    public PerfumeStoryRequest(String name, String scentAnswer, String moodAnswer, String seasonAnswer, String styleAnswer) {
        this.name = name;
        this.scentAnswer = scentAnswer;
        this.moodAnswer = moodAnswer;
        this.seasonAnswer = seasonAnswer;
        this.styleAnswer = styleAnswer;
    }

    public String toPromptString() {
        return "제가 제시할 단어는 다음과 같습니다. " +
                "사람 이름: " + name +
                ", 향기: " + scentAnswer +
                ", 분위기: " + moodAnswer +
                ", 계절: " + seasonAnswer +
                "해당 5가지의 단어들을 조합하여 5문장 분량의 이야기를 만들어줘. 문장 번호는 붙이지 말고, '\n'도 절대 나오면 안돼";
    }
}

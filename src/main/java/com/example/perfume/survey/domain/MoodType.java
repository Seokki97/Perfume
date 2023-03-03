package com.example.perfume.survey.domain;

import com.example.perfume.survey.exception.SeasonNotFoundException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum MoodType {
    //따뜻한
    WARMTH("따뜻한", ""),
    //산뜻한
    NEAT("산뜻한",""),
    //관능적인
    SENSUAL("관능적인",""),
    //시크한
    CHIC("시크한",""),
    //차분한
    TRANQUIL("차분한",""),
    //깨끗한
    CLEAN("깨끗한",""),
    //포근한
    COZY("포근한",""),
    //세련된
    REFINED("세련된",""),
    //상큼한
    FRESH("상큼한",""),
    //달콤한
    SWEET("달콤한",""),
    //싱그러운
    REFRESHING("싱그러운","");

    private final String mood;
    private final String message;

    MoodType(String mood, String message){
        this.mood = mood;
        this.message = message;
    }

    public static String getMessage(Survey survey) {
        String expectedMood = survey.getMoodAnswer();
        MoodType moodType = Arrays.stream(MoodType.values())
                .filter(mood -> mood.getMood().equals(expectedMood)).findAny()
                .orElseThrow(SeasonNotFoundException::new);
        return moodType.getMessage();
    }
}

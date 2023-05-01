package com.example.perfume.recommend.service;

import com.example.perfume.perfume.domain.Perfume;

import java.util.List;
import java.util.Random;

public class RecommendUtils {

    public static int createRandomPerfumeFromList(List<Perfume> surveyResultList) {
        Random random = new Random();
        return random.nextInt(surveyResultList.size());
    }

    public static int createRandomNumber(List<String> nicknameList){
        Random random = new Random();
        int nicknameListSize = nicknameList.size();
        return random.nextInt(nicknameListSize);
    }
}

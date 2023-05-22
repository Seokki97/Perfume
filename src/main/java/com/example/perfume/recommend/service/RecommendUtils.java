package com.example.perfume.recommend.service;

import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.recommend.exception.RecommendNotFoundException;

import java.util.List;
import java.util.Random;

public class RecommendUtils {

    public static int createRandomPerfumeFromList(List<Perfume> surveyResultList) {
        Random random = new Random();
        int recommendedPerfumeSize = surveyResultList.size();

        if(recommendedPerfumeSize == 0){
           throw new RecommendNotFoundException();
        }
        return random.nextInt(recommendedPerfumeSize);
    }

    public static int createRandomNumber(List<String> nicknameList) {
        Random random = new Random();
        int nicknameListSize = nicknameList.size();
        return random.nextInt(nicknameListSize);
    }
}

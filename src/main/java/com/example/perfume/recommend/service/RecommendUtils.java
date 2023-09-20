package com.example.perfume.recommend.service;

import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.recommend.exception.RecommendNotFoundException;

import java.util.List;
import java.util.Random;

public class RecommendUtils {

    public static int createRandomPerfumeFromList(List<Perfume> recommendedPerfumeList) {
        Random random = new Random();
        int listSize = recommendedPerfumeList.size();

        if(listSize == 0){
           throw new RecommendNotFoundException();
        }
        return random.nextInt(listSize);
    }

    public static int createRandomNumber(List<String> nicknameList) {
        Random random = new Random();
        int nicknameListSize = nicknameList.size();
        return random.nextInt(nicknameListSize);
    }
}

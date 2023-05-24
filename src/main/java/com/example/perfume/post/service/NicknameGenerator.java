package com.example.perfume.post.service;

import com.example.perfume.post.domain.Nickname;
import com.example.perfume.recommend.service.RecommendUtils;
import org.springframework.stereotype.Service;


@Service
public class NicknameGenerator {

    private static int generateRandomAdjectiveNumber(Nickname nickname) {
        return RecommendUtils.createRandomNumber(nickname.getAdjectiveNickname());
    }

    private static int generateRandomNounNumber(Nickname nickname) {
        return RecommendUtils.createRandomNumber(nickname.getNounNickname());
    }

    public static String generateRandomNickname(Nickname nickname) {
        int adjectiveNumber = generateRandomAdjectiveNumber(nickname);
        int nounNumber = generateRandomNounNumber(nickname);

        return nickname.getAdjectiveNickname().get(adjectiveNumber) + " " +
                nickname.getNounNickname().get(nounNumber);
    }
}

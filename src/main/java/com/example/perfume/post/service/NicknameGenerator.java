package com.example.perfume.post.service;

import com.example.perfume.recommend.service.RecommendUtils;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
public class NicknameGenerator {

    private static final List<String> adjectiveNickname = Arrays.asList("가냘픈", "그렇고 그런", "가엾은", "참 이쁜", "미친 과학자인",
            "익명의", "거친",
            "검은", "게으른", "귀여운", "글미 최강의", "존잘인", "너 없이 못사는", "향수를 좋아하는", "바이레도와 잘어울리는", "웃음을 못참는", "공병주와 닮은",
            "준석이랑 데이트할", "찬욱이랑 뽀뽀할", "사랑하는", "그렇고 그런", "응큼한", "밥을 잘 사주는", "미친 개발자인", "행복한", "휘날리는", "깊은", "깎아지른", "깨끗한",
            "나쁜", "나은", "난데없는", "날랜", "날카로운", "낮은", "너그러운", "너른", "널따란", "넓은", "네모난", "노란", "높은", "누런", "눅은", "느닷없는",
            "느린", "늦은", "다른", "더러운", "더운", "덜된", "동그란", "돼먹잖은", "된", "둥그런", "둥근", "뒤늦은", "드문", "딱한", "때늦은", "뛰어난",
            "뜨거운", "막다른", "많은", "매운", "먼", "멋진", "메마른", "메스꺼운", "모난", "못난", "못된", "못생긴", "무거운", "무딘", "무른", "무서운",
            "미끄러운", "미운", "바람직한", "반가운", "밝은", "밤늦은", "보드라운", "보람찬", "부드러운", "부른", "붉은", "비싼", "빠른", "빨간", "뻘건",
            "시트러스 향이 나는", "비누향이 나는", "뿌연", "설레는", "우디향이 가득한", "바닐라향이 나는", "노래를 잘하는", "뛰어난 춤꾼인", "강한", "향수가 잘 어울리는",
            "쉬운", "스스러운", "슬픈", "시원찮은", "싫은", "싼", "쌀쌀맞은", "쏜살같은", "쓰디쓴", "쓰린", "쓴", "아니꼬운", "아닌", "아름다운", "아쉬운", "아픈",
            "안된");

    private static final List<String> nounNickname = Arrays.asList("남자", "프로도", "어피치", "루피", "이누야샤", "공병주", "박찬욱",
            "김준석",
            "쿠로다 류헤이", "켄지로");

    private static int generateRandomAdjectiveNumber() {
        return RecommendUtils.createRandomNumber(adjectiveNickname);
    }

    private static int generateRandomNounNumber() {
        return RecommendUtils.createRandomNumber(nounNickname);
    }

    public static String generateRandomNickname() {
        int adjectiveNumber = generateRandomAdjectiveNumber();
        int nounNumber = generateRandomNounNumber();

        return adjectiveNickname.get(adjectiveNumber) + " " +
                nounNickname.get(nounNumber);
    }
}

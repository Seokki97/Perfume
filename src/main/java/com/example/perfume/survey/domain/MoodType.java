package com.example.perfume.survey.domain;

import com.example.perfume.survey.exception.MoodNotFoundException;
import com.example.perfume.survey.exception.SeasonNotFoundException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum MoodType {
    //따뜻한
    WARMTH("따뜻한", "따뜻하고 온화한 분위기를 연출하고 싶군요. 이 향수를 사용하시면 추운 겨울에도 따뜻한 분위기를 낼 수 있을거에요. 또 겉으로는 시크하지만 내면은 따뜻한 무드를 가지고 있음을 잘 나타내줄 거에요."),
    //산뜻한
    NEAT("산뜻한", "산뜻한 느낌이 나는 향을 선호하시군요. 산뜻한 분위기의 향은 언제나 부담스럽지 않아요. 또한 뭔가 일이 잘 안풀리고, 속이 답답해서 기분전환을 해야 할 때에 사용해보세요."),
    //관능적인
    SENSUAL("관능적인", "관능적인 매력의 소유자이시군요. 이성과 함께있는 자리에서 사용하셔서 이성에게 어필해보세요. 이 향수와 함께라면 관능적인 섹시미로 이성을 유혹할 수 있을거에요."),
    //시크한
    CHIC("시크한", "파리지앵처럼 시크한 분위기를 연출하고 싶군요. 많은 사람들은 자신도 모르게 시크한 분위기에 끌린다 하지요. 이 향수와 함께 당신의 시크한 매력을 더욱 발산해 보세요."),
    //차분한
    TRANQUIL("차분한", "차분한 분위기를 좋아하시군요. 차분한 분위기인 만큼 언제 어디서나 누구와 만나든 호불호 없이 데일리로 사용하시기 좋을거에요."),
    //깨끗한
    CLEAN("깨끗한", "금방이라도 샤워를 하고 나온 듯한 깨끗한 향과 분위기를 좋아하시군요. 이 향수와 함께라면 투명하고 깨끗한 분위기를 연출하실 수 있을거에요."),
    //포근한
    COZY("포근한", "포근한 분위기를 좋아하시군요. 이 향수로 꽃들 사이로 살포시 내려앉은 햇살같은 포근한 분위기를 연출할 수 있을거에요. 멋들어지거나 화려하지는 않지만, 가만히 안기고 싶은 포근한 매력을 가지고 있어요."),
    //세련된
    REFINED("세련된", "세련된 도시적 느낌을 연출하고 싶군요. 중요한 사람을 만날 때 사용하신다면 보다더 고급스럽고 세련된 인상을 각인시켜줄거에요."),
    //상큼한
    FRESH("상큼한", "상큼 발랄한 매력의 소유자이시군요. 이 향수와 함께 순정만화의 주인공처럼 상큼한 분위기를 연출해 보세요. 묵직하고 분위기있어야 할 때보다 가볍고 편하게 있을 때 사용해보세요."),
    //달콤한
    SWEET("달콤한", "달콤한 향과 분위기를 좋아하시군요. 사랑스러운 분위기를 내고 싶을 때에도 사용해보세요. 당신이 달달한 향을 뿜어내면 상대방의 분위기도 좋게 바꿔줄 거에요."),
    //싱그러운
    REFRESHING("싱그러운", "예쁜 정원에 있는 듯한 싱그러운 분위기를 좋아하시군요. 생동감 있고 프레쉬한 경쾌함을 느끼고 싶을 때 사용해보세요. 또 힘이 없는 날에 사용하신다면 지친 심신에 큰 활력이 될거에요.");

    private final String mood;
    private final String message;

    MoodType(String mood, String message) {
        this.mood = mood;
        this.message = message;
    }

    public static String getMessage(Survey survey) {
        String expectedMood = survey.getMoodAnswer();

        MoodType moodType = Arrays.stream(MoodType.values())
                .filter(mood -> expectedMood.contains(mood.getMood())).findAny()
                .orElseThrow(MoodNotFoundException::new);
        return moodType.getMessage();
    }
}

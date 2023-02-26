package com.example.perfume.survey.domain;

import com.example.perfume.perfume.domain.Perfume;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity(name = "features")
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, length = 10)
    private String genderAnswer; //남녀

    @NotNull
    @Column(nullable = false, length = 10)
    private String scentAnswer; //향

    @NotNull
    @Column(nullable = false, length = 10)
    private String moodAnswer; //무드

    @NotNull
    @Column(nullable = false, length = 10)
    private String seasonAnswer; //계절

    @NotNull
    @Column(nullable = false, length = 10)
    private String styleAnswer; //스타일

    @Builder
    public Survey(Long id, String genderAnswer, String scentAnswer, String moodAnswer, String seasonAnswer, String styleAnswer) {
        this.id = id;
        this.genderAnswer = genderAnswer;
        this.scentAnswer = scentAnswer;
        this.moodAnswer = moodAnswer;
        this.seasonAnswer = seasonAnswer;
        this.styleAnswer = styleAnswer;
    }

}

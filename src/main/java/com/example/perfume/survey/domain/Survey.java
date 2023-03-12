package com.example.perfume.survey.domain;

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
    private String genderAnswer;

    @NotNull
    @Column(nullable = false, length = 10)
    private String scentAnswer;

    @NotNull
    @Column(nullable = false, length = 10)
    private String moodAnswer;

    @NotNull
    @Column(nullable = false, length = 10)
    private String seasonAnswer;

    @NotNull
    @Column(nullable = false, length = 10)
    private String styleAnswer;

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

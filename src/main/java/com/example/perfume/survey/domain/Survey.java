package com.example.perfume.survey.domain;

import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.perfume.dto.perfumeDto.PerfumeRequestDto;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity(name = "features")
@Table(name = "features")
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, length = 10)
    private String firstAnswerOfSurvey; //남녀

    @NotNull
    @Column(nullable = false, length = 10)
    private String secondAnswerOfSurvey; //향

    @NotNull
    @Column(nullable = false, length = 10)
    private String thirdAnswerOfSurvey; //무드

    @NotNull
    @Column(nullable = false, length = 10)
    private String fourthAnswerOfSurvey; //계절

    @NotNull
    @Column(nullable = false, length = 10)
    private String fifthAnswerOfSurvey; //스타일

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "perfume_id")
    private Perfume perfume;

    @Builder
    public Survey(Long id, String firstAnswerOfSurvey, String secondAnswerOfSurvey, String thirdAnswerOfSurvey, String fourthAnswerOfSurvey, String fifthAnswerOfSurvey, Perfume perfume) {
        this.id = id;
        this.firstAnswerOfSurvey = firstAnswerOfSurvey;
        this.secondAnswerOfSurvey = secondAnswerOfSurvey;
        this.thirdAnswerOfSurvey = thirdAnswerOfSurvey;
        this.fourthAnswerOfSurvey = fourthAnswerOfSurvey;
        this.fifthAnswerOfSurvey = fifthAnswerOfSurvey;
        this.perfume = perfume;

    }
}

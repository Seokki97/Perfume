package com.example.perfume.perfume.domain;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity(name = "feature")
@Table(name = "feature")
public class Feature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, length = 5)
    private String answerOfSurvey;

    @ManyToOne
    @JoinColumn(name = "perfume_id")
    public Perfume perfume;

  /*  @Column(nullable = false, length = 5)
    public Long perfumeId;*/

    @Builder
    public Feature(Long id, String answerOfSurvey) {
        this.id = id;
        this.answerOfSurvey = answerOfSurvey;
        //this.perfumeId = perfume.deliverPerfumeId();
    }
}

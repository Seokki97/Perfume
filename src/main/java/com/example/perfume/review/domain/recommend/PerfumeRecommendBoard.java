package com.example.perfume.review.domain.recommend;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "perfume_recommend_board")
public class PerfumeRecommendBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "perfume_recommend_board_id", nullable = false)
    private Long perfume_recommend_board_id;
}

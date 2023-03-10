package com.example.perfume.perfume.domain;

import com.example.perfume.member.domain.Recommendation;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity(name = "perfume")
public class Perfume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(nullable = false, length = 40)
    private String perfumeName;

    @NotNull
    @Column(nullable = false, length = 20)
    private String brandName;

    @NotNull
    @Column(nullable = false, length = 100)
    private String perfumeFeature;

    @NotNull
    @Column(nullable = false)
    private String perfumeImageUrl;

    @Builder
    public Perfume(Long id, String perfumeName, String brandName, String perfumeFeature, String perfumeImageUrl) {
        this.id = id;
        this.perfumeName = perfumeName;
        this.brandName = brandName;
        this.perfumeFeature = perfumeFeature;
        this.perfumeImageUrl = perfumeImageUrl;
    }

    public Long deliverPerfumeId() {
        return id;
    }

}

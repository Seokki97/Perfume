package com.example.perfume.perfume.domain;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity(name = "perfume")
public class Perfume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "perfume_id")
    private Long perfumeId;

    @NotNull
    @Column(nullable = false, length = 40)
    private String perfumeName;

    @NotNull
    @Column(nullable = false, length = 30)
    private String brandName;

    @NotNull
    @Column(nullable = false, length = 100)
    private String perfumeFeature;

    @NotNull
    @Column(nullable = false, length = 500)
    private String perfumeImageUrl;

    @NotNull
    @Column(nullable = false, length = 20)
    private String maintenance;

    @Builder
    public Perfume(Long perfumeId, String perfumeName, String brandName, String perfumeFeature, String perfumeImageUrl, String maintenance) {
        this.perfumeId = perfumeId;
        this.perfumeName = perfumeName;
        this.brandName = brandName;
        this.perfumeFeature = perfumeFeature;
        this.perfumeImageUrl = perfumeImageUrl;
        this.maintenance = maintenance;
    }
}

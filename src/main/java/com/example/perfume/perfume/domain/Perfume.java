package com.example.perfume.perfume.domain;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "perfume")
@Table(name = "perfume")
@Getter
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

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Perfume perfume1 = (Perfume) o;
        return Objects.equals(perfumeName,perfume1.perfumeName);
    }

    @Override
    public int hashCode(){
        return Objects.hash(perfumeName);
    }
}

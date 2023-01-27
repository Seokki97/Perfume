package com.example.perfume.perfume.domain;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity(name = "perfume")
@Table(name = "perfume")
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
    @Column(nullable = false, length = 60)
    private String perfumeFeature;

    @Builder
    public Perfume(Long id, String perfumeName, String brandName, String perfumeFeature){
        this.id= id;
        this.perfumeName = perfumeName;
        this.brandName = brandName;
        this.perfumeFeature = perfumeFeature;
    }
    //특징 : a , b , c , d
}
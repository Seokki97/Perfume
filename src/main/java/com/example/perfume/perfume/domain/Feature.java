package com.example.perfume.perfume.domain;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "perfume")
@Data
@Table(name = "feature")
public class Feature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, length = 5)
    private String firstFeature;

    @NotNull
    @Column(nullable = false, length = 5)
    private String secondFeature;

    @NotNull
    @Column(nullable = false, length = 5)
    private String thirdFeature;

    @NotNull
    @Column(nullable = false, length = 5)
    private String fourthFeature;

}
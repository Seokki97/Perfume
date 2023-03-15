package com.example.perfume.feedback.domain;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "feedback")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(nullable = false)
    private String serviceName;

    @Column(nullable = false, length = 1000)
    private String comment;

    @Builder
    public Feedback(Long id, String serviceName, String comment){
        this.id = id;
        this.serviceName = serviceName;
        this.comment = comment;
    }


}

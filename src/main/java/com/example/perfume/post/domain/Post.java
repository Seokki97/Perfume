package com.example.perfume.post.domain;

import com.example.perfume.post.service.NicknameGenerator;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", nullable = false)
    private Long postId;

    @NotNull
    private String visitor;

    @NotNull
    private String content;

    public Post(String content) {
        this.visitor = NicknameGenerator.generateRandomNickname();
        this.content = content;
    }
}

package com.example.perfume.post.domain;

import com.example.perfume.post.service.NicknameGenerator;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, length = 50)
    private String visitor;

    @NotNull
    @Column(nullable = false, length = 500)
    private String content;

    @Builder
    public Post(Long id, Nickname nickname, String content) {
        this.id = id;
        this.visitor = NicknameGenerator.generateRandomNickname(nickname);
        this.content = content;
    }
}

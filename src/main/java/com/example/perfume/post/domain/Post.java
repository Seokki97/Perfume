package com.example.perfume.post.domain;

import com.example.perfume.post.service.NicknameGenerator;
import com.sun.istack.NotNull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
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

    public Post(Nickname nickname, String content) {
        this.visitor = NicknameGenerator.generateRandomNickname(nickname);
        this.content = content;
    }

    public Post(Long id) {
        this.id = id;
    }
}

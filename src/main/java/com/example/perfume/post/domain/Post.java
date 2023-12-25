package com.example.perfume.post.domain;

import com.example.perfume.post.service.NicknameGenerator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    private Long id;

    private String visitor;

    private String content;

    public Post(Nickname nickname, String content) {
        this.visitor = NicknameGenerator.generateRandomNickname(nickname);
        this.content = content;
    }
}

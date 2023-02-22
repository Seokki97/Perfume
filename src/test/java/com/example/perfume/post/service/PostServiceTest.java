package com.example.perfume.post.service;


import com.example.perfume.post.domain.Post;
import com.example.perfume.post.dto.PostRequestDto;
import com.example.perfume.post.dto.PostResponseDto;
import com.example.perfume.post.repository.PostRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
public class PostServiceTest {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @DisplayName("작성한 게시물이 DB에 저장된다.")
    @Test
    void savePost() {
        long id = 2l;

        PostRequestDto actual = new PostRequestDto(id, "익명의 준석킴", "테스트해주세용");

        postService.savePost(actual);

        Post expected = postRepository.findById(id).get();

        assertAll(
                () -> assertThat(actual).usingRecursiveComparison()
                        .ignoringFields("id")
                        .isEqualTo(expected)
        );
    }

    @DisplayName("하나의 게시물을 단일 조회한다.")
    @Test
    void findPost() {
        long id = 1l;
        Post actual = Post.builder().id(id)
                .content("작품 너무 예뻐용 ㅋ")
                .visitor("익명의 코끼리")
                .build();

        PostResponseDto postResponseDto = PostResponseDto.builder()
                .id(id)
                .content("작품 너무 예뻐용 ㅋ")
                .visitor("익명의 코끼리")
                .build();
        Post expected = postService.showOnePost(postResponseDto.getId());

        assertAll(
                () -> assertThat(actual).usingRecursiveComparison()
                        .ignoringFields("id")
                        .isEqualTo(expected)
        );
    }

    @DisplayName("전체 게시물 리스트를 조회한다.")
    @Test
    void showAllPost() {

        List<Post> actual = postService.showAllPost();

        Post expected1 = Post.builder()
                .id(1l)
                .content("작품 너무 예뻐용 ㅋ")
                .visitor("익명의 코끼리")
                .build();
        Post expected2 = Post.builder().id(2l)
                .visitor("익명의 준석킴")
                .content("테스트해주세용")
                .build();

        List<Post> expected = new ArrayList<>();
        expected.add(expected1);
        expected.add(expected2);

        assertAll(
                () -> assertThat(actual).usingRecursiveComparison()
                        .ignoringFields("id")
                        .isEqualTo(expected)
        );

    }
}

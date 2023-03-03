package com.example.perfume.post.service;


import com.example.perfume.post.domain.Post;
import com.example.perfume.post.dto.PostRequestDto;
import com.example.perfume.post.dto.PostResponseDto;
import com.example.perfume.post.exception.PostNotFoundException;
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
        long id = 1l;

        PostRequestDto actual = new PostRequestDto(id, "익명의 준석킴", "테스트해주세용");

        postService.savePost(actual);

        Post expected = postRepository.findById(id).orElseThrow(PostNotFoundException::new);

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

        PostRequestDto postRequestDto = PostRequestDto.builder()
                .id(id)
                .content("작품 너무 예뻐용 ㅋ")
                .visitor("익명의 코끼리")
                .build();
        postService.savePost(postRequestDto);
        Post expected = postService.showOnePost(postRequestDto.getId());

        assertAll(
                () -> assertThat(actual).usingRecursiveComparison()
                        .ignoringFields("id")
                        .isEqualTo(expected)
        );
    }

    @DisplayName("전체 게시물 리스트를 조회한다.")
    @Test
    void showAllPost() {


        PostRequestDto expected1 = PostRequestDto.builder()
                .id(1l)
                .content("작품 너무 예뻐용 ㅋ")
                .visitor("익명의 코끼리")
                .build();
        PostRequestDto expected2 = PostRequestDto.builder().id(2l)
                .visitor("익명의 준석킴")
                .content("테스트해주세용")
                .build();

        postService.savePost(expected1);
        postService.savePost(expected2);
        List<Post> actual = postService.showAllPost();

        assertAll(
                () -> assertThat(actual.get(0)).usingRecursiveComparison()
                        .ignoringFields("id")
                        .isEqualTo(expected1),

                () -> assertThat(actual.get(1)).usingRecursiveComparison()
                        .ignoringFields("id")
                        .isEqualTo(expected2)
        );

    }
}

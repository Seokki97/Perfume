package com.example.perfume.post.controller;

import com.example.perfume.post.controller.docs.PostControllerDocs;
import com.example.perfume.post.dto.PostRequestDto;
import com.example.perfume.post.domain.Post;
import com.example.perfume.post.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/post")
public class PostController implements PostControllerDocs {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/save-post")
    public ResponseEntity<Void> savePost(@RequestBody final PostRequestDto postRequestDto) {
        log.info("방명록 작성하기 요청");
        postService.savePost(postRequestDto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/show-post/{id}")
    public ResponseEntity<Post> showOnePost(@PathVariable final Long id) {
        log.info("게시글 넘버 :{} 조회하기", id);
        return ResponseEntity.ok(postService.showOnePost(id));
    }

    @GetMapping("/show-all-post")
    public ResponseEntity<List<Post>> showAllPost() {
        log.info("모든 게시글 조회 요청");
        return ResponseEntity.ok(postService.showAllPost());
    }
}

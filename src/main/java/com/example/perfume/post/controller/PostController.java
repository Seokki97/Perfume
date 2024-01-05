package com.example.perfume.post.controller;

import com.example.perfume.post.controller.docs.PostControllerDocs;
import com.example.perfume.post.dto.PostRequestDto;
import com.example.perfume.post.dto.PostResponseDto;
import com.example.perfume.post.service.PostService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        postService.writePost(postRequestDto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/show-post/{id}")
    public ResponseEntity<PostResponseDto> showOnePost(@PathVariable final Long id) {
        return ResponseEntity.ok(postService.showOnePost(id));
    }

    @GetMapping("/show-all-post")
    public ResponseEntity<List<PostResponseDto>> showAllPost() {
        return ResponseEntity.ok(postService.showAllPost());
    }

    @GetMapping("/show-post")
    public ResponseEntity<List<PostResponseDto>> showByContent(@RequestParam(name = "content") String content) {
        return ResponseEntity.ok(postService.findByContent(content));
    }
}

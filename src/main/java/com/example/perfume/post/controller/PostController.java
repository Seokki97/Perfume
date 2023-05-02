package com.example.perfume.post.controller;

import com.example.perfume.post.controller.docs.PostControllerDocs;
import com.example.perfume.post.dto.PostRequestDto;
import com.example.perfume.post.domain.Post;
import com.example.perfume.post.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController implements PostControllerDocs {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/save-post")
    public ResponseEntity<Void> savePost(@RequestBody PostRequestDto postRequestDto) {
        postService.savePost(postRequestDto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/show-post/{id}")
    public ResponseEntity<Post> showOnePost(@PathVariable Long id) {
        return ResponseEntity.ok(postService.showOnePost(id));
    }

    @GetMapping("/show-all-post")
    public ResponseEntity<List<Post>> showAllPost() {
        return ResponseEntity.ok(postService.showAllPost());
    }
}

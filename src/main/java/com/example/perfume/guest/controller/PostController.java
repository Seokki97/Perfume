package com.example.perfume.guest.controller;

import com.example.perfume.guest.domain.Post;
import com.example.perfume.guest.dto.PostRequestDto;
import com.example.perfume.guest.dto.PostResponseDto;
import com.example.perfume.guest.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService){
        this.postService = postService;
    }

    @PostMapping("save-post")
    public void savePost(@RequestBody PostRequestDto postRequestDto){
        postService.savePost(postRequestDto);
    }

    @GetMapping("show-post/{id}")
    public ResponseEntity<Post> showOnePost(@PathVariable Long id){
        return ResponseEntity.ok(postService.showOnePost(id));
    }

    @GetMapping("show-all-post")
    public ResponseEntity<List<Post>> showOnePost(){
        return ResponseEntity.ok(postService.showAllPost());
    }
}

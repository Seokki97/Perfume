package com.example.perfume.guest.service;

import com.example.perfume.guest.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private final PostRepository postRepository;


    public PostService(PostRepository postRepository){
        this.postRepository = postRepository;
    }
}

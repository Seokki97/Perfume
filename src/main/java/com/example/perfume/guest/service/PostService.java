package com.example.perfume.guest.service;

import com.example.perfume.guest.domain.Post;
import com.example.perfume.guest.dto.PostRequestDto;
import com.example.perfume.guest.dto.PostResponseDto;
import com.example.perfume.guest.exception.PostNotFoundException;
import com.example.perfume.guest.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;


    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void savePost(PostRequestDto postRequestDto) {
        Post post = new Post(postRequestDto.getId(), postRequestDto.getVisitor(), postRequestDto.getContent());

        postRepository.save(post);
    }

    public Post showOnePost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(PostNotFoundException::new);

        return post;
    }

    public List<Post> showAllPost() {
        if (!isPostExist()) {
            throw new PostNotFoundException();
        }
        return postRepository.findAll();
    }

    public boolean isPostExist() {
        if (postRepository.count() == 0) {
            return false;
        }
        return true;
    }
}

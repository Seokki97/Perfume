package com.example.perfume.survey.domain.post.service;

import com.example.perfume.survey.domain.post.domain.Post;
import com.example.perfume.survey.domain.post.dto.PostRequestDto;
import com.example.perfume.survey.domain.post.exception.PostNotFoundException;
import com.example.perfume.survey.domain.post.repository.PostRepository;
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
        return postRepository.findById(id)
                .orElseThrow(PostNotFoundException::new);
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

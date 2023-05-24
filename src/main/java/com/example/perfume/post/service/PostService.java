package com.example.perfume.post.service;

import com.example.perfume.post.domain.Nickname;
import com.example.perfume.post.domain.Post;
import com.example.perfume.post.dto.PostRequestDto;
import com.example.perfume.post.exception.PostNotFoundException;
import com.example.perfume.post.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void savePost(PostRequestDto postRequestDto) {
        Nickname nickname = new Nickname();
        Post post = Post.builder()
                .content(postRequestDto.getContent())
                .nickname(nickname)
                .build();
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

    private boolean isPostExist() {
        if (postRepository.count() == 0) {
            return false;
        }
        return true;
    }
}

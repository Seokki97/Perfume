package com.example.perfume.post.service;

import com.example.perfume.post.domain.Post;
import com.example.perfume.post.dto.PostRequestDto;
import com.example.perfume.post.dto.PostResponseDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private final JdbcTemplate jdbcTemplate;

    public PostService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void writePost(PostRequestDto postRequestDto) {
        String query = "INSERT INTO post (visitor, content) VALUES (?, ?)";
        Post post = new Post(postRequestDto.getContent());
        jdbcTemplate.update(query, post.getVisitor(), post.getContent());
    }

    public PostResponseDto showOnePost(Long postId) {
        String query = "SELECT * FROM post WHERE post_id = ?";
        return jdbcTemplate.queryForObject(query, new PostRowMapper(), postId);
    }
}

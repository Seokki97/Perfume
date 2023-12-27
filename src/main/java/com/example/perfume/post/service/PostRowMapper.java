package com.example.perfume.post.service;

import com.example.perfume.post.dto.PostResponseDto;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public class PostRowMapper implements RowMapper<PostResponseDto> {

    @Override
    public PostResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        PostResponseDto postResponseDto = new PostResponseDto(rs.getLong("post_id"),
                rs.getString("visitor"), rs.getString("content"));
        return postResponseDto;
    }
}

package com.example.perfume.post.controller.docs;

import com.example.perfume.post.domain.Post;
import com.example.perfume.post.dto.PostRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "방명록 API")
public interface PostControllerDocs {
    @Operation(summary = "방명록 작성")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "게시글 작성 성공"),
            @ApiResponse(responseCode = "404", description = "게시글 작성 실패")
    })
    ResponseEntity<Void> savePost(@Parameter(name = "제목, 내용") @RequestBody PostRequestDto postRequestDto);

    @Operation(summary = "방명록 단일 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회"),
            @ApiResponse(responseCode = "404", description = "해당 게시물 없음")
    })
    ResponseEntity<Post> showOnePost(@Parameter(description = "게시글 id") @PathVariable Long id);

    @Operation(summary = "방명록 전체 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회"),
            @ApiResponse(responseCode = "200", description = "게시글 없음")
    })
    ResponseEntity<List<Post>> showAllPost();
}

package com.example.perfume.post.controller.docs;

import com.example.perfume.common.APICommonResponse;
import com.example.perfume.post.dto.PostRequestDto;
import com.example.perfume.post.dto.PostResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "방명록 API")
public interface PostControllerDocs {
    @Operation(summary = "방명록 작성")
    @APICommonResponse
    ResponseEntity<Void> savePost(@Parameter(name = "제목, 내용") @RequestBody PostRequestDto postRequestDto);

    @Operation(summary = "방명록 단일 조회")
    @APICommonResponse
    ResponseEntity<PostResponseDto> showOnePost(@Parameter(description = "게시글 id") @PathVariable Long id);

    @Operation(summary = "방명록 전체 조회")
    @APICommonResponse
    ResponseEntity<List<PostResponseDto>> showAllPost();

    @Operation(summary = "게시글 내용으로 찾기")
    @APICommonResponse
    ResponseEntity<List<PostResponseDto>> showByContent(
            @Parameter(description = "게시글 내용") @RequestParam(name = "content") String content);
}

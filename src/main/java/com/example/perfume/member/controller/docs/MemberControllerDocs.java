package com.example.perfume.member.controller.docs;

import com.example.perfume.member.dto.loginDto.SecessionRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "회원 탈퇴 Api")
public interface MemberControllerDocs {
    @Operation(summary = "회원 삭제")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원 삭제 성공",headers = @Header(name = "Authorization", description = "Access Token")),
            @ApiResponse(responseCode = "401", description = "인증되지 않는 접근")
    })
    ResponseEntity<Void> deleteMember(@Parameter(name = "memberId, RefreshToken") @RequestBody SecessionRequest secessionRequest);
}

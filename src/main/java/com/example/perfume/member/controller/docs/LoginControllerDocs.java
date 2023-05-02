package com.example.perfume.member.controller.docs;

import com.example.perfume.member.dto.loginDto.LoginResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

@Tag(name = "로그인 API")
public interface LoginControllerDocs {

    @Operation(summary = "요청 시 토큰 유효한지 검증")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "검증 성공", headers = @Header(name = "Authorization", description = "Access Token")),
            @ApiResponse(responseCode = "401", description = "실패")
    })
    ResponseEntity<LoginResponse> resolveToken(@Parameter(name = "Access Token") HttpServletRequest httpServletRequest);

    @Operation(summary = "AccessToken 만료 시 RefreshToken 요청 - 검증")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "검증 성공", headers = @Header(name = "X-REFRESH-TOKEN", description = "Refresh Token")),
            @ApiResponse(responseCode = "401", description = "실패")
    })
    ResponseEntity<LoginResponse> regenerateEntity(@Parameter(name = "Refresh Token") HttpServletRequest httpServletRequest);
}

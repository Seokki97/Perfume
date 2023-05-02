package com.example.perfume.oauth.controller.docs;

import com.example.perfume.member.dto.loginDto.LoginResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Tag(name = "카카오 Oauth Login Api")
public interface OauthControllerDocs {

    @Operation(summary = "카카오 회원정보 받아서 회원가입&로그인 하기")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그인 성공", headers = @Header(name = "code", description = "인가코드")),
            @ApiResponse(responseCode = "401", description = "로그인 실패")
    })
    ResponseEntity<LoginResponse> signUp(@Parameter(name = "code", description = "인가 코드") @RequestParam String code, HttpSession httpSession);
}

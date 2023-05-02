package com.example.perfume.member.controller.docs;

import com.example.perfume.member.dto.logoutDto.LogoutRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

@Tag(name = "로그아웃 API")
public interface LogoutControllerDocs {

    @Operation(summary = "로그아웃")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그아웃 성공", headers = @Header(name = "Authorization", description = "Access Token")),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 접근")
    })
    ResponseEntity<Void> permitLogout(@Parameter(name = "memberId") @RequestBody LogoutRequestDto logoutRequestDto,@Parameter(name = "Access Token") HttpServletRequest httpServletRequest);
}

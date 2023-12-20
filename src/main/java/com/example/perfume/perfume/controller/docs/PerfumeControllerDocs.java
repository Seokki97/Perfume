package com.example.perfume.perfume.controller.docs;

import com.example.perfume.common.APICommonResponse;
import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.perfume.dto.PerfumeRequestDto;
import com.example.perfume.perfume.dto.PerfumeResponseDto;
import com.example.perfume.survey.dto.featureDto.FeatureResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;

@Tag(name = "향수 데이터 컨트롤러")
public interface PerfumeControllerDocs {

    @Operation(summary = "이름으로 향수 찾기")
    @APICommonResponse
    ResponseEntity<List<Perfume>> findByPerfumeName(
            @RequestBody(description = " 향수 이름") @Parameter(description = "향수 이름") PerfumeRequestDto perfumeRequestDto);

    @Operation(summary = "브랜드로 향수 찾기")
    @APICommonResponse
    ResponseEntity<List<Perfume>> findByBrandName(
            @Parameter(description = "브랜드 이름") PerfumeRequestDto perfumeRequestDto);

    @Operation(summary = "모든 향수 데이터 조회")
    @APICommonResponse
    ResponseEntity<List<Perfume>> showAllData();

    @Operation(summary = "향수 id로 조회")
    @APICommonResponse
    ResponseEntity<FeatureResponseDto> showPerfumeDetails(@Parameter(description = "향수 id") Long id);

    @Operation(summary = "향수 image 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공", headers = @Header(name = "Authorization", description = "Access Token")),
            @ApiResponse(responseCode = "401", description = "유효하지 않은 사용자 접근")
    })
    ResponseEntity<PerfumeResponseDto> showPerfumeImage(@Parameter(description = "향수 이름") String perfumeName);
}

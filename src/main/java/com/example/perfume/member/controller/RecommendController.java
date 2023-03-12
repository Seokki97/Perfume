package com.example.perfume.member.controller;

import com.example.perfume.member.domain.Recommendation;
import com.example.perfume.member.dto.recommendDto.RecommendRequestDto;
import com.example.perfume.member.dto.recommendDto.RecommendResponseDto;
import com.example.perfume.member.service.recommend.RecommendationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
public class RecommendController {
    private final RecommendationService recommendationService;

    public RecommendController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;

    }

    @PostMapping("/recommend/{id}")
    public Recommendation recommendPerfume(@PathVariable("id") Long id, @RequestBody RecommendRequestDto recommendRequestDto) {
       return recommendationService.recommendByOtherGuest(id, recommendRequestDto);
    }

    @GetMapping("/show-recommended-perfume/{id}")
    public ResponseEntity<RecommendResponseDto> showRecommendedPerfume(@PathVariable("id") Long id) {
        return ResponseEntity.ok(recommendationService.showRecommendedPerfume(id));
    }

}

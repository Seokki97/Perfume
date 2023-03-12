package com.example.perfume.recommend.controller;

import com.example.perfume.recommend.domain.Recommendation;
import com.example.perfume.recommend.dto.RecommendRequestDto;
import com.example.perfume.recommend.dto.RecommendResponseDto;
import com.example.perfume.recommend.service.RecommendationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

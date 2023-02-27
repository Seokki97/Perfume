package com.example.perfume.member.controller;

import com.example.perfume.member.domain.Recommendation;
import com.example.perfume.member.dto.recommendDto.RecommendRequestDto;
import com.example.perfume.member.service.RecommendationService;
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
    public void recommendPerfume(@PathVariable("id") Long id, @RequestBody RecommendRequestDto recommendRequestDto) {
        recommendationService.recommendByOtherGuest(id, recommendRequestDto);

    }

    @GetMapping("/show-recommended-perfume")
    public ResponseEntity<List<Recommendation>> showRecommendedPerfume() {
        return ResponseEntity.ok(recommendationService.showRecommendedPerfume());
    }
}

package com.example.perfume.recommend.controller;

import com.example.perfume.log.LoggerUtil;
import com.example.perfume.member.service.LogoutService;
import com.example.perfume.member.service.jwt.LoginCheck;
import com.example.perfume.recommend.domain.Recommendation;
import com.example.perfume.recommend.dto.RecommendRequestDto;
import com.example.perfume.recommend.dto.RecommendResponseDto;
import com.example.perfume.recommend.service.RecommendationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/member")
public class RecommendController {
    private final RecommendationService recommendationService;
    private final LogoutService logoutService;

    public RecommendController(RecommendationService recommendationService, LogoutService logoutService) {
        this.recommendationService = recommendationService;
        this.logoutService = logoutService;
    }

    @PostMapping("/recommend/{id}")
    public Recommendation recommendPerfume(@PathVariable("id") Long id, @RequestBody RecommendRequestDto recommendRequestDto) {
        return recommendationService.recommendByOtherGuest(id, recommendRequestDto);
    }

    @LoginCheck
    @GetMapping("/show-recommended-perfume/{id}")
    public ResponseEntity<RecommendResponseDto> showRecommendedPerfume(@PathVariable("id") Long id, HttpServletRequest httpServletRequest) {
        LoggerUtil.countThirdApi("타인이 보는 나의 향수 (세번째 기능) API 호출 횟수 :");
        logoutService.isUserAlreadyLogout(httpServletRequest.getHeader("Authorization"));
        return ResponseEntity.ok(recommendationService.showRecommendedPerfume(id));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteRecommendData() {
        recommendationService.deleteRecommendedData();
        return ResponseEntity.noContent().build();
    }

}

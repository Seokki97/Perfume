package com.example.perfume.recommend.controller;

import com.example.perfume.member.service.LogoutService;
import com.example.perfume.member.service.jwt.LoginCheck;
import com.example.perfume.recommend.controller.docs.RecommendControllerDocs;
import com.example.perfume.recommend.domain.Recommendation;
import com.example.perfume.recommend.dto.RecommendRequestDto;
import com.example.perfume.recommend.dto.RecommendResponseDto;
import com.example.perfume.recommend.service.RecommendationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/member")
public class RecommendController implements RecommendControllerDocs {

    private final RecommendationService recommendationService;
    private final LogoutService logoutService;

    public RecommendController(RecommendationService recommendationService, LogoutService logoutService) {
        this.recommendationService = recommendationService;
        this.logoutService = logoutService;
    }

    @PostMapping("/recommend/{id}")
    public ResponseEntity<Recommendation> recommendPerfume(@PathVariable("id") Long id, @RequestBody RecommendRequestDto recommendRequestDto) {
        log.info("memberId : {}에게 향수를 추천해주는 요청",id);
        return ResponseEntity.ok(recommendationService.recommendByOtherGuest(id, recommendRequestDto));
    }

    @LoginCheck
    @GetMapping("/show-recommended-perfume/{id}")
    public ResponseEntity<RecommendResponseDto> showRecommendedPerfume(@PathVariable("id") Long id, HttpServletRequest httpServletRequest) {
        log.info("memberId : {}에게 추천된 향수를 조회하기", id);
        logoutService.isUserAlreadyLogout(httpServletRequest.getHeader("Authorization"));
        return ResponseEntity.ok(recommendationService.showRecommendedPerfume(id));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteRecommendData() {
        recommendationService.deleteRecommendedData();
        return ResponseEntity.noContent().build();
    }

}

package com.example.perfume.recommend.controller;

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


    public RecommendController(RecommendationService recommendationService,LogoutService logoutService) {
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
        logoutService.isUserLogout(httpServletRequest.getHeader("Authorization"));
        return ResponseEntity.ok(recommendationService.showRecommendedPerfume(id));
    }

    @DeleteMapping("/delete")
    public void deleteRecommendData(){
        recommendationService.deleteRecommendedData();
    }

}

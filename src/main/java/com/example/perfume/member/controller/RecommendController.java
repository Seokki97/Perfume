package com.example.perfume.member.controller;

import com.example.perfume.member.dto.memberDto.MemberRequestDto;
import com.example.perfume.member.service.RecommendService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class RecommendController {
    private final RecommendService recommendService;

    public RecommendController(RecommendService recommendService) {
        this.recommendService = recommendService;
    }

    @PostMapping("/recommend/{id}")
    public void recommendPerfume(@PathVariable("id") MemberRequestDto memberRequestDto) {

    }
}

package com.example.perfume.member.controller;

import com.example.perfume.member.controller.docs.MemberControllerDocs;
import com.example.perfume.member.dto.loginDto.SecessionRequest;
import com.example.perfume.member.service.MemberService;
import com.example.perfume.member.service.jwt.LoginCheck;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController implements MemberControllerDocs {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @LoginCheck
    @PostMapping("/delete")
    public ResponseEntity<Void> deleteMember(@RequestBody SecessionRequest secessionRequest) {
        memberService.deleteMemberId(secessionRequest);
        return ResponseEntity.noContent().build();
    }
}

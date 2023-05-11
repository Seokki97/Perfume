package com.example.perfume.member.controller;

import com.example.perfume.member.controller.docs.MemberControllerDocs;
import com.example.perfume.member.dto.loginDto.SecessionRequest;
import com.example.perfume.member.service.MemberService;
import com.example.perfume.member.service.jwt.LoginCheck;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
public class MemberController implements MemberControllerDocs {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @LoginCheck
    @PostMapping("/delete")
    public ResponseEntity<Void> deleteMember(@RequestBody final SecessionRequest secessionRequest) {
        memberService.deleteMemberId(secessionRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete-all")
    public ResponseEntity<Void> deleteAllMember() {
        memberService.deleteAllMember();
        return ResponseEntity.noContent().build();
    }
}

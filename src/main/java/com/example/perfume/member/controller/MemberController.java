package com.example.perfume.member.controller;

import com.example.perfume.member.controller.docs.MemberControllerDocs;
import com.example.perfume.member.domain.Member;
import com.example.perfume.member.dto.loginDto.SecessionRequest;
import com.example.perfume.member.dto.memberDto.MemberRequestDto;
import com.example.perfume.member.service.MemberService;
import com.example.perfume.member.service.jwt.LoginCheck;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api//member")
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

    @DeleteMapping("/delete-select-member/{id}")
    public ResponseEntity<Void> deleteSelectedMember(@PathVariable("id") Long memberId) {
        memberService.deleteMember(memberId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("save")
    public ResponseEntity<Void> saveMember(@RequestBody final MemberRequestDto memberRequestDto) {
        Member member = Member.builder()
                .memberId(memberRequestDto.getMemberId())
                .kakaoId(memberRequestDto.getKakaoId())
                .email(memberRequestDto.getEmail())
                .nickname(memberRequestDto.getNickname())
                .thumbnailImage(memberRequestDto.getThumbnailImage())
                .build();
        memberService.saveMemberProfile(member);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/find/{memberId}")
    public ResponseEntity<Member> findMember(@PathVariable("memberId") Long memberId) {
        return ResponseEntity.ok(memberService.findByMemberPk(memberId));
    }
}

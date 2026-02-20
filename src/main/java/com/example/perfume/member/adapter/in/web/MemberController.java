package com.example.perfume.member.adapter.in.web;

import com.example.perfume.member.application.port.in.MemberUseCase;
import com.example.perfume.member.controller.docs.MemberControllerDocs;
import com.example.perfume.member.dto.loginDto.SecessionRequest;
import com.example.perfume.member.dto.memberDto.MemberRequestDto;
import com.example.perfume.member.dto.memberDto.MemberResponseDto;
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
@RequestMapping("/api/member")
public class MemberController implements MemberControllerDocs {

    private final MemberUseCase memberUseCase;

    public MemberController(MemberUseCase memberUseCase) {
        this.memberUseCase = memberUseCase;
    }

    @LoginCheck
    @PostMapping("/delete")
    public ResponseEntity<Void> deleteMember(@RequestBody final SecessionRequest secessionRequest) {
        memberUseCase.deleteMemberId(secessionRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete-all")
    public ResponseEntity<Void> deleteAllMember() {
        memberUseCase.deleteAllMember();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete-select-member/{id}")
    public ResponseEntity<Void> deleteSelectedMember(@PathVariable("id") Long memberId) {
        memberUseCase.deleteMember(memberId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/save")
    public ResponseEntity<Void> saveMember(@RequestBody final MemberRequestDto memberRequestDto) {
        memberUseCase.saveMemberProfile(memberRequestDto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/find/{memberId}")
    public ResponseEntity<MemberResponseDto> findMember(@PathVariable("memberId") Long memberId) {
        return ResponseEntity.ok(MemberResponseDto.from(memberUseCase.findByMemberPk(memberId)));
    }
}

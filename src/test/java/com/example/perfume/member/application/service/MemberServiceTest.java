package com.example.perfume.member.application.service;

import com.example.perfume.member.application.port.out.MemberPersistencePort;
import com.example.perfume.member.domain.Member;
import com.example.perfume.member.dto.loginDto.SecessionRequest;
import com.example.perfume.member.dto.memberDto.MemberRequestDto;
import com.example.perfume.member.exception.TokenInvalidException;
import com.example.perfume.member.exception.UserNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    private MemberPersistencePort memberPersistencePort;

    @InjectMocks
    private MemberService memberService;

    @DisplayName("신규 회원은 저장된다")
    @Test
    void saveMemberProfile() {
        MemberRequestDto requestDto = MemberRequestDto.builder()
                .kakaoId(10L)
                .nickname("tester")
                .email("tester@perfume.com")
                .thumbnailImage("thumbnail")
                .build();

        when(memberPersistencePort.saveMember(any(Member.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Member saved = memberService.saveMemberProfile(requestDto);

        Assertions.assertEquals(10L, saved.getKakaoId());
        Assertions.assertEquals("tester", saved.getNickname());
    }

    @DisplayName("기존 카카오 회원이 있으면 재저장하지 않는다")
    @Test
    void registerIfAbsentWhenMemberExists() {
        Member existing = Member.create(1L, "exist", "exist@perfume.com", "thumb");
        MemberRequestDto requestDto = MemberRequestDto.builder()
                .kakaoId(1L)
                .nickname("new")
                .build();

        when(memberPersistencePort.findMemberByKakaoId(1L)).thenReturn(Optional.of(existing));

        Member result = memberService.registerIfAbsent(requestDto);

        Assertions.assertEquals(existing.getKakaoId(), result.getKakaoId());
        verify(memberPersistencePort, never()).saveMember(any(Member.class));
    }

    @DisplayName("탈퇴 시 리프레시 토큰이 없으면 예외가 발생한다")
    @Test
    void deleteMemberIdWithoutRefreshToken() {
        SecessionRequest request = new SecessionRequest(1L, "invalid-refresh");

        when(memberPersistencePort.deleteMemberById(1L)).thenReturn(1L);
        when(memberPersistencePort.deleteTokenByRefreshToken("invalid-refresh")).thenReturn(0L);

        Assertions.assertThrows(TokenInvalidException.class, () -> memberService.deleteMemberId(request));
    }

    @DisplayName("회원 삭제 대상이 없으면 예외가 발생한다")
    @Test
    void deleteMemberWithoutTarget() {
        when(memberPersistencePort.deleteMemberById(999L)).thenReturn(0L);

        Assertions.assertThrows(UserNotFoundException.class, () -> memberService.deleteMember(999L));
    }
}

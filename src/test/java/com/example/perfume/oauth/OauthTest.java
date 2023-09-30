package com.example.perfume.oauth;


import com.example.perfume.member.domain.Member;
import com.example.perfume.member.dto.memberDto.MemberRequestDto;
import com.example.perfume.member.service.MemberService;
import com.example.perfume.oauth.service.OauthService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class OauthTest {

    @Autowired
    private OauthService oauthService;

    @Autowired
    private MemberService memberService;

    @DisplayName("회원 정보를 DB에 저장시킨다.")
    @Test
    void saveMember(){
        MemberRequestDto memberRequestDto = MemberRequestDto.builder()
                .kakaoId(122342113l)
                .email("asadssk@naver.com")
                .nickname("서sa키")
                .thumbnailImage("dsadasad")
                .build();
        Member member = Member.builder()
                .memberId(2l)
                .email(memberRequestDto.getEmail())
                .nickname(memberRequestDto.getNickname())
                .memberId(memberRequestDto.getKakaoId())
                .build();
        oauthService.saveUserProfile(memberRequestDto);

       Member actual =  memberService.findByMemberPk(memberRequestDto.getKakaoId());
        Member expected = Member.builder()
                .memberId(member.getMemberId())
                .email(memberRequestDto.getEmail())
                .kakaoId(memberRequestDto.getKakaoId())
                .nickname(memberRequestDto.getNickname())
                .build();

        assertThat(actual.getMemberId()).usingRecursiveComparison().isEqualTo(expected.getMemberId());
    }


}

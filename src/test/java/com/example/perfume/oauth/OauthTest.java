package com.example.perfume.oauth;


import com.example.perfume.member.domain.Member;
import com.example.perfume.member.dto.memberDto.MemberRequestDto;
import com.example.perfume.member.service.MemberService;
import com.example.perfume.oauth.exception.EmailNotFoundException;
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

    @DisplayName("이메일 사용을 동의하지 않았을 시 예외가 발생한다.")
    @Test
    void isAgreeEmailCheck(){
        String email = null;

        assertThatThrownBy(() -> oauthService.isAgreeEmailUsing(email))
                .isInstanceOf(EmailNotFoundException.class).hasMessage("계정 이용 동의를 하지 않았습니다.");
    }

    @DisplayName("회원 정보를 DB에 저장시킨다.")
    @Test
    void saveMember(){
        MemberRequestDto memberRequestDto = MemberRequestDto.builder()
                .id(1l)
                .memberId(123l)
                .email("ask@naver.com")
                .nickname("서키")
                .build();
        oauthService.saveUserProfile(memberRequestDto);
       Member actual =  memberService.findByMemberPk(memberRequestDto.getMemberId());
        Member expected = Member.builder()
                .id(memberRequestDto.getId())
                .email(memberRequestDto.getEmail())
                .memberId(memberRequestDto.getMemberId())
                .nickname(memberRequestDto.getNickname())
                .build();

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

}

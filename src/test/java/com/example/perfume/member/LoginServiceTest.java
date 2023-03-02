package com.example.perfume.member;


import com.example.perfume.member.domain.Member;
import com.example.perfume.member.dto.memberDto.LoginResponse;
import com.example.perfume.member.repository.MemberRepository;
import com.example.perfume.member.service.LoginService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class LoginServiceTest {

    @Autowired
    private LoginService loginService;
    @Autowired
    private MemberRepository memberRepository;


    @DisplayName("응답값으로 사용자 정보를 받아온다")
    @Test
    void responseMember() {
        Member member = Member.builder()
                .id(1l)
                .memberId(12341l)
                .email("skaksdl@naver.com")
                .nickname("seokki97")
                .build();
        memberRepository.save(member);
        LoginResponse actual = loginService.generateToken(member.getMemberId());

        LoginResponse expected = LoginResponse.builder()
                .id(1l)
                .accessToken(actual.getAccessToken())
                .refreshToken(actual.getRefreshToken())
                .email("skaksdl@naver.com")
                .nickname("seokki97")
                .build();
        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }
}

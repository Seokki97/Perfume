package com.example.perfume.member;


import com.example.perfume.member.domain.Member;
import com.example.perfume.member.domain.Token;
import com.example.perfume.member.dto.loginDto.LoginResponse;
import com.example.perfume.member.repository.MemberRepository;
import com.example.perfume.member.repository.TokenRepository;
import com.example.perfume.member.service.LoginService;
import com.example.perfume.member.service.jwt.JwtProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class LoginServiceTest {

    @Autowired
    private LoginService loginService;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Value("${jwt.secret}")
    private String secretKey;
    // 토큰 유효시간 168 시간(7일)
    private long tokenValidTime =6000L;


    @DisplayName("응답값으로 사용자 정보를 받아온다")
    @Test
    void responseMember() {
        Member member = Member.builder()
                .memberId(12341l)
                .email("skaksdl@naver.com")
                .nickname("seokki97")
                .build();
        memberRepository.save(member);
        LoginResponse actual = loginService.generateToken(member.getKakaoId());

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

    @DisplayName("Refresh 토큰을 저장한다.")
    @Test
    void saveRefreshToken(){
        LoginResponse loginResponse = LoginResponse.builder()
                .nickname("테스트")
                .email("테스트")
                .refreshToken("token")
                .build();
        Member member = Member.builder()
                .memberId(213l)
                .email("qwe")
                .thumbnailImage("qwe")
                .nickname("qwe")
                .build();
       Token actual =  loginService.saveToken(loginResponse,member);
       Token expected = tokenRepository.findByMemberId(member.getKakaoId()).orElseThrow();

       assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @DisplayName("Access 토큰을 생성한다.")
    @Test
    void provideAccessToken(){
        String userPk = String.valueOf(123l);
        String actual = jwtProvider.createToken(userPk);

        assertThat(actual).isNotNull();
    }

    @DisplayName("Refresh 토큰을 생성한다.")
    @Test
    void provideRefreshToken(){
        String userPk = String.valueOf(123l);
        String actual = jwtProvider.createRefreshToken(userPk);

        assertThat(actual).isNotNull();
    }

    @DisplayName("Access 토큰으로 사용자 정보를 조회한다.")
    @Test
    void showUserDetails(){

        String userPk = String.valueOf(123l);
        String actual = jwtProvider.createToken(userPk);

        String expected = jwtProvider.getUserPk(actual);

        assertThat(userPk).isEqualTo(expected);
    }
}

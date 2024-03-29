package com.example.perfume.member.service;

import com.example.perfume.member.domain.Member;
import com.example.perfume.member.domain.Token;
import com.example.perfume.member.dto.loginDto.LoginResponse;
import com.example.perfume.member.exception.MemberAlreadyLogoutException;
import com.example.perfume.member.exception.UserNotFoundException;
import com.example.perfume.member.repository.TokenRepository;
import com.example.perfume.member.service.jwt.JwtProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginService {
    private final JwtProvider jwtProvider;
    private final TokenRepository tokenRepository;
    private final MemberService memberService;

    public LoginService(JwtProvider jwtProvider, TokenRepository tokenRepository, MemberService memberService) {
        this.jwtProvider = jwtProvider;
        this.tokenRepository = tokenRepository;
        this.memberService = memberService;
    }

    //토큰 저장
    public void saveToken(LoginResponse loginResponse, Member member) {
        Token token = new Token(loginResponse.getRefreshToken(), member.getKakaoId());
        if (!tokenRepository.existsByMemberId(member.getKakaoId())) {
            tokenRepository.save(token);
        }
    }

    public LoginResponse generateToken(Long id) {
        Member member = memberService.findMemberById(id);
        String accessToken = jwtProvider.generateAccessToken(String.valueOf(member.getMemberId()));
        String refreshToken = jwtProvider.generateRefreshToken(String.valueOf(member.getMemberId()));

        LoginResponse loginResponse = LoginResponse.makeLoginResponseObject(member, accessToken, refreshToken);

        saveToken(loginResponse, member);
        return loginResponse;
    }

    public LoginResponse permitClientRequest(String accessToken) {
        Member member = memberService.findMemberById(Long.valueOf(jwtProvider.getUserPk(accessToken)));
        if (!memberService.isMemberLogout(accessToken)) {
            throw new MemberAlreadyLogoutException();
        }
        return LoginResponse.builder()
                .memberId(member.getMemberId())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .build();
    }

    @Transactional
    public LoginResponse generateNewAccessToken(String refreshToken) {
        Token token = tokenRepository.findByRefreshToken(refreshToken).orElseThrow(UserNotFoundException::new);
        Member member = memberService.findMemberById(token.getMemberId());

        return LoginResponse.builder()
                .memberId(member.getMemberId())
                .nickname(member.getNickname())
                .email(member.getEmail())
                .accessToken(regenerateAccessToken(String.valueOf(member.getKakaoId())))
                .build();
    }

    public String regenerateAccessToken(String userPk) {
        return jwtProvider.generateAccessToken(userPk);
    }
}

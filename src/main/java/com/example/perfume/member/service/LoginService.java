package com.example.perfume.member.service;

import com.example.perfume.member.domain.Member;
import com.example.perfume.member.domain.Token;
import com.example.perfume.member.dto.memberDto.LoginResponse;
import com.example.perfume.member.exception.TokenInvalidException;
import com.example.perfume.member.exception.UserNotFoundException;
import com.example.perfume.member.repository.TokenRepository;
import com.example.perfume.member.service.jwt.JwtProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginService implements UserDetailsService {
    private final JwtProvider jwtProvider;
    private final TokenRepository tokenRepository;
    private final MemberService memberService;

    public LoginService(JwtProvider jwtProvider, TokenRepository tokenRepository, MemberService memberService) {
        this.jwtProvider = jwtProvider;
        this.tokenRepository = tokenRepository;
        this.memberService = memberService;
    }

    @Override
    public UserDetails loadUserByUsername(String refreshToken) throws UsernameNotFoundException {
        return (UserDetails) memberService.findMemberByEmail(refreshToken);
    }

    //토큰 저장
    public Token saveToken(LoginResponse loginResponse, Member member) {
        Token token = Token.builder()
                .refreshToken(loginResponse.getRefreshToken())
                .memberId(member.getMemberId())
                .build();
        if (!tokenRepository.existsByMemberId(member.getMemberId())) {
            tokenRepository.save(token);
        }
        return token;
    }

    public LoginResponse generateToken(Long memberId) {
        Member member = memberService.findByMemberPk(memberId);
        String accessToken = jwtProvider.createToken(String.valueOf(member.getMemberId()));
        String refreshToken = jwtProvider.createRefreshToken(String.valueOf(member.getMemberId()));

        LoginResponse loginResponse = LoginResponse.builder()
                .id(member.getId())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

        saveToken(loginResponse, member);
        return loginResponse;
    }

    public LoginResponse permitClientRequest(String accessToken) { // 요청 보내는 부분
        Member member = memberService.findMemberByEmail(jwtProvider.getUserPk(accessToken));

        return LoginResponse.builder()
                .id(member.getId())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .build();
    }

    @Transactional
    public LoginResponse generateNewAccessToken(String refreshToken) {
        Token token = tokenRepository.findByRefreshToken(refreshToken).orElseThrow(UserNotFoundException::new);
        Member member = memberService.findByMemberPk(token.getMemberId());

        return LoginResponse.builder()
                .id(member.getId())
                .nickname(member.getNickname())
                .email(member.getEmail())
                .accessToken(regenerateAccessToken(String.valueOf(member.getMemberId())))
                .build();
    }

    public String regenerateAccessToken(String userPk) {
        return jwtProvider.createToken(userPk);
    }
}

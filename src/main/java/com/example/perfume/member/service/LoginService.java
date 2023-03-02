package com.example.perfume.member.service;

import com.example.perfume.member.domain.Member;
import com.example.perfume.member.domain.Token;
import com.example.perfume.member.dto.memberDto.LoginResponse;
import com.example.perfume.member.exception.TokenInvalidException;
import com.example.perfume.member.exception.UserNotFoundException;
import com.example.perfume.member.repository.MemberRepository;
import com.example.perfume.member.repository.TokenRepository;
import com.example.perfume.member.service.jwt.JwtInterceptor;
import com.example.perfume.member.service.jwt.JwtProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
                .accessToken(loginResponse.getAccessToken())
                .refreshToken(loginResponse.getRefreshToken())
                .memberId(member.getMemberId())
                .build();
        if (!tokenRepository.existsByMemberId(member.getMemberId())) {
            tokenRepository.save(token);
        }
        return token;
    }

    public LoginResponse createLoginResponse(Member member, String accessToken, String refreshToken) {
        LoginResponse loginResponse = LoginResponse.builder()
                .id(member.getId())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
        return loginResponse;
    }

    public LoginResponse generateToken(Long memberId) {
        Member member = memberService.findByMemberPk(memberId);
        String accessToken = jwtProvider.createToken(member.getEmail());
        String refreshToken = jwtProvider.createRefreshToken(member.getEmail());
        LoginResponse loginResponse = createLoginResponse(member, accessToken, refreshToken);

        saveToken(loginResponse, member);
        return loginResponse;
    }

    public LoginResponse permitClientRequest(Member member) {
        Token generatedToken = tokenRepository.findByMemberId(member.getMemberId()).orElseThrow(TokenInvalidException::new);

        return createLoginResponse(member, generatedToken.getAccessToken(), generatedToken.getRefreshToken());
    }

    public LoginResponse responseToken(String accessToken) {

        Member member = memberService.findMemberByEmail(jwtProvider.getUserPk(accessToken));
        return permitClientRequest(member);
    }

    @Transactional
    public LoginResponse generateNewAccessToken(String accessToken, String refreshToken) {

        Token token = tokenRepository.findByRefreshTokenAndAccessToken(refreshToken, accessToken).orElseThrow(UserNotFoundException::new);

        tokenRepository.deleteByMemberId(token.getMemberId());
        LoginResponse newTokenResponse = generateToken(token.getMemberId());
        return newTokenResponse;
    }
}

package com.example.perfume.member.service;

import com.example.perfume.member.domain.Member;
import com.example.perfume.member.domain.Token;
import com.example.perfume.member.dto.LoginResponse;
import com.example.perfume.member.dto.MemberRequestDto;
import com.example.perfume.member.exception.UserNotFoundException;
import com.example.perfume.member.repository.MemberRepository;
import com.example.perfume.member.repository.TokenRepository;
import com.example.perfume.oauth.exception.EmailNotFoundException;
import com.example.perfume.oauth.service.OauthService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;

    private final OauthService oauthService;
    private final TokenRepository tokenRepository;

    public LoginService(MemberRepository memberRepository, JwtProvider jwtProvider, OauthService oauthService,
                        TokenRepository tokenRepository) {
        this.memberRepository = memberRepository;
        this.jwtProvider = jwtProvider;
        this.oauthService = oauthService;
        this.tokenRepository = tokenRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String refreshToken) throws UsernameNotFoundException {
        return (UserDetails) memberRepository.findByEmail(refreshToken)
                .orElseThrow(UserNotFoundException::new);
    }
    //로그인 기능
    public LoginResponse permitLogin(Long memberId) {
        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(UserNotFoundException::new);
        LoginResponse loginResponse = LoginResponse.builder()
                .id(member.getId())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .accessToken(jwtProvider.createToken(member.getEmail()))
                .refreshToken(jwtProvider.createRefreshToken(member.getEmail()))
                .build();

        saveToken(loginResponse, member);
        return loginResponse;
    }

    //토큰 저장
    public Token saveToken(LoginResponse loginResponse, Member member) {
        Token token = Token.builder()
                .accessToken(loginResponse.getAccessToken())
                .refreshToken(loginResponse.getRefreshToken())
                .memberId(member.getMemberId())
                .build();
        return tokenRepository.save(token);
    }

}

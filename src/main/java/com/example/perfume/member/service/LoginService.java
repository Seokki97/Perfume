package com.example.perfume.member.service;

import com.example.perfume.member.domain.Member;
import com.example.perfume.member.domain.Token;
import com.example.perfume.member.dto.LoginResponse;
import com.example.perfume.member.dto.TokenDto;
import com.example.perfume.member.exception.UserNotFoundException;
import com.example.perfume.member.repository.MemberRepository;
import com.example.perfume.member.repository.TokenRepository;
import com.example.perfume.oauth.service.OauthService;
import lombok.extern.java.Log;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

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

    public Member findMember(String token) {
        return memberRepository.findByEmail(jwtProvider.getUserPk(token))
                .orElseThrow(UserNotFoundException::new);
    }

    //토큰 저장
    public Token saveToken(LoginResponse loginResponse, Member member) {
        Token token = Token.builder()
                .accessToken(loginResponse.getAccessToken())
                .refreshToken(loginResponse.getRefreshToken())
                .memberId(member.getMemberId())
                .build();
        if(!tokenRepository.existsByMemberId(member.getMemberId())) {
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
        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(UserNotFoundException::new);
        String accessToken = jwtProvider.createToken(member.getEmail());
        String refreshToken = jwtProvider.createRefreshToken(member.getEmail());
        LoginResponse loginResponse = createLoginResponse(member, accessToken, refreshToken);

        saveToken(loginResponse, member);
        return loginResponse;
    }

    public LoginResponse permitClientRequest(Member member) {
        Token generatedToken = tokenRepository.findByMemberId(member.getMemberId()).get();

        return createLoginResponse(member, generatedToken.getAccessToken(), generatedToken.getRefreshToken());
    }

    public LoginResponse responseToken(HttpServletRequest httpServletRequest) {
        String token = jwtProvider.resolveToken(httpServletRequest);
        Member member = findMember(token);

        if (!jwtProvider.validateToken(token)) {
            tokenRepository.deleteByMemberId(member.getMemberId());
            LoginResponse newTokenResponse = generateToken(member.getMemberId());
            return newTokenResponse;
        }
        return permitClientRequest(member);
    }

    @Transactional
    public LoginResponse generateNewAccessToken(HttpServletRequest httpServletRequest){
        String accessToken = jwtProvider.resolveToken(httpServletRequest);
        String refreshToken = jwtProvider.resolveRefreshToken(httpServletRequest);

        if(!tokenRepository.existsByRefreshToken(refreshToken)){
            //예외
        }
        Token token = tokenRepository.findByRefreshTokenAndAccessToken(refreshToken,accessToken).orElseThrow(UserNotFoundException::new);

        tokenRepository.deleteByMemberId(token.getMemberId());
        LoginResponse newTokenResponse = generateToken(token.getMemberId());
        return newTokenResponse;
    }
}

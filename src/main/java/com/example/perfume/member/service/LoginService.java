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

    //토큰 저장
    public Token saveToken(LoginResponse loginResponse, Member member) {
        Token token = Token.builder()
                .accessToken(loginResponse.getAccessToken())
                .refreshToken(loginResponse.getRefreshToken())
                .memberId(member.getMemberId())
                .build();
        return tokenRepository.save(token);
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

    //로그인 기능
    public LoginResponse permitLogin(Long memberId) {
        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(UserNotFoundException::new);
        String accessToken = jwtProvider.createToken(member.getEmail());
        String refreshToken = jwtProvider.createRefreshToken(member.getEmail());
        LoginResponse loginResponse = createLoginResponse(member, accessToken, refreshToken);

        saveToken(loginResponse, member);
        return loginResponse;
    }

    //요청이 있을때마다 헤더에 붙여온 token을 읽고 만료시간 판단 -> 후 만료됐으면 새로 발급 아니면
    public LoginResponse readToken(HttpServletRequest httpServletRequest) {
        String token = jwtProvider.resolveToken(httpServletRequest);

        Member member = memberRepository.findByEmail(jwtProvider.getUserPk(token))
                .orElseThrow(UserNotFoundException::new);

        if (!jwtProvider.validateToken(token)) { //만료되었으면 기존 토큰 삭제하고, 새로 발급해서 저장하고 반환
            String accessToken = jwtProvider.createToken(member.getEmail());
            String refreshToken = jwtProvider.createRefreshToken(member.getEmail());
            tokenRepository.deleteByMemberId(member.getMemberId()).get();
            LoginResponse loginResponse2 = createLoginResponse(member,accessToken,refreshToken);
            Token newToken = Token.builder()
                    .memberId(member.getMemberId())
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();
            tokenRepository.save(newToken);
            return loginResponse2;
        }

        Token generatedToken = tokenRepository.findByMemberId(member.getMemberId()).get();
        LoginResponse loginResponse = createLoginResponse
                (member, generatedToken.getAccessToken(), generatedToken.getRefreshToken());
        return loginResponse;
    }

}

package com.example.perfume.member.service;

import com.example.perfume.member.domain.Member;
import com.example.perfume.member.domain.Token;
import com.example.perfume.member.dto.LoginResponse;
import com.example.perfume.member.exception.UserNotFoundException;
import com.example.perfume.member.repository.MemberRepository;
import com.example.perfume.member.repository.TokenRepository;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class LoginService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;
    private final TokenRepository tokenRepository;
    private final JwtInterceptor jwtInterceptor;

    public LoginService(MemberRepository memberRepository, JwtProvider jwtProvider,
                        TokenRepository tokenRepository, JwtInterceptor jwtInterceptor) {
        this.memberRepository = memberRepository;
        this.jwtProvider = jwtProvider;
        this.tokenRepository = tokenRepository;
        this.jwtInterceptor = jwtInterceptor;
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

    public LoginResponse responseToken(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws IOException {
        String token = jwtProvider.resolveToken(httpServletRequest);
        if(!jwtProvider.validateToken(token)){
            httpServletResponse.sendError(401, "401에러 받아랑!");
        }
        Member member = findMember(token);
        //만료됐으면 만료 응답 내려줌 -> 재발급 요청 -> 재생성해서 보내줌
        return permitClientRequest(member);
    }

    @Transactional
    public LoginResponse generateNewAccessToken(HttpServletRequest httpServletRequest){
        String accessToken = jwtProvider.resolveToken(httpServletRequest);
        String refreshToken = jwtProvider.resolveRefreshToken(httpServletRequest);

        Token token = tokenRepository.findByRefreshTokenAndAccessToken(refreshToken,accessToken).orElseThrow(UserNotFoundException::new);

        tokenRepository.deleteByMemberId(token.getMemberId());
        LoginResponse newTokenResponse = generateToken(token.getMemberId());
        return newTokenResponse;
    }
}

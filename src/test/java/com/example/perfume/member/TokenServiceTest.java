package com.example.perfume.member;

import com.example.perfume.member.service.jwt.JwtProvider;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TokenServiceTest {
    @Autowired
    private JwtProvider jwtProvider;

    @Value("${jwt.secret}")
    private String secretKey ;

    @DisplayName("Access 토큰을 생성한다.")
    @Test
    void createAccessToken(){
        String accessToken = jwtProvider.createToken("seokki97");

        assertThat(accessToken).isNotNull();
    }

    @DisplayName("Refresh 토큰을 생성한다.")
    @Test
    void createRefreshToken(){
        String refreshToken = jwtProvider.createRefreshToken("seokki87");

        assertThat(refreshToken).isNotNull();
    }

    @DisplayName("토큰으로 유저 정보를 조회한다.")
    @Test
    void getUserInfoByInvalidToken(){
        String accessToken = jwtProvider.createToken("seokki97");
        String userInfo = Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(accessToken).getBody().getSubject();
        assertThat("seokki97").isEqualTo(userInfo);
    }


}

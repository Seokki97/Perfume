package com.example.perfume.member.jwt;

import com.example.perfume.member.service.jwt.JwtProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = "jwt.secret=testsecret")
public class JwtProviderTest {

    @Autowired
    private JwtProvider jwtProvider;

    @DisplayName("AcccessToken을 생성한다.")
    @Test
    void generateAccessToken() {
        String userPk = "123";
        String accessToken = jwtProvider.generateAccessToken(userPk);

        String parsedToken = jwtProvider.getUserPk(accessToken);
        Assertions.assertAll(
                () -> Assertions.assertNotEquals(userPk, accessToken),
                () -> Assertions.assertNotNull(accessToken),
                () -> Assertions.assertFalse(accessToken.isEmpty()),
                () -> Assertions.assertEquals(userPk, parsedToken)
        );
    }

    @DisplayName("RefreshToken을 생성한다")
    @Test
    void generateRefreshToken() {
        String userPk = "123";
        String refreshToken = jwtProvider.generateRefreshToken(userPk);

        String parsedToken = jwtProvider.getUserPk(refreshToken);
        Assertions.assertAll(
                () -> Assertions.assertNotEquals(userPk, refreshToken),
                () -> Assertions.assertNotNull(refreshToken),
                () -> Assertions.assertFalse(refreshToken.isEmpty()),
                () -> Assertions.assertNotEquals(parsedToken, refreshToken)
        );
    }

    
}

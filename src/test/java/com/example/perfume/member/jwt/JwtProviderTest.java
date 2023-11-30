package com.example.perfume.member.jwt;

import com.example.perfume.member.exception.TokenInvalidException;
import com.example.perfume.member.service.jwt.JwtProvider;
import com.example.perfume.review.repository.ReviewBoardRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import javax.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = "jwt.secret=testsecret")
public class JwtProviderTest {

    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private ReviewBoardRepository reviewBoardRepository;

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

    @DisplayName("Request Header에 담긴 AccessToken을 가져온다")
    @Test
    void resolveAccessToken() {
        String userPk = "123";
        String accessToken = jwtProvider.generateAccessToken(userPk);
        HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);

        Mockito.when(httpServletRequest.getHeader("Authorization")).thenReturn(accessToken);
        Assertions.assertEquals(accessToken, jwtProvider.resolveAccessToken(httpServletRequest));
    }

    @DisplayName("Header에 담긴 RefreshToken을 가져온다.")
    @Test
    void resolveRefreshToken() {

        String userPk = "123";
        String refreshToken = jwtProvider.generateRefreshToken(userPk);
        HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);

        Mockito.when(httpServletRequest.getHeader("X-REFRESH-TOKEN")).thenReturn(refreshToken);
        Assertions.assertEquals(refreshToken, jwtProvider.resolveRefreshToken(httpServletRequest));
    }

    @DisplayName("AccessToken의 Claim을 추출한다. 정보가 다를 경우 Exception이 발생한다.")
    @Test
    void getClaims() {
        String userPk = "123";
        String accessToken = jwtProvider.generateAccessToken(userPk);
        String fakeToken = "dosaodasoa";

        Jws<Claims> claim = jwtProvider.getClaims(accessToken);
        Assertions.assertAll(
                () -> Assertions.assertEquals(userPk, claim.getBody().getSubject()),
                () -> Assertions.assertEquals("https://inhyang.netlify.app/", claim.getBody().getIssuer()),
                () -> Assertions.assertThrows(TokenInvalidException.class, () -> jwtProvider.getClaims(fakeToken))
        );
    }
}

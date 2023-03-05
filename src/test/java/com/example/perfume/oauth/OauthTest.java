package com.example.perfume.oauth;


import com.example.perfume.oauth.exception.EmailNotFoundException;
import com.example.perfume.oauth.service.OauthService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class OauthTest {

    @Autowired
    private OauthService oauthService;

    @DisplayName("이메일 사용을 동의하지 않았을 시 예외가 발생한다.")
    @Test
    void isAgreeEmailCheck(){
        String email = null;

        assertThatThrownBy(() -> oauthService.isAgreeEmailUsing(email))
                .isInstanceOf(EmailNotFoundException.class).hasMessage("계정 이용 동의를 하지 않았습니다.");
    }

}

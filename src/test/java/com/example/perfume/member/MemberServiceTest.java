package com.example.perfume.member;

import com.example.perfume.member.domain.Member;
import com.example.perfume.member.dto.memberDto.MemberRequestDto;
import com.example.perfume.member.exception.UserNotFoundException;
import com.example.perfume.member.service.MemberService;
import com.example.perfume.oauth.exception.MemberAlreadyExistException;
import com.example.perfume.oauth.service.OauthService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private OauthService oauthService;
    @DisplayName("id로 회원을 찾는다. 없을 시 UserNotFoundException 을 발생시킨다.")
    @Test
    void findById() {
        Member expected = Member.builder()
                .memberId(12323l)
                .email("dd32as@mav.com")
                .nickname("석32키")
                .build();
        memberService.saveMemberProfile(expected);
        Member actual = memberService.findMemberById(expected.getMemberId());

        assertAll(
                () -> assertThat(actual).usingRecursiveComparison().isEqualTo(expected),
                () -> assertThrows(UserNotFoundException.class, () -> {
                    memberService.findMemberById(123141224l);
                })
        );
    }

    @DisplayName("pk로 회원을 찾는다. 없을 시 UserNotFoundException 을 발생시킨다.")
    @Test
    void findByPk() {

        Member expected = Member.builder()
                .memberId(12332l)
                .email("ddas@mav.com")
                .nickname("석키")
                .build();

        memberService.saveMemberProfile(expected);
        Member actual = memberService.findByMemberPk(expected.getKakaoId());

        assertAll(
                () -> assertThat(actual).usingRecursiveComparison().isEqualTo(expected),
                () -> assertThrows(UserNotFoundException.class, () -> {
                    memberService.findByMemberPk(123141224l);
                })
        );
    }

    @DisplayName("email로 회원을 찾는다. 없을 시 UserNotFoundException 을 발생시킨다.")
    @Test
    void findByEmail() {

        Member expected = Member.builder()
                .memberId(123l)
                .email("ddas@m21av.com")
                .nickname("석키")
                .build();
        memberService.saveMemberProfile(expected);

        Member actual = memberService.findMemberByEmail(expected.getEmail());

        assertAll(
                () -> assertThat(actual).usingRecursiveComparison().isEqualTo(expected),
                () -> assertThrows(UserNotFoundException.class, () -> {
                    memberService.findMemberByEmail("Dasda");
                })
        );
    }

    @DisplayName("회원이 이미 존재하면, MemberAlreadyException 을 발생시킨다.")
    @Test
    void isAlreadyExist() {

        Member expected = Member.builder()
                .memberId(123123l)
                .email("ddas@m123av.com")
                .nickname("석키123")
                .build();
        MemberRequestDto memberId = MemberRequestDto.builder().memberId(expected.getKakaoId())
                .email("ddas@m123av.com")
                .nickname("석키123").build();
        memberService.saveMemberProfile(expected);

        assertThrows(MemberAlreadyExistException.class, () -> {
            oauthService.saveUserProfile(memberId);
        });
    }
}

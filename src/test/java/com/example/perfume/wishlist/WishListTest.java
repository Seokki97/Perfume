package com.example.perfume.wishlist;

import com.example.perfume.member.domain.Member;
import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.wishlist.domain.WishList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


public class WishListTest {

    @DisplayName("향수를 위시리스트에 추가한다.")
    @Test
    void addWishList() {
        Member member = Member.builder()
                .memberId(1l)
                .build();
        Perfume perfume = Perfume.builder()
                .perfumeId(1l)
                .build();
        WishList actual = WishList.addPerfumeToWishList(member, perfume);

        Assertions.assertInstanceOf(WishList.class, actual);
    }

}

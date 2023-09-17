package com.example.perfume.wishlist;

import com.example.perfume.member.domain.Member;
import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.wishlist.domain.WishList;
import com.example.perfume.wishlist.dto.WishListRequest;
import com.example.perfume.wishlist.repository.WishListRepository;
import com.example.perfume.wishlist.service.WishListUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.*;

@ExtendWith(MockitoExtension.class)
public class WishListUtilTest {

    @InjectMocks
    private WishListUtil wishListUtil;

    @Mock
    private WishListRepository wishListRepository;

    @DisplayName("RequestBody가 빈 객체인지 검증한다.")
    @Test
    void validateRequestBody() {
        WishListRequest nullObject = WishListRequest.builder().build();
        WishListRequest perfumeIdEmpty = WishListRequest.builder()
                .memberId(1l)
                .build();
        WishListRequest memberIdEmptyObject = WishListRequest.builder()
                .perfumeId(1l)
                .build();
        boolean emptyObject = wishListUtil.isEmptyRequestBody(nullObject);
        boolean perfumeEmptyObject = wishListUtil.isEmptyRequestBody(perfumeIdEmpty);
        boolean memberEmptyObject = wishListUtil.isEmptyRequestBody(memberIdEmptyObject);

        Assertions.assertAll(
                () -> Assertions.assertTrue(emptyObject),
                () -> Assertions.assertTrue(perfumeEmptyObject),
                () -> Assertions.assertTrue(memberEmptyObject)

        );
    }

    @DisplayName("WishList의 담은 항목이 중복되었는지 검증한다.")
    @Test
    void isDuplicateWishObject() {
        WishList wishList = new WishList(1l, Member.builder().id(2l).build(), Perfume.builder().id(1l).build());
        List<WishList> wishLists = new ArrayList<>();
        wishLists.add(wishList);

        when(wishListRepository.findByMemberId(any())).thenReturn(wishLists);

        WishListRequest duplicatedPerfume = WishListRequest.builder()
                .perfumeId(1l)
                .memberId(2l)
                .build();
        boolean actual = wishListUtil.isDuplicateWishItem(duplicatedPerfume);

        Assertions.assertTrue(actual);
    }
}

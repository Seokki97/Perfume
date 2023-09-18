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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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

    @DisplayName("위시리스트가 가득 찼는지 확인한다")
    @Test
    void isMaxSize() {
        List<WishList> wishLists = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            wishLists.add(new WishList(1l, Member.builder().id(1l).build(), null));
        }

        when(wishListRepository.findByMemberId(any())).thenReturn(wishLists);

        boolean actual = wishListUtil.isWishListOverMaxSize(WishListRequest.builder().memberId(1l).build());

        Assertions.assertTrue(actual);
    }

    @DisplayName("모든 위시리스트를 삭제한다.")
    @Test
    void deleteAllWishList() {
        Long memberId = 1l;
        doNothing().when(wishListRepository).deleteByMemberId(memberId);

        wishListUtil.deleteAllWishedList(memberId);

        verify(wishListRepository, times(1)).deleteByMemberId(memberId);
    }

    @DisplayName("선택한 위시 향수를 삭제한다.")
    @Test
    void deleteSelectedWishElement() {
        WishListRequest wishListRequest = WishListRequest.builder()
                .perfumeId(1l)
                .memberId(1l)
                .build();

        doNothing().when(wishListRepository).deleteByMemberIdAndPerfumeId(wishListRequest.getMemberId(), wishListRequest.getPerfumeId());

        wishListUtil.deleteSelectedWishElement(wishListRequest);
        verify(wishListRepository, times(1)).deleteByMemberIdAndPerfumeId(wishListRequest.getMemberId(), wishListRequest.getPerfumeId());
    }
}

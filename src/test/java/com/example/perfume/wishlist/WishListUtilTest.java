package com.example.perfume.wishlist;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.perfume.member.domain.Member;
import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.wishlist.domain.WishList;
import com.example.perfume.wishlist.dto.WishListRequest;
import com.example.perfume.wishlist.repository.WishListRepository;
import com.example.perfume.wishlist.service.WishListService;
import com.example.perfume.wishlist.service.WishListValidation;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class WishListUtilTest {

    @InjectMocks
    private WishListService wishListUtil;

    @InjectMocks
    private WishListValidation wishListValidation;

    @Mock
    private WishListRepository wishListRepository;

    @DisplayName("RequestBody가 빈 객체인지 검증한다.")
    @Test
    void validateRequestBody() {
        WishListRequest nullObject = new WishListRequest(null, null);
        WishListRequest perfumeIdEmpty = new WishListRequest(1l, null);
        WishListRequest memberIdEmptyObject = new WishListRequest(null, 1l);
        boolean emptyObject = nullObject.isEmptyRequestBody();
        boolean perfumeEmptyObject = perfumeIdEmpty.isEmptyRequestBody();
        boolean memberEmptyObject = memberIdEmptyObject.isEmptyRequestBody();

        Assertions.assertAll(() -> Assertions.assertTrue(emptyObject), () -> Assertions.assertTrue(perfumeEmptyObject),
                () -> Assertions.assertTrue(memberEmptyObject)

        );
    }

    @DisplayName("WishList의 담은 항목이 중복되었는지 검증한다.")
    @Test
    void isDuplicateWishObject() {
        WishList wishList = new WishList(1l, Member.builder().memberId(2l).build(),
                Perfume.builder().perfumeId(1l).build());
        List<WishList> wishLists = new ArrayList<>();
        wishLists.add(wishList);

        when(wishListRepository.findByMemberMemberId(any())).thenReturn(wishLists);

        WishListRequest duplicatedPerfume = new WishListRequest(2l, 1l);
        boolean actual = wishListValidation.isDuplicateWishItem(duplicatedPerfume);

        Assertions.assertTrue(actual);
    }

    @DisplayName("위시리스트가 가득 찼는지 확인한다")
    @Test
    void isMaxSize() {
        List<WishList> wishLists = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            wishLists.add(new WishList(1l, Member.builder().memberId(1l).build(), null));
        }

        when(wishListRepository.findByMemberMemberId(any())).thenReturn(wishLists);

        boolean actual = wishListValidation.isWishListOverMaxSize(new WishListRequest(1l, 1l));

        Assertions.assertTrue(actual);
    }

    @DisplayName("모든 위시리스트를 삭제한다.")
    @Test
    void deleteAllWishList() {
        Long memberId = 1l;
        doNothing().when(wishListRepository).deleteByMemberMemberId(memberId);

        wishListUtil.deleteAllWishList(memberId);

        verify(wishListRepository, times(1)).deleteByMemberMemberId(memberId);
    }

    @DisplayName("선택한 위시 향수를 삭제한다.")
    @Test
    void deleteSelectedWishElement() {
        WishListRequest wishListRequest = new WishListRequest(1l, 1l);
        List<WishList> wishLists = new ArrayList<>();
        wishLists.add(new WishList(1l, new Member(1l, null, null, null, null), null));
        doNothing().when(wishListRepository).deleteByMemberMemberIdAndPerfumePerfumeId(any(), any());
        when(wishListRepository.findByMemberMemberId(any())).thenReturn(wishLists);

        wishListUtil.deleteSelectedWishList(wishListRequest);

        verify(wishListRepository, times(1)).deleteByMemberMemberIdAndPerfumePerfumeId(wishListRequest.getMemberId(),
                wishListRequest.getPerfumeId());
    }

    @DisplayName("위시리스트 항목들을 조회한다. 없을 경우 빈 객체가 반환된다.")
    @Test
    void showAllWishList() {

        List<WishList> wishLists = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            wishLists.add(new WishList(1l, Member.builder().memberId(1l).build(), null));
        }
        List<WishList> notFoundedWishList = new ArrayList<>();

        when(wishListRepository.findByMemberMemberId(eq(1l))).thenReturn(wishLists);
        when(wishListRepository.findByMemberMemberId(eq(2l))).thenReturn(notFoundedWishList);

        int actual = wishListUtil.showWishList(1l).size();
        int actualNotFoundedCase = wishListUtil.showWishList(2l).size();
        Assertions.assertAll(() -> Assertions.assertEquals(10, actual),
                () -> Assertions.assertEquals(0, actualNotFoundedCase));
    }
}

package com.example.perfume.wishlist;

import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.wishlist.domain.WishList;
import com.example.perfume.wishlist.dto.RankingResponse;
import com.example.perfume.wishlist.repository.WishListRepository;
import com.example.perfume.wishlist.service.WishListAnalyze;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WishListAnalyzeTest {

    @InjectMocks
    private WishListAnalyze wishListAnalyze;

    @Mock
    private WishListRepository wishListRepository;

    @DisplayName("위시리스트 항목의 같은 향수 항목들의 개수를 센다.")
    @Test
    void countWishObject() {
        Perfume perfume = Perfume.builder()
                .perfumeName("조말론 우드세이지")
                .build();

        List<WishList> wishLists = new ArrayList<>();

        for (int i = 0; i < 14; i++) {
            wishLists.add(new WishList(null, null, perfume));
        }
        when(wishListRepository.findAll()).thenReturn(wishLists);

        List<RankingResponse> rankingResponses = wishListAnalyze.makeCountedWishList();

        Assertions.assertEquals(14, rankingResponses.size());
    }

    @DisplayName("인기 많은 향수순으로 추출한다.")
    @Test
    void showRanking() {

        Perfume perfume = Perfume.builder()
                .perfumeName("조말론 우드세이지")
                .build();
        Perfume perfume1 = Perfume.builder()
                .perfumeName("가비아")
                .build();
        List<WishList> wishLists = new ArrayList<>();

        for (int i = 0; i < 14; i++) {
            wishLists.add(new WishList(null, null, perfume));

        }
        for(int i = 0; i < 13; i ++){
            wishLists.add(new WishList(null, null, perfume1));
        }
        when(wishListRepository.findAll()).thenReturn(wishLists);

        List<RankingResponse> rankingResponses = wishListAnalyze.showTopRankedPerfumeList();

        Assertions.assertAll(
                () -> Assertions.assertEquals(2, rankingResponses.size()),
                () -> Assertions.assertEquals(14,rankingResponses.get(0).getCount()),
                () -> Assertions.assertEquals(13, rankingResponses.get(1).getCount())
        );
    }
}

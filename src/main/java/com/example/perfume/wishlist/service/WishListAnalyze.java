package com.example.perfume.wishlist.service;

import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.wishlist.domain.WishList;
import com.example.perfume.wishlist.dto.RankingResponse;
import com.example.perfume.wishlist.dto.WishRankingResponse;
import com.example.perfume.wishlist.repository.WishListRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class WishListAnalyze {
    private final WishListRepository wishListRepository;

    public WishListAnalyze(WishListRepository wishListRepository) {
        this.wishListRepository = wishListRepository;
    }

    public List<RankingResponse> showTopRankedPerfume() {
        List<WishList> wishLists = findAllWishList();
        long count;
        RankingResponse rankingResponse;
        List<RankingResponse> rankingResponses = new ArrayList<>();
        for (int i = 0; i < wishLists.size(); i++) {
            int finalI = i;
            count = wishLists.stream()
                    .filter(perfume -> getPerfumeName(perfume).matches(wishLists.get(finalI).getPerfume().getPerfumeName()))
                    .count();
            rankingResponse = RankingResponse.builder()
                    .perfume(wishLists.get(i).getPerfume())
                    .count(count)
                    .build();
            rankingResponses.add(rankingResponse);
        }
        rankingResponses = rankingResponses.stream().distinct().collect(Collectors.toList());
        return rankingResponses;
    }

    public String getPerfumeName(WishList wishList) {
        return wishList.getPerfume().getPerfumeName();
    }

    public List<WishList> findAllWishList() {
        return wishListRepository.findAll();
    }
}
package com.example.perfume.wishlist.service;

import com.example.perfume.perfume.domain.Perfume;
import com.example.perfume.wishlist.domain.WishList;
import com.example.perfume.wishlist.dto.WishRankingResponse;
import com.example.perfume.wishlist.repository.WishListRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WishListAnalyze {
    private final WishListRepository wishListRepository;

    public WishListAnalyze(WishListRepository wishListRepository) {
        this.wishListRepository = wishListRepository;
    }

    public WishRankingResponse ShowMostPopularPerfume() {
        List<WishList> wishLists = wishListRepository.findAll();
        long count;
        Map<String, Long> mostPopularPerfume = new HashMap<>();
        for (int i = 0; i < wishLists.size(); i++) {
            int finalI = i;
            count = wishLists.stream().filter(perfume -> perfume.getPerfume().getPerfumeName().matches(wishLists.get(finalI).getPerfume().getPerfumeName())).count();
            mostPopularPerfume.put(wishLists.get(i).getPerfume().getPerfumeName(), count);
        }
        return WishRankingResponse.builder().rankingMap(mostPopularPerfume).build();
    }
}
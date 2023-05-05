package com.example.perfume.wishlist.service;

import com.example.perfume.wishlist.domain.WishList;
import com.example.perfume.wishlist.dto.RankingResponse;
import com.example.perfume.wishlist.exception.RankingCannotMakeException;
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

    public List<RankingResponse> makeCountedWishList() {
        List<WishList> wishLists = findAllWishList();
        long count;
        RankingResponse rankingResponse;
        List<RankingResponse> rankingResponses = new ArrayList<>();

        for (int i = 0; i < wishLists.size(); i++) {
            count = countWishListObjects(wishLists, i);

            rankingResponse = RankingResponse.makeRankingResponseObject(wishLists.get(i).getPerfume(), count);
            rankingResponses.add(rankingResponse);
        }

        return rankingResponses;
    }

    public List<RankingResponse> showTopRankedPerfumeList() {
        List<RankingResponse> rankingResponses = makeCountedWishList();
        rankingResponses = deleteDuplicatedElement(rankingResponses);
        rankingResponses = sortRankingListDescendingOrder(rankingResponses);

        if (rankingResponses.isEmpty()) {
            throw new RankingCannotMakeException();
        }
        return rankingResponses;
    }

    public Long countWishListObjects(List<WishList> wishLists, int index) {
        return wishLists.stream()
                .filter(perfume -> getPerfumeName(perfume).matches(wishLists.get(index).getPerfume().getPerfumeName()))
                .count();
    }

    public List<RankingResponse> sortRankingListDescendingOrder(List<RankingResponse> rankingResponses) {
        return rankingResponses.stream()
                .sorted(Comparator.comparingLong(RankingResponse::getCount).reversed())
                .collect(Collectors.toList());
    }

    public List<RankingResponse> deleteDuplicatedElement(List<RankingResponse> rankingResponses) {
        return rankingResponses.stream()
                .distinct()
                .collect(Collectors.toList());
    }

    public String getPerfumeName(WishList wishList) {
        return wishList.getPerfume().getPerfumeName();
    }

    public List<WishList> findAllWishList() {
        return wishListRepository.findAll();
    }
}
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
        List<WishList> wishLists = wishListRepository.findAll();
        List<RankingResponse> rankingResponses = new ArrayList<>();

        Map<String, Long> countedWishLists = countWishListObjects(wishLists);

        for (WishList wishList : wishLists) {
            long count = countedWishLists.getOrDefault(wishList.getPerfume().getPerfumeName(), 0l);
            RankingResponse rankingResponse = RankingResponse.makeRankingResponseObject(wishList.getPerfume(), count);
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

    private Map<String, Long> countWishListObjects(List<WishList> wishLists) {
        return wishLists.stream()
                .collect(Collectors.groupingBy(
                        wishList -> wishList.getPerfume().getPerfumeName(), Collectors.counting()
                ));
    }

    private List<RankingResponse> sortRankingListDescendingOrder(List<RankingResponse> rankingResponses) {
        return rankingResponses.stream()
                .sorted(Comparator.comparingLong(RankingResponse::getCount).reversed())
                .collect(Collectors.toList());
    }

    private List<RankingResponse> deleteDuplicatedElement(List<RankingResponse> rankingResponses) {
        return rankingResponses.stream()
                .distinct()
                .collect(Collectors.toList());
    }
}
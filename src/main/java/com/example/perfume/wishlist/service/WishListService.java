package com.example.perfume.wishlist.service;

import com.example.perfume.wishlist.repository.WishListRepository;
import org.springframework.stereotype.Service;

@Service
public class WishListService {
    private final WishListRepository wishListRepository;

    public WishListService(WishListRepository wishListRepository) {
        this.wishListRepository = wishListRepository;
    }
}

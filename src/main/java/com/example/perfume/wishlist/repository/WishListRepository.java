package com.example.perfume.wishlist.repository;

import com.example.perfume.wishlist.domain.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Long> {
}

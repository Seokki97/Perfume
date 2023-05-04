package com.example.perfume.wishlist.repository;

import com.example.perfume.wishlist.domain.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface WishListRepository extends JpaRepository<WishList, Long> {
    List<WishList> findByMemberId(Long memberId);

    List<WishList> deleteByMemberId(Long memberId);

    Optional<WishList> deleteByMemberIdAndPerfumeId(Long memberId, Long perfumeId);

    List<WishList> findAll();
}

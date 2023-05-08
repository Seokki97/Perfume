package com.example.perfume.wishlist.repository;

import com.example.perfume.wishlist.domain.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Long> {
    List<WishList> findByMemberId(Long memberId);

    void deleteByMemberId(Long memberId);

    void deleteByMemberIdAndPerfumeId(Long memberId, Long perfumeId);

    boolean existsByMemberIdAndPerfumeId(Long memberId, Long perfumeId);

    List<WishList> findAll();
}

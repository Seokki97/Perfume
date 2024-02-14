package com.example.perfume.wishlist.repository;

import com.example.perfume.wishlist.domain.WishList;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Long> {
    List<WishList> findByMemberMemberId(Long memberId);

    void deleteByMemberMemberId(Long memberId);

    void deleteByMemberMemberIdAndPerfumePerfumeId(Long memberId, Long perfumeId);

    List<WishList> findAll();
}

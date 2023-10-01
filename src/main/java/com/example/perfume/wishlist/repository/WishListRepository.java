package com.example.perfume.wishlist.repository;

import com.example.perfume.wishlist.domain.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Long> {
    List<WishList> findByMember(Long memberId);

    void deleteByMember_MemberId(Long memberId);

    void deleteByMember_MemberIdAndPerfume_PerfumeId(Long memberId, Long perfumeId);

    boolean existsByMember_MemberIdAndPerfume_PerfumeId(Long memberId, Long perfumeId);

    List<WishList> findAll();
}

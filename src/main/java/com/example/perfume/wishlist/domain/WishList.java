package com.example.perfume.wishlist.domain;

import com.example.perfume.member.domain.Member;
import com.example.perfume.perfume.domain.Perfume;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "wish_list")
@Getter
@NoArgsConstructor
public class WishList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wish_list_id", nullable = false)
    private Long wishListId;

    @ManyToOne
    private Member member;

    @ManyToOne
    private Perfume perfume;

    @Builder
    public WishList(Long wishListId, Member member, Perfume perfume) {
        this.wishListId = wishListId;
        this.member = member;
        this.perfume = perfume;
    }

    public static WishList addPerfumeToWishList(Member member, Perfume perfume) {
        return WishList.builder()
                .perfume(perfume)
                .member(member)
                .build();
    }

}

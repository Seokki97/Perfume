package com.example.perfume.wishlist.domain;

import com.example.perfume.member.domain.Member;
import com.example.perfume.perfume.domain.Perfume;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

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


    public WishList(Long wishListId, Member member, Perfume perfume) {
        this.wishListId = wishListId;
        this.member = member;
        this.perfume = perfume;
    }

    public WishList(Member member, Perfume perfume) {
        this.member = member;
        this.perfume = perfume;
    }

    public static WishList addPerfumeToWishList(Member member, Perfume perfume) {
        return new WishList(member, perfume);
    }

    public String getPerfumeName() {
        return perfume.getPerfumeName();
    }
}

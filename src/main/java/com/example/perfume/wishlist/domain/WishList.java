package com.example.perfume.wishlist.domain;

import com.example.perfume.member.domain.Member;
import com.example.perfume.perfume.domain.Perfume;
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
    private Long id;

    @ManyToOne
    private Member member;

    @ManyToOne
    private Perfume perfume;

    public WishList(Long id, Member member, Perfume perfume) {
        this.id = id;
        this.member = member;
        this.perfume = perfume;
    }
}

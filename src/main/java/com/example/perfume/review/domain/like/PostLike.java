package com.example.perfume.review.domain.like;

import com.example.perfume.member.domain.Member;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class PostLike {

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @Enumerated(EnumType.ORDINAL)
    private LikeStatus likeStatus;

    public PostLike(Member member, LikeStatus likeStatus) {
        this.member = member;
        this.likeStatus = likeStatus;
    }

}

package com.example.perfume.review.domain.report;

import com.example.perfume.member.domain.Member;
import com.example.perfume.review.domain.review.PerfumeReviewBoard;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class ReportDetail {

    @ManyToOne(fetch = FetchType.LAZY)
    private Member reporter;

    @ManyToOne(fetch = FetchType.LAZY)
    private PerfumeReviewBoard reportedPost;

    @ManyToOne(fetch = FetchType.LAZY)

    private Member reportedPostUser;

    public ReportDetail(Member reporter, PerfumeReviewBoard reportedPost, Member reportedPostUser) {
        this.reporter = reporter;
        this.reportedPost = reportedPost;
        this.reportedPostUser = reportedPostUser;
    }

}

package com.example.perfume.review.domain.report;

import com.example.perfume.member.domain.Member;
import com.example.perfume.review.domain.review.PerfumeReviewBoard;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class ReportDetail {

    @ManyToOne(fetch = FetchType.LAZY)
    private Member administrator;

    private Long postId;

    private Long reportedPostUserId;

    @Builder
    public ReportDetail(Member administrator, Long postId, Long reportedPostUserId) {
        this.administrator = administrator;
        this.postId = postId;
        this.reportedPostUserId = reportedPostUserId;
    }

}

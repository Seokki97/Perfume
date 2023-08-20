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

    private Long administrator;

    private Long postId;

    private Long reportedPostUserId;

    public ReportDetail(Long administrator, Long postId, Long reportedPostUserId) {
        this.administrator = administrator;
        this.postId = postId;
        this.reportedPostUserId = reportedPostUserId;
    }

}

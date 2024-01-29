package com.example.perfume.review.dto.review.responseDto;

import com.example.perfume.review.domain.review.PerfumeReviewBoard;
import java.util.List;
import lombok.Getter;

@Getter
public class ReviewAnalyzeResponse {

    private List<PerfumeReviewBoard> analyzedData;

    public ReviewAnalyzeResponse(final List<PerfumeReviewBoard> analyzedData) {
        this.analyzedData = analyzedData;
    }
}

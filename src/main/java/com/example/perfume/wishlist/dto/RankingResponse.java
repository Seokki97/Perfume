package com.example.perfume.wishlist.dto;

import com.example.perfume.perfume.domain.Perfume;
import java.util.Objects;
import lombok.Getter;

@Getter
public class RankingResponse {

    private Perfume perfume;

    private Long count;

    public RankingResponse() {
    }

    public RankingResponse(Perfume perfume, Long count) {
        this.perfume = perfume;
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof RankingResponse)) {
            return false;
        }
        RankingResponse r = (RankingResponse) o;
        return r.perfume.getPerfumeName().equals(this.perfume.getPerfumeName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(perfume);
    }

    public static RankingResponse makeRankingResponseObject(Perfume perfume, Long count) {
        return new RankingResponse(perfume, count);
    }
}

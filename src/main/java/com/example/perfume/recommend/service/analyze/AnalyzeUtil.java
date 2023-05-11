package com.example.perfume.recommend.service.analyze;

import com.example.perfume.recommend.dto.AnalyzeResponse;
import com.example.perfume.recommend.exception.RecommendNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalyzeUtil {

    public void isCountingNumberExist(Long maxCount) {
        if (maxCount < 1) {
            throw new RecommendNotFoundException();
        }
    }

    public Long countElement(List<String> elementList, int i) {
        return elementList.stream().filter(x -> elementList.get(i).matches(x)).count();
    }

    public AnalyzeResponse countPerfumeList(List<String> elementList) {
        Long maxCount = 0L;
        String elementName = "";
        int perfumeNameListSize = elementList.size();
        for (int i = 0; i < perfumeNameListSize; i++) {
            Long count = countElement(elementList, i);
            if (count > maxCount) {
                elementName = elementList.get(i);
                maxCount = count;
            }
        }
        isCountingNumberExist(maxCount);

        return AnalyzeResponse.builder()
                .elementName(elementName)
                .count(maxCount)
                .build();
    }
}

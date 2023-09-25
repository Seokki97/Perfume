package com.example.perfume.recommend.service.analyze;

import com.example.perfume.recommend.dto.analyze.AnalyzeResponse;
import com.example.perfume.recommend.exception.RecommendNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AnalyzeUtil {

    public void isCountingNumberExist(Long maxCount) {
        if (maxCount < 1) {
            throw new RecommendNotFoundException();
        }
    }

    public Map<String, Long> countElement(List<String> elementList) {
        Map<String, Long> elementCountMap = new HashMap<>();

        for (String element : elementList) {
            elementCountMap.put(element, elementCountMap.getOrDefault(element, 0L) + 1);
        }
        return elementCountMap;
    }

    public AnalyzeResponse countPerfumeList(List<String> elementList) {
        Long maxCount = 0L;
        String elementName = "";

        Map<String, Long> elementCountMap = countElement(elementList);

        for (Map.Entry<String, Long> entry : elementCountMap.entrySet()) {
            if (entry.getValue() > maxCount) {
                elementName = entry.getKey();
                maxCount = entry.getValue();
            }
        }
        isCountingNumberExist(maxCount);

        return new AnalyzeResponse(elementName, maxCount);
    }
}

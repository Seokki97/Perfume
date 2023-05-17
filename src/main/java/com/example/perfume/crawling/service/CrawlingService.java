package com.example.perfume.crawling.service;

import com.example.perfume.crawling.domain.perfume.CrawlingFeature;
import com.example.perfume.crawling.domain.perfume.CrawlingPerfume;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CrawlingService {

    private static final int ZERO = 0;

    private static final String PERFUME_NAME_CSS_QUERY_RANGE = "section.thumbnail a img";
    private static final String PERFUME_NAME_ATTRIBUTE_KEY = "alt";
    private static final String PERFUME_FEATURE_CSS_QUERY_RANGE = "ul.spec li.summary";
    public Document connectAndGetDocument(String targetUrl) throws IOException {
        Connection connection = Jsoup.connect(targetUrl);
        Document document = connection.get();

        return document;
    }


    public List<CrawlingPerfume> crawPerfumeName(String targetUrl) throws IOException {
        Elements setRange = connectAndGetDocument(targetUrl).select(PERFUME_NAME_CSS_QUERY_RANGE);
        List<CrawlingPerfume> perfumeNameList = new ArrayList<>();
        for (int firstIndexOfList = ZERO; firstIndexOfList < setRange.size(); firstIndexOfList++) {
            CrawlingPerfume perfume = new CrawlingPerfume(setRange.get(firstIndexOfList).attr(PERFUME_NAME_ATTRIBUTE_KEY));
            perfumeNameList.add(perfume);
        }
        return perfumeNameList;
    }

    public List<CrawlingFeature> crawPerfumeFeature(String targetUrl) throws IOException {
        Elements setRange = connectAndGetDocument(targetUrl).select(PERFUME_FEATURE_CSS_QUERY_RANGE);

        List<CrawlingFeature> featureList = new ArrayList<>();
        for (int firstIndexOfList = ZERO; firstIndexOfList < setRange.size(); firstIndexOfList++) {
            String replaceString = setRange.get(firstIndexOfList).text().replaceAll(",", "");
            CrawlingFeature perfumeFeature = new CrawlingFeature(replaceString);
            featureList.add(perfumeFeature);

        }
        return featureList;
    }

}

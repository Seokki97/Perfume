package com.example.perfume.crawling.service;

import com.example.perfume.crawling.domain.PerfumeFeature;
import com.example.perfume.crawling.domain.PerfumeCrawling;
import com.example.perfume.crawling.domain.PerfumeImage;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CrawlingService {

    private static final int LOOP_ZERO = 0;

    private static final String PERFUME_NAME_CSS_QUERY_RANGE = "section.thumbnail a img";
    private static final String PERFUME_NAME_ATTRIBUTE_KEY = "alt";
    private static final String PERFUME_FEATURE_CSS_QUERY_RANGE = "ul.spec li.summary";
    private static final String IMG_URL = "src";

    public Document connectAndGetDocument(String targetUrl) throws IOException {
        Connection connection = Jsoup.connect(targetUrl);
        Document document = connection.get();

        return document;
    }


    public List<PerfumeCrawling> crawPerfumeName(String targetUrl) throws IOException {
        Elements setRange = connectAndGetDocument(targetUrl).select(PERFUME_NAME_CSS_QUERY_RANGE);
        List<PerfumeCrawling> perfumeCrawlingNameList = new ArrayList<>();
        for (int firstIndexOfList = LOOP_ZERO; firstIndexOfList < setRange.size(); firstIndexOfList++) {
            PerfumeCrawling perfumeCrawling = new PerfumeCrawling(setRange.get(firstIndexOfList).attr(PERFUME_NAME_ATTRIBUTE_KEY));
            perfumeCrawlingNameList.add(perfumeCrawling);
        }
        return perfumeCrawlingNameList;
    }

    public List<PerfumeFeature> crawPerfumeFeature(String targetUrl) throws IOException {
        Elements setRange = connectAndGetDocument(targetUrl).select(PERFUME_FEATURE_CSS_QUERY_RANGE);

        List<PerfumeFeature> perfumeFeatureList = new ArrayList<>();
        for (int firstIndexOfList = LOOP_ZERO; firstIndexOfList < setRange.size(); firstIndexOfList++) {
            String replaceString = setRange.get(firstIndexOfList).text().replaceAll(",", "");
            PerfumeFeature perfumeFeature = new PerfumeFeature(replaceString);
            perfumeFeatureList.add(perfumeFeature);

        }
        return perfumeFeatureList;
    }

    public List<PerfumeImage> crawPerfumeImageUrl(String targetUrl) throws IOException {
        Elements setRange = connectAndGetDocument(targetUrl).select(PERFUME_NAME_CSS_QUERY_RANGE);
        List<PerfumeImage> perfumeImageList = new ArrayList<>();
        for (int firstIndexOfList = LOOP_ZERO; firstIndexOfList < setRange.size(); firstIndexOfList++) {
            PerfumeImage perfumeImage = new PerfumeImage(setRange.get(firstIndexOfList).attr(IMG_URL));
            perfumeImageList.add(perfumeImage);
        }
        return perfumeImageList;

    }
}

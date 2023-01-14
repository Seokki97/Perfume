package com.example.perfume.crawling.service;

import com.example.perfume.crawling.domain.Feature;
import com.example.perfume.crawling.domain.Perfume;
import com.example.perfume.crawling.domain.URL;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClawlingService {

    private static final int ZERO = 0;

    public Document connectAndGetDocument(String url) throws IOException {
        Connection connection = Jsoup.connect(url);
        Document document = connection.get();

        return document;
    }


    public List<Perfume> crawPerfumeName(String url) throws IOException {
        Elements setRange = connectAndGetDocument(url).select("section.thumbnail a img");
        List<Perfume> perfumeNameList = new ArrayList<>();
        for (int firstIndexOfList = ZERO; firstIndexOfList < setRange.size(); firstIndexOfList++) {
            Perfume perfume = new Perfume(setRange.get(firstIndexOfList).attr("alt"));
            perfumeNameList.add(perfume);
        }
        return perfumeNameList;
    }

    public List<Feature> crawPerfumeFeature(String url) throws IOException {
        Elements setRange = connectAndGetDocument(url).select("ul.spec li.summary");

        List<Feature> featureList = new ArrayList<>();
        for (int firstIndexOfList = ZERO; firstIndexOfList < setRange.size(); firstIndexOfList++) {
            String replaceString = setRange.get(firstIndexOfList).text().replaceAll(",","");
            Feature feature = new Feature(replaceString);
            featureList.add(feature);

        }
        return featureList;
    }

}

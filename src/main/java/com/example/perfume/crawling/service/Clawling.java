package com.example.perfume.crawling.service;

import com.example.perfume.crawling.domain.Feature;
import com.example.perfume.crawling.domain.Perfume;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Clawling {
    private static final String URL = "https://perfumegraphy.com/category/citrus/674/";
    private static final int ZERO = 0;

    public Document connectAndGetDocument() throws IOException {
        Connection connection = Jsoup.connect(URL);
        Document document = connection.get();

        return document;
    }

    public List<Perfume> crawPerfumeName() throws IOException {
        Elements setRange = connectAndGetDocument().select("section.thumbnail a img");
        List<Perfume> perfumeNameList = new ArrayList<>();
        for (int firstIndexOfList = ZERO; firstIndexOfList < setRange.size(); firstIndexOfList++) {
            Perfume perfume = new Perfume(setRange.get(firstIndexOfList).attr("alt"));
            perfumeNameList.add(perfume);
        }
        return perfumeNameList;
    }

    public List<Feature> crawPerfumeFeature() throws IOException {
        Elements setRange = connectAndGetDocument().select("ul.spec li.summary");

        List<Feature> featureList = new ArrayList<>();
        for (int firstIndexOfList = ZERO; firstIndexOfList < setRange.size(); firstIndexOfList++) {
            Feature feature = new Feature(setRange.get(firstIndexOfList).text());
            featureList.add(feature);
        }
        return featureList;
    }

}

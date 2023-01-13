package com.example.perfume.crawling.service;

import com.example.perfume.crawling.domain.Perfume;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Clawling {
    private static final String URL = "https://perfumegraphy.com/category/citrus/674/";
    private static final int ZERO = 0;

    public Document connectAndGetDocument() throws IOException {
        Connection connection = Jsoup.connect(URL);
        Document document = connection.get();

        return document;
    }

    public List<String> CrawPerfumeName() throws IOException {
        Elements setRange = connectAndGetDocument().select("section.thumbnail a img");
        List<String> perfumeNameList = new ArrayList<>();
        for (int firstIndexOfList = ZERO; firstIndexOfList < setRange.size(); firstIndexOfList++) {
            perfumeNameList.add(setRange.get(firstIndexOfList).attr("alt"));
        }
        return perfumeNameList;
    }

    public List<String> CrawPerfumeFeature() throws IOException{
        Elements setRange = connectAndGetDocument().select("ul.spec li.summary");

        List<String> perfumeFeatureList = new ArrayList<>();
        for(int firstIndexOfList = ZERO; firstIndexOfList < setRange.size(); firstIndexOfList++){

          perfumeFeatureList.add(setRange.get(firstIndexOfList).text());

        }
        return perfumeFeatureList;
    }
}

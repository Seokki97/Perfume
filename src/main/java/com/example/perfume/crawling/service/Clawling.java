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

    public Document connectAndGetDocument() throws IOException {
        Connection connection = Jsoup.connect(URL);
        Document document = connection.get();

        return document;
    }

    public List<Perfume> CrawPerfumeName() throws IOException {
        Elements titles = connectAndGetDocument().select("section.thumbnail a img");
        List<Perfume> perfumeList = new ArrayList<>();
        for (int i = 0; i < titles.size(); i++) {
            Perfume setPerfumeName = new Perfume(titles.get(i).attr("alt"));
            perfumeList.add(setPerfumeName);
            System.out.println(perfumeList.get(i).toString());
        }
        return perfumeList;
    }

}

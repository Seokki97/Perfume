package com.example.perfume.crawling.service;



import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import org.jsoup.nodes.Element;
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

    public void CrawWebSource() throws IOException {
        Elements titles = connectAndGetDocument().select("section.thumbnail a img");


        for(int i = 0 ; i < titles.size(); i++){
            System.out.println(titles.get(i).attr("alt"));

        }

    }
/*
    public List<Element> extractPerfumeName(){
        List<Element> perfumeList = new ArrayList<>();
        for(int i=0 ; i<content.size(); i++){
            perfumeList.add(content.get(i));
            System.out.println(content.get(i));
        }
        return perfumeList;
        }

 */

}

package com.example.perfume.crawling;


import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;

public class Clawling {
    private static final String URL = "https://perfumegraphy.com/category/citrus/674/";

    public Document connectAndGetDocument() throws IOException {
        Connection connection = Jsoup.connect(URL);
        Document document = connection.get();

        return document;
    }

    public void CrawWebSource() throws IOException {
        Elements urlElements = connectAndGetDocument().getElementsByClass("thumbnail");
        for(Element element : urlElements){
            System.out.println(element);
        }
    }

}

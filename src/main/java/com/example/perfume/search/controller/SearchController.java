package com.example.perfume.search.controller;

import com.example.perfume.search.service.SearchService;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/perfume")
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/search")
    public ResponseEntity<String> searchPerfumeInformation(@RequestParam("perfumeName") String perfumeName) {
        return new RestTemplate()
                .exchange(searchService.generateQueryMessage(perfumeName), HttpMethod.GET,
                        searchService.generateRequestEntity(), String.class);
    }
}

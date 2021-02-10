package com.example.textsearchservice.controllers;

import com.example.textsearchservice.TrieNode;
import com.example.textsearchservice.services.TrieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import java.util.List;


@RestController
public class SearchController {

    @Autowired
    TrieService trieService;

    @GetMapping(value = "/search")
    public Flux<List<String>> getCompletions(@RequestParam(value = "complete") String complete){
        List<String> results = trieService.getAutoSuggestions(complete);
        return Flux.just(results);
    }

    @PostMapping(value = "/search")
    public Mono<String> addTerm(@RequestBody String term){
        trieService.addTerm(term);
        return Mono.just("ok");
    }
}

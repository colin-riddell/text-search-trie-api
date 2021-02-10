package com.example.textsearchservice.controllers;

import com.example.textsearchservice.TrieNode;
import com.example.textsearchservice.services.TermService;
import com.example.textsearchservice.services.TrieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import java.util.List;


@RestController
public class SearchController {

    @Autowired
    TermService termService;

    @GetMapping(value = "/search")
    public Mono<List<String>> getCompletions(@RequestParam(value = "complete") String complete){
        return  termService.getSuggestions(complete);
    }

//    @PostMapping(value = "/search")
//    public Mono<String> addTerm(@RequestBody String term){
//        trieService.addTerm(term);
//        return Mono.just("ok");
//    }
}

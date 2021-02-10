package com.example.textsearchservice.services;

import com.example.textsearchservice.client.Client;
import com.example.textsearchservice.models.Term;
import com.example.textsearchservice.repositories.TermRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


@Service
public class TermService {

    TermRepository termRepository;

    @Autowired
    TrieService trieService;


    public TermService(Client client, TermRepository termRepository){
        this.termRepository = termRepository;
        List<Term> terms = client.getAllWords();

        termRepository.saveAll(terms).subscribe();
    }

    /**
     * Given an input prefix term like app return all the
     * associated completions of that prefix.
     *
     * This service caches into a Trie. If the cache misses
     * it will hit the DB.
     * @param input
     * @return
     */
    public Mono<List<String>> getSuggestions(String input){
        // check the trie structure for the input
        if (trieService.search(input)){
            // if it has data - return the auto completion
            System.out.printf("Hitting the cache for term: %s \n", input);
            return Mono.just(trieService.getAutoSuggestions(input));
        }

        System.out.printf("Cache miss for term: %s \n", input);

        // if it doesn't do the query to get the related terms
        Flux<Term> foundTerms = termRepository.findByTermStartingWith(input);

        // add the term(s) to the trie
        // return the result
        return  foundTerms
                .map(t -> trieService.addTerm(t.getTerm()))
                .collectList();
    }
}

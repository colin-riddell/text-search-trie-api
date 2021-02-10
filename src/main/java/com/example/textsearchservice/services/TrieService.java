package com.example.textsearchservice.services;

import com.example.textsearchservice.Trie;
import com.example.textsearchservice.TrieNode;
import com.example.textsearchservice.client.Client;
import com.example.textsearchservice.models.Term;
import com.example.textsearchservice.repositories.TermRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrieService {


    static TrieNode root;

    @Autowired
    public TrieService() {
        root = new TrieNode();
    }

    public List<String> getAutoSuggestions(String query){
        return Trie.getAutoSuggestions(root, query);
    }

    public String addTerm(String term){
        Trie.insert(root, term);
        return term;

    }

    public boolean search(String term){
        return Trie.search(root, term);
    }
}

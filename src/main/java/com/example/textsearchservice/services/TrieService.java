package com.example.textsearchservice.services;

import com.example.textsearchservice.Trie;
import com.example.textsearchservice.TrieNode;
import com.example.textsearchservice.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrieService {


    static TrieNode root;

    @Autowired
    public TrieService(Client client) {

        List<String> keys = client.getAllWords();

        root = new TrieNode();

        // Construct trie
        for (String key : keys) {
            Trie.insert(root, key);
        }

    }

//    @Cacheable("suggest")
    public List<String> getAutoSuggestions(String query){
        return Trie.getAutoSuggestions(root, query);
    }

    public void addTerm(String term){
        Trie.insert(root, term);
    }
}

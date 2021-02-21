package com.example.textsearchservice;

import com.example.textsearchservice.trie.Trie;
import com.example.textsearchservice.trie.TrieNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TrieTests {

    TrieNode root;

    @Test
    public void canInsertSearchTrie(){
        root = new TrieNode();
        Trie.insert(root, "cap");
        Trie.insert(root, "call");
        Trie.insert(root, "california");
        Trie.insert(root, "apple");

        assertTrue(Trie.search(root, "cap"));
    }
}

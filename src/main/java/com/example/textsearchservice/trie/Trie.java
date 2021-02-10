package com.example.textsearchservice.trie;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Trie {

    static final int ALPHABET_SIZE = 26;

    /**
     * Strips carriage returns if present
     * @param in
     * @return
     */
    public static String stripCarriageReturn(String in){
        return Optional.ofNullable(in)
                .filter(str -> str.length() != 0)
                .map(str -> str.replaceAll("\\n", ""))
                .map(str -> str.replaceAll("\\r", ""))
                .orElse(in);
    }


    /**
     * If not present, inserts key into trie
     * If the key is prefix of trie node, just marks leaf node
     *
     * @param root
     * @param key
     */
    public static void insert(TrieNode root, String key) {
        key = stripCarriageReturn(key);
        int length = key.length();
        int index;

        TrieNode node = root;

        for (int level = 0; level < length; level++) {
            index = key.charAt(level) - 'a';
            if (node.children[index] == null)
                node.children[index] = new TrieNode();

            node = node.children[index];
        }

        // mark last node as leaf
        node.isEndOfWord = true;
    }

    /**
     * Returns true if key presents in trie, else false
     */
    public static boolean search(TrieNode root, String key) {
        int length = key.length();
        int index;
        TrieNode node = root;

        for (int level = 0; level < length; level++) {
            index = key.charAt(level) - 'a';

            if (node.children[index] == null)
                return false;

            node = node.children[index];
        }

        return (node != null && node.isEndOfWord);
    }



    /**
     *  Returns false if current node has a child
     *  If all children are NULL, return true.
     * @param root
     * @return
     */
    static boolean isLastNode(TrieNode root) {
        for (int i = 0; i < ALPHABET_SIZE; i++)
            if (root.children[i] != null)
                return false;
        return true;
    }

    public static String removeLastCharOptional(String s) {
        return Optional.ofNullable(s)
                .filter(str -> str.length() != 0)
                .map(str -> str.substring(0, str.length() - 1))
                .orElse(s);
    }

    static List<String> suggestionsRec(TrieNode root, String currPrefix, List<String> results)
    {
        // found a string in Trie with the given prefix
        if (root.isEndOfWord) {
            results.add(currPrefix);
        }

        // All children struct node pointers are NULL
//        if (isLastNode(root))
//            return null;

        for (int i = 0; i < ALPHABET_SIZE; i++) {
            if (root.children[i] != null) {
                // append current character to currPrefix string
                char appending = (char) (97 + i);
                currPrefix += appending;

                // recur over the rest
                results = suggestionsRec(root.children[i], currPrefix, results);
                // remove last character
                currPrefix = removeLastCharOptional(currPrefix);
            }
        }
        return results;
    }

    public static List<String> getAutoSuggestions(TrieNode root, String query)  {
        query = stripCarriageReturn(query);

        TrieNode node = root;

        // Check if prefix is present and find the
        // the node (of last level) with last character
        // of given string.
        int n = query.length();
        for (int level = 0; level < n; level++)
        {
            int index = query.charAt(level) - 'a';

            // no string in the Trie has this prefix
            if (node.children[index] == null)
                return List.of();

            node = node.children[index];
        }

        // If prefix is present as a word.
        boolean isWord = (node.isEndOfWord);

        // If prefix is last node of tree (has no children)
        boolean isLast = isLastNode(node);

        /*
         If prefix is present as a word, but
         there is no subtree below the last matching node.
         */
//        if (isWord && isLast) {
//            return -1;
//        }


        List<String> results = new ArrayList<>();
        List<String> found = new ArrayList<>();
        if (!isLast)  {
            found = suggestionsRec(node, query, results);
        }
        return found;
    }



}

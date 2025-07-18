package org.example;

import java.util.Map;
import java.util.HashMap;

/**
 * 
 */
public class Trie {

    // Represents a single node in the Trie
    // Each node contains a map of its child nodes 
    // and a boolean flag indicating if it marks the end of a word
    private static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isWord = false;
    }

    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }







    
}

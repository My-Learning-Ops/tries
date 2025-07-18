package org.example;

import java.util.Map;
import java.util.HashMap;

/**
 * A simple implentation of a Trie data structure.
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

    // Constructs a Trie with an empty root node
    public Trie() {
        root = new TrieNode();
    }


    /**
     * Inserts a word into the Trie
     * Each character is added as a child node if it dosent exist already
     * 
     * @param word The word being added to the Trie
     */
    public void insert(String word) {
        TrieNode currentNode = root;

        // Iterate through each character in the word
        for (char ch : word.toCharArray()) {
            // If the char is not already a child, create a new node
            currentNode.children.putIfAbsent(ch, new TrieNode());
            // Move to the child node
            currentNode = currentNode.children.get(ch);
        }
        // Mark the end of the word
        currentNode.isWord = true;
    }

    /**
     * Checks if any word in the Trie starts with the given prefix
     * 
     * @param prefix The prefix to search for in the Trie
     * @return true if any word starts with the prefix, false otherwise
     */
    public boolean startsWith(String prefix) {
        // Check if the Trie contains any word that starts with the given prefix
        return findNode(prefix) != null;
    }

    /**
     * Searches for a complete word in the Trie
     * 
     * @param word The word to search for in the Trie
     * @return true if the word exists in the Trie, false otherwise
     */
    public boolean search(String word) {
        TrieNode node = findNode(word);
        // If the node is found and it marks the end of a word, return true
        return node != null && node.isWord;
    }

    /**
     * Returns a string representation of all words in the Trie
     */
    public String toString() {
        StringBuilder output = new StringBuilder();
        collectWords(root, new StringBuilder(), output);
        return output.toString();
    }



    /**
     * Traverses the Trie according toi the input string provided,
     * and returns the last node reached, or null if path dosent exist
     * 
     * @param input The input string to traverse the Trie
     * @return The last TrieNode reached following the input string,
     *         or null if the path does not exist in the Trie
     */
    private TrieNode findNode(String input) {
        TrieNode currentNode = root;

        // Loop through each char in input string
        for (char ch : input.toCharArray()) {
            // If the character is not found, return null
            if (!currentNode.children.containsKey(ch)) {
                return null;
            }
            // Move to the child node
            currentNode = currentNode.children.get(ch);
        }
        return currentNode;
    }

    /**
     * Recursively collects all words in the Trie starting from the given node
     * 
     * @param node The current trie node being visited
     * @param prefix The current prefix built so far
     * @param output StringBuilder to collect the words found
     */
    private void collectWords(TrieNode node, StringBuilder prefix, StringBuilder output) {

        if (node.children.isEmpty()) {
            return;
        }
        // Recursively collect words from child nodes
        for (Map.Entry<Character, TrieNode> entry : node.children.entrySet()) {
            // Current character and child node
            char ch = entry.getKey();
            TrieNode childNode = entry.getValue();

            // Add current character to prefix
            prefix.append(ch);
            
            // Recurse into the child
            collectWords(childNode, prefix, output);

            // Backtrack after recursion to remove last char
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }









    
}

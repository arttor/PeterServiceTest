/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.peterService.peterServiceTest.structures;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author artem
 */
public class Trie {

    static class TrieNode {

        private final Set<Integer> value = new LinkedHashSet<>();
        private final Map<Character, TrieNode> children = new HashMap<>();
    }

    private final TrieNode root = new TrieNode();

    public void put(String key, Integer value) {
        if (key != null && key.length() > 0) {
            TrieNode node = root;
            for (Character c : key.toLowerCase().toCharArray()) {
                TrieNode nextNode = node.children.get(c);
                if (nextNode == null) {
                    nextNode = new TrieNode();
                    node.children.put(c, nextNode);
                }
                node = nextNode;
            }
            node.value.add(value);
        }
    }

    public Set<Integer> get(String key) {
        if (key != null && key.length() > 0) {
            TrieNode node = root;
            for (Character c : key.toLowerCase().toCharArray()) {
                node = node.children.get(c);
                if (node == null) {
                    return null;
                }
            }
            return node.value;
        }
        return null;
    }
}

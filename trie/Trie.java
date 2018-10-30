package trie;

class TrieNode{
	public boolean isWord;
	public TrieNode() {}
	TrieNode[] children = new TrieNode[26];
}
public class Trie {
	private TrieNode root;
    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
        	char c = word.charAt(i);
        	if (node.children[c - 'a'] == null) {
        		node.children[c - 'a'] = new TrieNode();
        	}
        	node = node.children[c - 'a'];
        }
        node.isWord = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
    	TrieNode node = root;
    	for (int i = 0; i < word.length(); i++) {
    		char c = word.charAt(i);
    		if (node.children[c - 'a'] == null) return false;
    		node = node.children[c - 'a'];
    	}
        return node.isWord;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
    	TrieNode node = root;
    	for (int i = 0; i < prefix.length(); i++) {
    		char c = prefix.charAt(i);
    		if (node.children[c - 'a'] == null) return false;
    		node = node.children[c - 'a'];
    	}
        return true;
    }
}
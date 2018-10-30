package trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * Given a list of words (without duplicates), please write a program that returns all concatenated words 
 * in the given list of words.

	A concatenated word is defined as a string that is comprised entirely of at least two shorter words in the 
	given array.
	
	Example:
	Input: ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
	
	Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]
	
	Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats"; 
	 "dogcatsdog" can be concatenated by "dog", "cats" and "dog"; 
	"ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".
 */
public class ConcatenateWords {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> result = new ArrayList<String>();
        Set<String> preWords = new HashSet<String>();
        Arrays.sort(words, new Comparator<String>() {
            public int compare (String s1, String s2) {
                return s1.length() - s2.length();
            }
        });
        
        for (int i = 0; i < words.length; i++) {
            if (canForm(words[i], preWords)) {
                result.add(words[i]);
            }
            preWords.add(words[i]);
        }
        
        return result;
    }
	
    private static boolean canForm(String word, Set<String> dict) {
        if (dict.isEmpty()) return false;
		boolean[] dp = new boolean[word.length() + 1];
		dp[0] = true;
		for (int i = 1; i <= word.length(); i++) {
		    for (int j = 0; j < i; j++) {
				if (!dp[j]) continue;
				if (dict.contains(word.substring(j, i))) {
				    dp[i] = true;
				    break;
				}
		    }
		}
		return dp[word.length()];
    }
    
    class TrieNode {
        TrieNode[] children;
        String word;
        boolean isEnd;
        boolean combo; //if this word is a combination of simple words
        boolean added; //if this word is already added in result
        public TrieNode() {
            this.children = new TrieNode[26];
            this.word = new String();
            this.isEnd = false;
            this.combo = false;
            this.added = false;
        }
    }
    private void addWord(String str) {
        TrieNode node = root;
        for (char ch : str.toCharArray()) {
            if (node.children[ch - 'a'] == null) {
                node.children[ch - 'a'] = new TrieNode();
            }
            node = node.children[ch - 'a'];
        }
        node.isEnd = true;
        node.word = str;
    }
    private TrieNode root;
    private List<String> result;
    public List<String> findAllConcatenatedWordsInADictTrie(String[] words) {
        root = new TrieNode();
        for (String str : words) {
            if (str.length() == 0) {
                continue;
            }
            addWord(str);
        }
        result = new ArrayList<String>();
        dfs(root, 0);
        return result;
    }
    private void dfs(TrieNode node, int multi) {
    	//multi counts how many single words combined in this word
        if(node.isEnd && !node.added && multi > 1) {
            node.combo = true;
            node.added = true;
            result.add(node.word);
        }
        searchWord(node, root, multi);
    }
    private void searchWord(TrieNode node1, TrieNode node2, int multi) {
        if (node2.combo) {
            return;
        }
        if (node2.isEnd) {
            //take the pointer of node2 back to root
            dfs(node1, multi + 1);
        }
        for (int  i = 0; i < 26; i++) {
            if (node1.children[i] != null && node2.children[i] != null) {
                searchWord(node1.children[i], node2.children[i], multi);
            }
        }
    }
    
}

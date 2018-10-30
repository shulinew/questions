package trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 *  Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.

Example 1:
Given words = ["bat", "tab", "cat"]
Return [[0, 1], [1, 0]]
The palindromes are ["battab", "tabbat"]

Example 2:
Given words = ["abcd", "dcba", "lls", "s", "sssll"]
Return [[0, 1], [1, 0], [3, 2], [2, 4]]
The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]
 */
public class PalindromePair {
	public List<List<Integer>> palindromePairs1(String[] words) {
	    List<List<Integer>> ret = new ArrayList<List<Integer>>(); 
	    if (words == null || words.length < 2) return ret;
	    Map<String, Integer> map = new HashMap<String, Integer>();
	    for (int i=0; i<words.length; i++) map.put(words[i], i);
	    for (int i=0; i<words.length; i++) {
	        for (int j=0; j<=words[i].length(); j++) { // notice it should be "j <= words[i].length()"
	            String str1 = words[i].substring(0, j);
	            String str2 = words[i].substring(j);
	            if (isPalindrome(str1)) {
	                String str2rvs = new StringBuilder(str2).reverse().toString();
	                if (map.containsKey(str2rvs) && map.get(str2rvs) != i) {
	                    List<Integer> list = new ArrayList<Integer>();
	                    list.add(map.get(str2rvs));
	                    list.add(i);
	                    ret.add(list);
	                }
	            }
	            if (isPalindrome(str2)) {
	                String str1rvs = new StringBuilder(str1).reverse().toString();
	                if (map.containsKey(str1rvs) && map.get(str1rvs) != i && str2.length()!=0) { 
	                    List<Integer> list = new ArrayList<Integer>();
	                    list.add(i);
	                    list.add(map.get(str1rvs));
	                    ret.add(list);
	                }
	            }
	        }
	    }
	    return ret;
	}

	private boolean isPalindrome(String str) {
		int left = 0;
		int right = str.length() - 1;
		while (left <= right) {
			if (str.charAt(left++) != str.charAt(right--)) {
				return false;
			}
		}
		return true;
	}
	
	
	/////////////////////////
	
	//https://discuss.leetcode.com/topic/39585/o-n-k-2-java-solution-with-trie-structure-n-total-number-of-words-k-average-length-of-each-word
	class TrieNode {
	    TrieNode[] next;
	    int index;
	    List<Integer> list;
	    	
	    TrieNode() {
	    	next = new TrieNode[26];
	    	index = -1;
	    	list = new ArrayList<Integer>();
	    }
	}
	    
	public List<List<Integer>> palindromePairs(String[] words) {
	    List<List<Integer>> res = new ArrayList<List<Integer>>();

	    TrieNode root = new TrieNode();
	    for (int i = 0; i < words.length; i++) addWord(root, words[i], i);
	    for (int i = 0; i < words.length; i++) search(words, i, root, res);
	    
	    return res;
	}
	    
	private void addWord(TrieNode root, String word, int index) {
	    for (int i = word.length() - 1; i >= 0; i--) {
	        int j = word.charAt(i) - 'a';
	    	if (root.next[j] == null) root.next[j] = new TrieNode();
	    	if (isPalindrome(word, 0, i)) root.list.add(index);
	    	root = root.next[j];
	    }
	    	
	    root.list.add(index);
	    root.index = index;
	}
	    
	private void search(String[] words, int i, TrieNode root, List<List<Integer>> res) {
	    for (int j = 0; j < words[i].length(); j++) {	
	    	if (root.index >= 0 && root.index != i && isPalindrome(words[i], j, words[i].length() - 1)) {
	    	    res.add(Arrays.asList(i, root.index));
	    	}
	    		
	    	root = root.next[words[i].charAt(j) - 'a'];
	      	if (root == null) return;
	    }
	    	
	    for (int j : root.list) {
	    	if (i == j) continue;
	    	res.add(Arrays.asList(i, j));
	    }
	}
	    
	private boolean isPalindrome(String word, int i, int j) {
	    while (i < j) {
	    	if (word.charAt(i++) != word.charAt(j--)) return false;
	    }
	    	
	    return true;
	}
}

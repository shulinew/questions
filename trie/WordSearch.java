package trie;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/*
 *  Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

For example,
Given words = ["oath","pea","eat","rain"] and board = 
[
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
Return ["eat","oath"]. 
 */
public class WordSearch {
	
	/*
	 * Intuitively, start from every cell and try to build a word in the dictionary. Backtracking (dfs) is the 
	 * powerful way to exhaust every possible ways. Apparently, we need to do pruning when current character is 
	 * not in any word.
    How do we instantly know the current character is invalid? HashMap?
    How do we instantly know what's the next valid character? LinkedList?
    But the next character can be chosen from a list of characters. "Mutil-LinkedList"?
	Combing them, Trie is the natural choice. Notice that:
    TrieNode is all we need. search and startsWith are useless.
    No need to store character at TrieNode. c.next[i] != null is enough.
    Never use c1 + c2 + c3. Use StringBuilder.
    No need to use O(n^2) extra space visited[m][n].
    No need to use StringBuilder. Storing word itself at leaf node is enough.
    No need to use HashSet to de-duplicate. Use "one time search" trie.

	 */
	public List<String> findWords(char[][] board, String[] words) {
	    List<String> res = new ArrayList<String>();
	    TrieNode1 root = buildTrie(words);
	    for (int i = 0; i < board.length; i++) {
	        for (int j = 0; j < board[0].length; j++) {
	            dfs (board, i, j, root, res);
	        }
	    }
	    return res;
	}

	public void dfs(char[][] board, int i, int j, TrieNode1 p, List<String> res) {
	    char c = board[i][j];
	    if (c == '#' || p.children[c - 'a'] == null) return;
	    p = p.children[c - 'a'];
	    if (p.word != null) {   // found one
	        res.add(p.word);
	        p.word = null;     // de-duplicate
	    }

	    board[i][j] = '#';
	    if (i > 0) dfs(board, i - 1, j ,p, res); 
	    if (j > 0) dfs(board, i, j - 1, p, res);
	    if (i < board.length - 1) dfs(board, i + 1, j, p, res); 
	    if (j < board[0].length - 1) dfs(board, i, j + 1, p, res); 
	    board[i][j] = c;
	}

	public TrieNode1 buildTrie(String[] words) {
		TrieNode1 root = new TrieNode1();
	    for (String w : words) {
	    	TrieNode1 p = root;
	        for (char c : w.toCharArray()) {
	            int i = c - 'a';
	            if (p.children[i] == null) p.children[i] = new TrieNode1();
	            p = p.children[i];
	       }
	       p.word = w;
	    }
	    return root;
	}

	class TrieNode1 {
		TrieNode1[] children = new TrieNode1[26];
	    String word;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	public class TrieNode {
		public boolean isWord = false;
		public TrieNode[] children = new TrieNode[26];
		public TrieNode () {}
	}
	boolean [][] flag;
	TrieNode root = new TrieNode();
	public List<String> findwords(char[][] board, String[] words) {
		Set<String> result = new HashSet<String>();
		flag = new boolean[board.length][board[0].length];
		
		addToTrie(words);
		
		for (int i = 0; i < board.length; i++) {
			for (int j =0; j < board[0].length; j++) {
				if (root.children[board[i][j] - 'a'] != null) {
					search(board, i, j, root, "", result);
				}
			}
		}
		return new LinkedList<String>(result);
	}
	private void addToTrie(String[] words) {
		for (String word: words) {
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
	}
	private void search(char[][] board, int i, int j, TrieNode node, String word, Set<String> set) {
		if (i >= board.length || i < 0 || j >= board[i].length || j < 0 || flag[i][j]) {
			return;
		}
		if (node.children[board[i][j] - 'a'] == null) {
			return;
		}
		flag[i][j] = true;
		node = node.children[board[i][j] - 'a'];
		if (node.isWord) {
			set.add(word + board[i][j]);
		}
		search(board, i-1, j, node, word + board[i][j], set);
		search(board, i+1, j, node, word + board[i][j], set);
		search(board, i, j-1, node, word + board[i][j], set);
		search(board, i, j+1, node, word + board[i][j], set);
		flag[i][j] = false;
	}

}

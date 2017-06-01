package trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordDictionary {
    public class TrieNode {
        public TrieNode[] children = new TrieNode[26];
        public String item = "";
    }
    
    private TrieNode root = new TrieNode();

    public void addWord(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (node.children[c - 'a'] == null) {
                node.children[c - 'a'] = new TrieNode();
            }
            node = node.children[c - 'a'];
        }
        node.item = word;
    }

    public boolean search(String word) {
        return match(word.toCharArray(), 0, root);
    }
    
    private boolean match(char[] chs, int k, TrieNode node) {
        if (k == chs.length) return !node.item.equals("");   
        if (chs[k] != '.') {
            return node.children[chs[k] - 'a'] != null && match(chs, k + 1, node.children[chs[k] - 'a']);
        } else {
            for (int i = 0; i < node.children.length; i++) {
                if (node.children[i] != null) {
                    if (match(chs, k + 1, node.children[i])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    Map<Integer, List<String>> map = new HashMap<Integer, List<String>>();
    public void addWord1(String word) {
    	int length = word.length();
    	if (!map.containsKey(length)) {
    		List<String> list = new ArrayList<String>();
    		list.add(word);
    		map.put(length, list);
    	} else {
    		map.get(length).add(word);
    	}
    }
    public boolean search1(String word) {
    	int length = word.length();
    	if (!map.containsKey(length)) {
    		return false;
    	}
    	List<String> list = map.get(length);
    	for (String s: list) {
    		if (isSame(word, s)) {
    			return true;
    		}
    	}
    	return false;
    }
    private boolean isSame(String word, String s) {
		for (int i = 0; i < s.length(); i++) {
			if (word.charAt(i) != '.' && word.charAt(i) != s.charAt(i)) {
				return false;
			}
		}
		return true;
    }
}

/*
public class WordDictionary {
WordNode root = new WordNode();
public void addWord(String word) {
	char chars[] = word.toCharArray();
    addWord(chars, 0, root);
}

private void addWord(char[] chars, int index, WordNode parent) {
	char c = chars[index];
	int idx = c-'a';
    WordNode node = parent.children[idx];
    if (node == null){
    	node = new WordNode();
    	parent.children[idx]=node;
    }
    if (chars.length == index+1){
    	node.isLeaf=true;
    	return;
    }
    addWord(chars, ++index, node);
}


public boolean search(String word) {
	return search(word.toCharArray(), 0, root);				     
}

private boolean search(char[] chars, int index, WordNode parent){
	if (index == chars.length){
		if (parent.isLeaf){
			return true;
		}
		return false;
	}
	WordNode[] childNodes = parent.children;
	char c = chars[index];
	if (c == '.'){
    	for (int i=0;i<childNodes.length;i++){
    		WordNode n = childNodes[i];
    		if (n !=null){
    			boolean b = search(chars, index+1, n);
    			if (b){
    				return true;
    			}
    		}
    	}
    	return false;
	}
	WordNode node = childNodes[c-'a'];
	if (node == null){
		return false;
	}
	return search(chars, ++index, node);
}



private class WordNode{
	boolean isLeaf;
	WordNode[] children = new WordNode[26];
}
}
*/
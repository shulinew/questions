package string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * Given a pattern and a string str, find if str follows the same pattern.
	Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty
	 word in str.
 */
public class WordPattern {
    public boolean wordPattern(String pattern, String str) {
    	if (pattern == null || str == null) return false;
    	String[] strs = str.split(" ");
    	if (pattern.length() != strs.length) return false;
    	Map<Character, String> map = new HashMap<Character, String>();
    	Set<String> addedStr = new HashSet<String>();
    	for (int i =0;i<pattern.length();i++){
    		char c = pattern.charAt(i);
    		String strFromMap = map.get(c);
    		if (strFromMap == null && !addedStr.contains(strs[i])){
    			map.put(c, strs[i]);
    			addedStr.add(strs[i]);
    		}else if (!strs[i].equals(strFromMap)){
    			return false;
    		}
    	}
    	return true;        
    }
    public static void main(String[] args){
    	WordPattern wp = new WordPattern();
    	System.out.println(wp.wordPattern("abba", "dog cat cat fish"));
    	System.out.println(wp.wordPattern("abba", "dog dog dog dog"));
    	System.out.println(wp.wordPattern("abba", "dog cat cat dog"));
    }

}

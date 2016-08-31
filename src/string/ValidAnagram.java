package string;

import java.util.HashMap;
import java.util.Map;

/*
 * Given two strings s and t, write a function to determine if t is an anagram of s.

For example,
s = "anagram", t = "nagaram", return true.
s = "rat", t = "car", return false. 
 */
public class ValidAnagram {
	public boolean isAnagram(String s, String t) {
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		char[] sChars = s.toCharArray();
		char[] tChars = t.toCharArray();
		for (char c: sChars){
			int count = map.getOrDefault(c, 0)+1;
			map.put(c, count);
			
		}
		for (char c: tChars){
			int count = map.getOrDefault(c, 0) -1;
			if (count >= 0){
				map.put(c, count);
			}else{
				return false;
			}
		}
		for (Map.Entry<Character, Integer> entry: map.entrySet()){
			if (entry.getValue() != 0){
				return false;
			}
		}
		return true;
        
    }

}

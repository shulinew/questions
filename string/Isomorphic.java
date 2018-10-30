package string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Isomorphic {
    public boolean isIsomorphic(String s, String t) {
    	Map<Character, Character> sTot = new HashMap<Character,Character>();
    	Map<Character, Character> tTos = new HashMap<Character,Character>();
    	char[] sc = s.toCharArray();
    	char[] tc = t.toCharArray();
    	
    	for (int i = 0;i<sc.length;i++){
    		char sChar = sTot.getOrDefault(sc[i], ' ');
    		char tChar = tTos.getOrDefault(tc[i], ' ');
    		if (sChar == ' ' && tChar == ' '){
    			sTot.put(sc[i], tc[i]);
    			tTos.put(tc[i], sc[i]);
    		} else if (sChar != tc[i] ){
    			return false;
    		} 
    	}
    	return true;  
    }
    
    public boolean isIsomorphic1(String s, String t) {
    	Map<Character, Character> sTot = new HashMap<Character,Character>();
    	Set<Character> tTos = new HashSet<Character>();
    	char[] sc = s.toCharArray();
    	char[] tc = t.toCharArray();
    	
    	for (int i = 0;i<sc.length;i++){
    		char sChar = sTot.getOrDefault(sc[i], ' ');
    		if (sChar == ' ' && !tTos.contains(tc[i])){
    			sTot.put(sc[i], tc[i]);
    			tTos.add(tc[i]);
    		} else if (sChar != tc[i] ){
    			return false;
    		} 
    	}
    	return true;  
    }

}

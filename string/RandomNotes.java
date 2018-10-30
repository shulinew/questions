package string;

import java.util.HashMap;
import java.util.Map;

/*
 * 
 */
public class RandomNotes {
    public boolean canConstruct(String randomNote, String magazine) {
        int [] count = new int[26];
        for (char c: magazine.toCharArray()){
        	count[c - 'a']++;
        }
        for (char c: randomNote.toCharArray()){
        	if (count[c - 'a']-- < 0){
        		return false;
        	}
        }
        return true;
    }
    
    public boolean canConstructs(String randomNote, String magazine){
        Map<Character, Integer> magM = new HashMap<Character, Integer>();
        for (char c:magazine.toCharArray()){
            int newCount = magM.getOrDefault(c, 0)+1;
            magM.put(c, newCount);
        }
        for (char c:randomNote.toCharArray()){
            int newCount = magM.getOrDefault(c,0)-1;
            if (newCount<0)
                return false;
            magM.put(c, newCount);
        }
        return true;
    }

}

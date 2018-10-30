package string;


/*
 * Given a string, find the first non-repeating character in it and return it's index. 
 * If it doesn't exist, return -1. 
 */
public class FirstUnique {
    public int firstUniqChar(String s) {    
        int[] frequency = new int[26];
        for (int i =0; i<s.length();i++){
        	frequency[s.charAt(i) - 'a']++;
        }
        for (int i=0;i<s.length();i++){
        	if (frequency[s.charAt(i) - 'a'] == 1){
        		return i;
        	}
        }
        return -1;
        
    }

}

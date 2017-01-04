package string;

/*
 *  Given a string s and a string t, check if s is subsequence of t.
	You may assume that there is only lower case English letters in both s and t. t is potentially a very long 
	(length ~= 500,000) string, and s is a short string (<=100).
	A subsequence of a string is a new string which is formed from the original string by deleting some 
	(can be none) of the characters without disturbing the relative positions of the remaining characters. 
	(ie, "ace" is a subsequence of "abcde" while "aec" is not). 
 */
public class SubSequence {
    public boolean isSubsequence(String s, String t) {
    	int tp =0, sp=0;
    	while(sp < s.length() && tp < t.length()){
    		if (s.charAt(sp) != t.charAt(tp)){
    			tp++;
    		}else {
    			sp++;
    			tp++;
    		}
    	}
    	if (sp < s.length()){
    		return false;
    	}else{
    		return true;
    	}
    }
    public boolean isSubsequency1(String s, String t){
        int fromIndex = 0;
        for (char c : s.toCharArray()) {
            fromIndex = t.indexOf(c, fromIndex);
            if (fromIndex++ < 0) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args){
    	SubSequence rn = new SubSequence();
    	System.out.println(rn.isSubsequence("ace","abcde"));
    	System.out.println(rn.isSubsequence("aec","abcde"));
    	System.out.println(rn.isSubsequence("abc","ahbgdc"));
    }
}

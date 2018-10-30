package math;


/*
 *  1 is read off as "one 1" or 11.
	11 is read off as "two 1s" or 21.
	21 is read off as "one 2, then one 1" or 1211.
	1211 is read off as "one 1, one 2, two 1s" or 111221

Given an integer n, generate the nth term of the count-and-say sequence.

Note: Each term of the sequence of integers will be represented as a string. 
Input: 4
Output: "1211"
 */
public class CountSay {
	
    public String countAndSay(int n) {
        if(n <= 0) return "-1";
        String result = "1";
        
        for(int i = 1; i < n; i ++) {
            result = build(result);
        }
        return result;
    }
    
    private String build(String result) {
    	StringBuilder sb = new StringBuilder();
    	int pos = 0;
    	while (pos < result.length()) {
    		char currentChar = result.charAt(pos);
    		int count = 0;
    		while (pos < result.length() && result.charAt(pos) == currentChar) {
    			pos++;
    			count++;
    		}
    		sb.append(String.valueOf(count));
    		sb.append(currentChar);
    	}
    	return sb.toString();
    }
    
//    private String build(String result) {
//        StringBuilder builder = new StringBuilder();
//        int p = 0;
//        while(p < result.length()) {
//            char val = result.charAt(p);
//            int count = 0;
//            
//            while(p < result.length() && 
//              result.charAt(p) == val){
//                p ++;
//                count ++;
//            }
//            builder.append(String.valueOf(count));
//            builder.append(val);
//        }
//        return builder.toString();
//    }
    
    public String countAndSay1(int n) {
        String s = "1";
        for(int i = 1; i < n; i++){
            s = countIdx(s);
        }
        return s;
    }
    
    public String countIdx(String s){
        StringBuilder sb = new StringBuilder();
        char c = s.charAt(0);
        int count = 1;
        for(int i = 1; i < s.length(); i++){
            if(s.charAt(i) == c){
                count++;
            }
            else
            {
                sb.append(count);
                sb.append(c);
                c = s.charAt(i);
                count = 1;
            }
        }
        sb.append(count);
        sb.append(c);
        return sb.toString();
    }
    
    //https://leetcode.com/problems/count-and-say/description/

}

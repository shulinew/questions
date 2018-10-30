package string;

/*
 * Write a function that takes a string as input and returns the string reversed.
 */
public class ReverseString {
    public String reverseString(String s) {
    	String reversedString = null;
    	if (s != null){
    		StringBuffer sb = new StringBuffer();
	    	for (int i = s.length() -1; i >=0;i--){
	    		sb.append(s.charAt(i));
	    	}
	    	reversedString = sb.toString();
    	}
    	return reversedString;
        
    }

}

package string;

public class IndexOf {
    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null || (haystack.length() < needle.length())) return -1;
        if (needle.length() == 0) return 0;
        
    	int pos = 0;
    	int j = 0;
    	while (pos < haystack.length()){
			while (pos < haystack.length() && needle.charAt(j) != haystack.charAt(pos)){
	    		pos++;
	    	}
	    	while (j < needle.length()){
	    		if (j+pos >= haystack.length() || needle.charAt(j) != haystack.charAt(pos+j)){
	    			break;
	    		}
	    		j++;
	    	}
	    	if (j == needle.length()) return pos;
	    	pos++;
	    	j = 0;
    	}
    	if (j >= needle.length() || pos >= haystack.length()) return -1;
    	return pos; 
    }
    public static void main (String[] args){
    	String result = "test";
    	result = result.substring(0, result.length()-1);
    	IndexOf rb = new IndexOf();
    	System.out.println(rb.strStr("mississippi","issip"));
    	System.out.println(rb.strStr("mississippi", "a"));
    	System.out.println(rb.strStr("aaa", "aa"));
    	System.out.println(rb.strStr("aaa", "aaaa"));
    	System.out.println(rb.strStr("mississippi", "issipi"));
    }

}

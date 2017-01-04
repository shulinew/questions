package string;

/*
 * Given an input string, reverse the string word by word. 
 * This is good
 * good is This
 */
public class ReverseWordsInString {
    public String reverseWords(String s) {
        if (s == null || !s.contains(" ") || s.length() ==0) return s;
        String [] strs = s.split(" ");
        StringBuffer sb = new StringBuffer();
        for (int i = strs.length -1; i>=0;i--){
        	if (strs[i].length() > 0){
        		sb.append(strs[i].trim());
        		sb.append(" ");
        	}
        }
        return sb.toString().trim();
    }
    public static void main(String [] strs){
    	ReverseWordsInString rs = new ReverseWordsInString();
    	rs.reverseWords("   a   b ");
    }

}

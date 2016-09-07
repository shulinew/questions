package string;

import java.util.Stack;

/*
 * Write a function that takes a string as input and reverse only the vowels of a string.

Example 1:
Given s = "hello", return "holle".

Example 2:
Given s = "leetcode", return "leotcede".

Note:
The vowels does not include the letter "y". 
 */
public class ReverseVowel {
    public String reverseVowels(String s) {
    	char[] vowels = new char[s.length()];
    	int i =0;
    	for (Character c:s.toCharArray()){
    		if (isVowel(c)){
    			vowels[i] = c;
    			i++;
    		}
    	}
    	i--;
    	StringBuffer sb = new StringBuffer();
    	for (Character c:s.toCharArray()){
    		if (isVowel(c)){
    			sb.append(vowels[i]);
    			i--;
    		}else{
    			sb.append(c);
    		}
    	}
    	return sb.toString();
        
    }
    public String reverseVowels1(String s){
    	int left=0, right = s.length()-1;
    	char[] chars = s.toCharArray();
    	while(left < right){
    		if (isVowel(s.charAt(left)) && isVowel(s.charAt(right))){
    			char temp = s.charAt(left);
    			chars[left] = chars[right];
    			chars[right] = temp;
    			left++;
    			right--;
    		}
    		if (!isVowel(s.charAt(left))) left++;
    		if (!isVowel(s.charAt(right))) right--;
    	}
    	return new String(chars);
    }
    public String reverseVowels2(String s){
        char[] chars = s.toCharArray();
        Stack<Character> rev = new Stack<Character>();
        String vowels = "aeiouAEIOU";
        for(char c:chars){
            if(vowels.contains(c+""))
                rev.push(c);
        }
        for(int i=0;i<chars.length;i++){
            if(vowels.contains(chars[i]+""))
                chars[i] = rev.pop();
        }
        return new String(chars);
    }
    private boolean isVowel(char c){
    	if (c == 'a' || c =='e' || c=='i' ||c=='o' ||c=='u'||
    		c == 'A' || c =='E' || c=='I' ||c=='O' ||c=='U'){
    		return true;
    	}
    	return false;
    }

}

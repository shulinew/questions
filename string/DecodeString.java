package string;

import java.util.Stack;

/*
 *  Given an encoded string, return it's decoded string.
	The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated 
	exactly k times. Note that k is guaranteed to be a positive integer.
	
	You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, 
	etc.
	
	Furthermore, you may assume that the original data does not contain any digits and that digits are only for 
	those repeat numbers, k. For example, there won't be input like 3a or 2[4]. 
	
	s = "3[a]2[bc]", return "aaabcbc".
	s = "3[a2[c]]", return "accaccacc".
	s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
 */
public class DecodeString {
	public String decodeString(String st){
		Stack<String> stack = new Stack<String>();
		StringBuffer result = new StringBuffer();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < st.length(); i++){
			if (st.charAt(i) != ']' && st.charAt(i) != '['){
				if (sb.length() != 0 && ((Character.isDigit(st.charAt(i)) && Character.isAlphabetic(sb.toString().charAt(0))) || 
					(Character.isAlphabetic(st.charAt(i)) && Character.isDigit(sb.toString().charAt(0))))){
					stack.push(sb.toString());
					sb = new StringBuffer();
				} 
				sb.append(st.charAt(i));
			} else if (st.charAt(i) == '['){
				stack.push(sb.toString());
				sb = new StringBuffer();
			} else if (st.charAt(i) == ']'){
				StringBuffer temp = new StringBuffer();
				Integer count = null;
				if (sb.length() != 0){
					temp.append(sb);
				} else{
					String str = stack.pop();
					if (Character.isAlphabetic(str.charAt(0))){
						temp.append(str).append(result);
						result = new StringBuffer();
					}else{
						count = new Integer(str);
						temp.append(result);
						result = new StringBuffer();
					}
				}
				if (count == null && Character.isDigit(str.charAt(0))){
					count = new Integer(str);
					result = new StringBuffer();
					result.append(repeatString(temp.toString(), count));
				} 
				String str = stack.pop();
				while (!Character.isDigit(str.charAt(0))){
					temp = new StringBuffer();
					temp.append(str).append(result).append(sb);
					str = stack.pop();
				}
				sb = new StringBuffer();
			}
		}
		if (sb.length() != 0){
			result.append(sb);
		}
		if (!stack.isEmpty()){
			StringBuffer temp = new StringBuffer();
			temp.append(stack.pop()).append(result);
			result = temp;
		}
		return result.toString();
	}
	private String repeatString(String str, int count){
		StringBuffer temp = new StringBuffer();
		while (count > 0){
			temp.append(str);
			count--;
		}
		return temp.toString();
	}
	
	public static void main(String [] args){
		DecodeString ds = new DecodeString();
		System.out.println(ds.decodeString("3[a]2[bc]"));
		System.out.println(ds.decodeString("3[a2[c]]"));
		System.out.println(ds.decodeString("2[abc]3[cd]ef"));
		System.out.println(ds.decodeString("2[2[b]]"));
		System.out.println(ds.decodeString("leetcode"));
		System.out.println(ds.decodeString("sd2[f2[e]g]i"));
	}

}

package string;

import java.util.Stack;

/*
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
 */
public class ValidParetheses {
    public boolean isValid(String s) {
    	Stack<Character> stack = new Stack<Character>();
    	char [] chars = s.toCharArray();
    	for (int i = 0;i<chars.length;i++){
    		if (chars[i] == '(' || chars[i] == '{' || chars[i] == '['){
    			stack.push(chars[i]);
    		}else if (chars[i] == ')' || chars[i] == '}' || chars[i] == ']'){
    			if (stack.isEmpty()) return false;
    			char c = stack.pop();
    			if ((chars[i] == ')' && c != '(') || (chars[i] == '}' && c != '{' ) ||
    				(chars[i] == ']' && c != '[' )){
    				return false;
    			}
    		}else{
    			continue;
    		}
    	}
    	return  (stack.isEmpty()? true:false);
    }
    public static void main (String[] args){
    	ValidParetheses v = new ValidParetheses();
    	System.out.println(v.isValid("()"));
    }
}

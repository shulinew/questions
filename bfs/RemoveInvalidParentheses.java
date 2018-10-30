package bfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/*
 *  Remove the minimum number of invalid parentheses in order to make the input string valid. Return 
 *  all possible results.
    Note: The input string may contain letters other than the parentheses ( and )
    "()())()" -> ["()()()", "(())()"]
	"(a)())()" -> ["(a)()()", "(a())()"]
	")(" -> [""]
 */
public class RemoveInvalidParentheses {
	public List<String> removeInvalidParentheses(String s) {
		List<String> result = new ArrayList<String>();
		remove (s, result, 0, 0, new char[]{'(', ')'});
//		String reversed = new StringBuilder(s).reverse().toString();
//		remove(reversed, result, 0 ,0, new char[]{')','('});
		int lastIndex = result.size();
		return result.subList(0, lastIndex);
	}
	public void remove(String s, List<String> result, int last_i, int last_j, char[] par) {
		for (int count = 0,  i = last_i; i < s.length(); ++i){
			if (s.charAt(i) == par[0]) {
				count++;
			}
			if (s.charAt(i) == par[1]) {
				count--;
			}
			if (count >= 0) continue;
			for (int j = last_j; j<=i; ++j) {
				if (s.charAt(j) == par[1] && (j == last_j || s.charAt(j-1) != par[1])) {
					remove(s.substring(0, j) + s.substring(j+1, s.length()), result, i, j, par);
				}
			}
			return;
		}
		//result.add(s);
		String reversed = new StringBuilder(s).reverse().toString();
		if (par[0] == '(') {
			remove(reversed, result, 0 ,0, new char[]{')','('});
		} else{
			result.add(reversed);
		}
	}
	
	/*
	 * The idea is straightforward, with the input string s, we generate all possible states by removing one ( or ), check if they are valid, if found valid ones 
	 * on the current level, put them to the final result list and we are done, otherwise, add them to a queue and carry on to the next level.
	The good thing of using BFS is that we can guarantee the number of parentheses that need to be removed is minimal, also no recursion call is needed in BFS.	
	Time complexity:
	
	In BFS we handle the states level by level, in the worst case, we need to handle all the levels, we can analyze the time complexity level by level and add them
	 up to get the final complexity.
	
	On the first level, there's only one string which is the input string s, let's say the length of it is n, to check whether it's valid, we need O(n) time. On 
	the second level, we remove one ( or ) from the first level, so there are C(n, n-1) new strings, each of them has n-1 characters, and for each string, we need 
	to check whether it's valid or not, thus the total time complexity on this level is (n-1) x C(n, n-1). Come to the third level, total time complexity is 
	(n-2) x C(n, n-2), so on and so forth...
	
	Finally we have this formula:
	T(n) = n x C(n, n) + (n-1) x C(n, n-1) + ... + 1 x C(n, 1) = n x 2^(n-1).

	 */
    public List<String> removeInvalidParentheses1(String s) {
        List<String> res = new ArrayList<String>();
        
        // sanity check
        if (s == null) return res;
        
        Set<String> visited = new HashSet<String>();
        Queue<String> queue = new LinkedList<String>();
        
        // initialize
        queue.add(s);
        visited.add(s);
        
        boolean found = false;
        
        while (!queue.isEmpty()) {
          s = queue.poll();
          
          if (isValid(s)) {
            // found an answer, add to the result
            res.add(s);
            found = true;
          }
          if (found) continue;
          // generate all possible states
          for (int i = 0; i < s.length(); i++) {
            // we only try to remove left or right paren
            if (s.charAt(i) != '(' && s.charAt(i) != ')') continue;
            String t = s.substring(0, i) + s.substring(i + 1);
            if (!visited.contains(t)) {
              // for each state, if it's not visited, add it to the queue
              queue.add(t);
              visited.add(t);
            }
          }
        }
        
        return res;
      }
      
      // helper function checks if string s contains valid parentheses
      boolean isValid(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
          char c = s.charAt(i);
          if (c == '(') count++;
          if (c == ')' && count-- == 0) return false;
        }
        return count == 0;
      }
	public static void main(String[] args) {
		RemoveInvalidParentheses rp = new RemoveInvalidParentheses();
		List<String> result = rp.removeInvalidParentheses("()())()");
	}
}

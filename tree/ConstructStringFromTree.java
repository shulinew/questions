package tree;

/*
 * You need to construct a string consists of parenthesis and integers from a binary tree with the preorder traversing way.

The null node needs to be represented by empty parenthesis pair "()". And you need to omit all the empty parenthesis pairs that don't affect the one-to-one mapping relationship between the string and the original binary tree.
 Input: Binary tree: [1,2,3,4]
       1
     /   \
    2     3
   /    
  4     

Output: "1(2(4))(3)"

Explanation: Originallay it needs to be "1(2(4)())(3()())", 
but you need to omit all the unnecessary empty parenthesis pairs. 
And it will be "1(2(4))(3)".
Input: Binary tree: [1,2,3,null,4]
       1
     /   \
    2     3
     \  
      4 

Output: "1(2()(4))(3)"

Explanation: Almost the same as the first example, 
except we can't omit the first parenthesis pair to break the one-to-one mapping relationship between the input and the output.

 */
public class ConstructStringFromTree {
	public String tree2str(TreeNode t) {
		StringBuilder sb = new StringBuilder();
		traverseTree(t, sb);
		return sb.toString();
    }
	private void traverseTree(TreeNode node, StringBuilder sb) {
		if (node == null) {
			return;
		}
		sb.append(node.val);
		sb.append("(");
		traverseTree(node.left, sb);
		sb.append(")");
		sb.append("(");
		traverseTree(node.right, sb);
		sb.append(")");
	}
	
	   public String tree2str1(TreeNode t) {
	        if (t == null) return "";
	        
	        String result = t.val + "";
	        
	        String left = tree2str(t.left);
	        String right = tree2str(t.right);
	        
	        if (left == "" && right == "") return result;
	        if (left == "") return result + "()" + "(" + right + ")";
	        if (right == "") return result + "(" + left + ")";
	        return result + "(" + left + ")" + "(" + right + ")";
	    }
	
	    public String tree2str2(TreeNode t) {
	        if(t==null)
	            return "";
	        if(t.left==null && t.right==null)
	            return t.val+"";
	        if(t.right==null)
	            return t.val+"("+tree2str(t.left)+")";
	        return t.val+"("+tree2str(t.left)+")("+tree2str(t.right)+")";   
	    }
	    
    public String tree2strIterate(TreeNode t) {
        if (t == null)
            return "";
        Stack < TreeNode > stack = new Stack < > ();
        stack.push(t);
        Set < TreeNode > visited = new HashSet < > ();
        StringBuilder s = new StringBuilder();
        while (!stack.isEmpty()) {
            t = stack.peek();
            if (visited.contains(t)) {
                stack.pop();
                s.append(")");
            } else {
                visited.add(t);
                s.append("(" + t.val);
                if (t.left == null && t.right != null)
                    s.append("()");
                if (t.right != null)
                    stack.push(t.right);
                if (t.left != null)
                    stack.push(t.left);
            }
        }
        return s.substring(1, s.length() - 1);
    }

}

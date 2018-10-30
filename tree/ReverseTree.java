package tree;

import java.util.LinkedList;
import java.util.Queue;

/*
 * Invert a binary tree
 *  based on the above Java code, it requires to understand it's memory model.
 *   Let me give you an example: by default each thread in Java can alocate up to 1MB for it's stack
 *    (this is various depending on the JVM version and operating system that is being run). 
 *    The LinkedList/Stack/Queue in contrary will be allocated on managed heap, 
 *    which by default can grow up to 1/4 of your phisycal memory.

	So if you distribute your application to the clients and one will run it on 2GB machine and another
	on 64GB both of them will be able to solve the same problem size - your implementation does not scale here. 
	I do not expect people to stop their system and increase the JVM stack size each time their memory 
	requirements increases, or worse recompile your c/c++ application in order to address this issue.

 */
public class ReverseTree {
    public TreeNode invertTree(TreeNode root) {

        if (root == null)
        	return null;
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = invertTree(right);
        root.right = invertTree(left);
        return root;
    }
    
    
    public TreeNode invertTree1(TreeNode root) {
    	
    	Queue<TreeNode> queue = new LinkedList<TreeNode>();
    	queue.add(root);
    	
    	while(!queue.isEmpty()){
    		TreeNode peek = queue.poll();
    		TreeNode left = peek.left;
    		peek.left = peek.right;
    		peek.right = left;  		
    		if (peek.left != null){
    			queue.add(peek.left);
    		}
    		if (peek.right != null){
    			queue.add(peek.right);
    		}
    	}
    	return root;
    }

}

package twodarray;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/*
 * Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest
 *  element in the matrix. Note that it is the kth smallest element in the sorted order, not the kth distinct 
 *  element. 
 */
public class KthSmallestElement {
    public int kthSmallest(int[][] matrix, int k) {

    	PriorityQueue<Triple> queue = new PriorityQueue<Triple>();
    	for (int i = 0; i< matrix.length;i++){
    		queue.offer(new Triple(0, i, matrix[0][i]));
    	}
    	for (int i = 0;i < k-1; i++){
    		Triple t = queue.poll();
    		if (t.row == matrix.length - 1){
    			continue;
    		}
    		queue.offer(new Triple(t.row+1, t.col, matrix[t.row+1][t.col]));
    	}
    	return queue.peek().val;
    	
    }
    public class Triple implements Comparable<Triple>{
    	int row;
    	int col;
    	int val;
    	public Triple(int i, int j, int val){
    		this.row = i;
    		this.col = j;
    		this.val = val;
    	}
    	public int compareTo(Triple that){
    		return this.val - that.val;
    	}
    }

}

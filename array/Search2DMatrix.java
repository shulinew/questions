package array;
/*
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
    Integers in each row are sorted from left to right.
    The first integer of each row is greater than the last integer of the previous row.
	For example,
	Consider the following matrix: 
	  [1,   3,  5,  7],
	  [10, 11, 16, 20],
	  [23, 30, 34, 50]
	Given target 3, return true
 */

public class Search2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
    	int start = 0, end;
    	int n = matrix.length;
    	int m = matrix[0].length;
    	end = m * n -1;
    	int mid;
    	while (start <= end){
    		mid = (start + end) / 2;
    		int cur = matrix[mid/m][mid%m];
    		if (cur == target) {
    			return true;
    		}else if (cur < target){
    			start = mid + 1;
    		}else{
    			end = mid -1;
    		}
    	}
    	return false;
        
    }

}

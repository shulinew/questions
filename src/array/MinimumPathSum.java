package array;

/*
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom 
 * right which minimizes the sum of all numbers along its path.
 */

public class MinimumPathSum {
    public int minPathSum(int[][] grid) {
    	int m = grid[0].length;
    	int n = grid.length;
    	for (int i = 1; i< n;i++){
    		grid[i][0] = grid[i-1][0] + grid[i][0];
    	}
    	for (int j = 1; j< m;j++){
    		grid[0][j] = grid[0][j-1] + grid[0][j];
    	}
    	for (int i = 1;i< n;i++){
    		for (int j =1; j< m;j++){
    			grid[i][j] = grid[i][j] + Math.min(grid[i][j-1], grid[i-1][j]);
    		}
    	}
    	return grid[n-1][m-1];
        
    }
    
    //Only use linear space
    public int minPathSum1(int[][] grid) {
    	int m = grid[0].length;
    	int [] cache = new int[m];
    	cache[0] = grid[0][0];
    	for (int i = 1; i< grid.length;i++){
    		cache[i] = cache[i-1] + grid[i][0];
    	}
    	for (int j = 1; j< m;j++){
    		grid[0][j] = grid[0][j-1] + grid[0][j];
    	}
    	for (int i = 1;i< n;i++){
    		for (int j =1; j< m;j++){
    			grid[i][j] = grid[i][j] + Math.min(grid[i][j-1], grid[i-1][j]);
    		}
    	}
    	return grid[n-1][m-1];
        
    }

}

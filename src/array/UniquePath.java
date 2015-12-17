package array;
/*
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
	The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
	How many possible unique paths are there?
 */
public class UniquePath {
    public int uniquePathsBasic(int m, int n) {
    	int [][] map = new int[m][n];
    	for (int i = 0;i<m;i++){
    		map[i][0] = 1;
    	}
    	for (int j = 0;j<n;j++){
    		map[0][j] = 1;
    	}
    	for (int i = 1;i<m;i++){
    		for (int j =1;j<n;j++){
    			map[i][j] = map[i-1][j] + map[i][j-1];
    		}
    	}
    	return map[m-1][n-1];
    }
    public int uniquePaths1DSpace(int m, int n) {
    	int [] dp = new int[n];
    	for (int i = 1;i<n;i++){
    		dp[i] = 1;
    	}
    	for (int i = 1;i<m;i++){
    		for (int j =1;j<n;j++){
    			dp[j] += dp[j-1];
    		}
    	}
    	return dp[n-1];
    	
    }
    public int uniquePaths(int m, int n) {
    	if (m > n){
    		m = m+n;
    		n = m-n;
    		m = m-n;
    	}
    	m--;
    	n--;
    	long result = 1;
    	int j = n+1;
    	for (int i = 1; i <= m;j++,i++){
    		result *= j;
    		result /=i;
    	}
    	return (int)result;
    }
    
    /*
     * Now consider if some obstacles are added to the grids. How many unique paths would there be?
		An obstacle and empty space is marked as 1 and 0 respectively in the grid.
		For example,
		There is one obstacle in the middle of a 3x3 grid as illustrated below.
     */
    
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
    	int n = obstacleGrid[0].length;
    	int [] currentRow = new int[n];
    	for (int i = 0; i< n;i++){
    		if (obstacleGrid[0][i] == 0){
    			currentRow[i] = 1;
    		}
    	}
    	for (int i = 1;i<obstacleGrid.length;i++){
    		for (int j =1;j<n;j++){
    			if (obstacleGrid[i][j] == 0){
    				currentRow[j] += currentRow[j-1];
    			}else{
    				currentRow[j] = 0;
    			}
    		}
    	}
    	return currentRow[n-1];
        
    }
    public static void main(String[] args){
    	UniquePath path = new UniquePath();
    	int[][] obstacleGrid = {{1,0}};
    	path.uniquePathsWithObstacles(obstacleGrid);
    }
    

}

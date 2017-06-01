package bfs;

/*
 * Given an integer matrix, find the length of the longest increasing path.
   From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally 
   or move outside of the boundary (i.e. wrap-around is not allowed).
	nums = [
	  [9,9,4],
	  [6,6,8],
	  [2,1,1]
	] 
	return 4 The longest increasing path is [1, 2, 6, 9].
	nums = [
	  [3,4,5],
	  [3,2,6],
	  [2,2,1]
	]
	return 4 [3, 4, 5, 6]
	
 */
public class LongestIncreaseMatrix {
	/*
	 * 
    Do DFS from every cell
    Compare every 4 direction and skip cells that are out of boundary or smaller
    Get matrix max from every cell's max
    Use matrix[x][y] <= matrix[i][j] so we don't need a visited[m][n] array
    The key is to cache the distance because it's highly possible to revisit a cell
	 */

	public int longestIncreasingPathDFS(int[][] matrix) {
		if (matrix.length == 0) return 0;
		int m = matrix.length;
		int n = matrix[0].length;
		int [][] cache = new int[m][n];
		int max = 1;
		for (int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				int len = dfs(matrix, i, j, m, n, cache);
				max = Math.max(max, len);
			}
		}
		return max;
	}
	private int dfs(int[][] matrix, int i, int j, int m, int n, int[][] cache) {
		if (cache[i][j] != 0) {
			return cache[i][j];
		}
		int max = 1;
		int[][] directions = {{0,-1},{0,1},{-1,0},{1,0}};
		for (int[]  direction: directions) {
			int row = i + direction[0];
			int col = j + direction[1];
			if (row < 0 || row >= m || col < 0 || col >= n || matrix[row][col] <= matrix[i][j]) {
				continue;
			}
			int len = 1 + dfs(matrix, row, col, m, n, cache);
			max = Math.max(max, len);
		}
		cache[i][j] = max;
		return max;
	}
	/*
	 * The idea is simple and intuitive:
		1. For each cell, try it's left, right, up and down for smaller number.
		2. If it's smaller, means we are on the right track and we should keep going. If larger, stop and return.
		3. Treat each cell as a start cell. Calculate and memorize the longest distance for this cell, so we don't
		 need to calculate it again in the future.
	 */
    public int longestIncreasingPath(int[][] matrix) {
    	if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
    		return 0;
    	}
    	int [][] cache = new int[matrix.length][matrix[0].length];
    	int max = 0;
    	for (int i = 0;i<matrix.length;i++){
    		for (int j = 0; j < matrix[0].length; j++) {
    			int length = findSmallAround(i, j, matrix, cache, Integer.MAX_VALUE);
    			max = Math.max(length, max);
    		}
    	}
    	return max;
    }
    private int findSmallAround(int i, int j, int[][] matrix, int[][] cache, int pre) {
    	if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length || matrix[i][j] >= pre) {
    		return 0;
    	}
    	if (cache[i][j] > 0) {
    		return cache[i][j];
    	} else {
    		int current = matrix[i][j];
    		int max = 0;
    		max = Math.max(findSmallAround(i-1, j, matrix, cache, current), max);
    		max = Math.max(findSmallAround(i, j-1, matrix, cache, current), max);
    		max = Math.max(findSmallAround(i+1, j, matrix, cache, current), max);
    		max = Math.max(findSmallAround(i, j+1, matrix, cache, current), max);
    		cache[i][j] = max++;
    		return max;
    	}
    }
    
    public static class Point{
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0)
            return 0;
        int n = matrix.length, m = matrix[0].length, count = m * n, ans = 0;
        while (count > 0) {
            HashSet<Point> remove = new HashSet<Point>();
            // each round, remove the peak number.
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (matrix[i][j] == Integer.MIN_VALUE)
                        continue;
                    boolean up = (i == 0 || matrix[i][j] >= matrix[i - 1][j]);
                    boolean bottom = (i == n - 1 || matrix[i][j] >= matrix[i + 1][j]);
                    boolean left = (j == 0 || matrix[i][j] >= matrix[i][j - 1]);
                    boolean right = (j == m - 1 || matrix[i][j] >= matrix[i][j + 1]);
                    if (up && bottom && left && right)
                        remove.add(new Point(i, j));
                }
            }
            for (Point point : remove) {
                matrix[point.x][point.y] = Integer.MIN_VALUE;
                count--;
            }
            ans++;
        }
        return ans;
}
}

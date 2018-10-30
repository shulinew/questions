/*
Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.) 

*/

public class MaxAreaIsland {
    public int maxAreaOfIsland(int[][] grid) {
        int [][] directions = {{0,1}, {1,0},{-1,0},{0,-1}};
        int maxArea = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length. j++) {
                if (grid[i][j] == 1) {
                    maxArea = Math.max(maxArea, areaDfs(grid, i, j));
                }
            }
        }
        return maxArea;
    }
    private int areaDfs(int [][] grid, int i, int j) {
        int n = grid.length;
        int m = grid[0].length;
        if (i >=0 && i < n && j >=0 && j < m && grid[i][j] == 1) {
            grid[i][j] = 0;
            return 1 + areaDfs(grid, i-1, j) + areaDfs(grid, i, j-1) + areaDfs(i, j-1) + areaDfs(i, j+1);
        }
        return 0;
    }
}
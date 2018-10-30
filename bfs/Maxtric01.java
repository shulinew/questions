package bfs;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 *  Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.
The distance between two adjacent cells is 1. 
input:
0 0 0
0 1 0
1 1 1
output:
0 0 0
0 1 0
1 2 1
 */
public class Maxtric01 {
    
    public List<List<Integer>> updateMatrix(List<List<Integer>> matrix) {
    	int m = matrix.size();
    	int n = matrix.get(0).size();
    	
    	Queue<int[]> queue = new LinkedList<int[]>();
    	
    	for (int i = 0;i < m; i++) {
    		for (int j = 0; j < n; j++) {
    			if (matrix.get(i).get(j) == 0) {
    				queue.offer(new int[] {i, j});
    			} else {
    				matrix.get(i).set(j, Integer.MAX_VALUE);
    			}
    		}
    	}
    	int[][] directions = {{-1,0},{1,0},{0, -1},{0, 1}};
    	while (!queue.isEmpty()) {
    		int[] cell = queue.poll();
    		for (int[] direction: directions) {
    			int row = cell[0] + direction[0];
    			int col = cell[1] + direction[1];
    			if (row < 0 || col <0 || row >= m || col >= n || matrix.get(row).get(col) <= matrix.get(cell[0]).get(cell[1]) + 1) {
    				continue;
    			}
    			queue.add(new int[]{row, col});
    			matrix.get(row).set(col, matrix.get(cell[0]).get(cell[1]) + 1);
    		}
    	}
    	return matrix;
    }
}

package bfs;

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Stack;

/*
 * Given an m x n matrix of positive integers representing the height of each unit cell in a 2D 
 * elevation map, compute the volume of water it is able to trap after raining. 
 * Both m and n are less than 110. The height of each unit cell is greater than 0 and is less than 20,000.
 * Given the following 3x6 height map:
	[
	  [1,4,3,1,3,2],
	  [3,2,1,3,2,4],
	  [2,3,3,2,3,1]
	]
	
	Return 4. 
 */
public class TrapRainWater {
	// define cell class
	public class Cell {
		int row;
		int col;
		int height;
		public Cell(int row, int col, int height) {
			this.row = row;
			this.col = col;
			this.height = height;
		}
	}

    public int trapRainWater(int[][] heightMap) {
        if (heightMap == null || heightMap.length == 0 || heightMap[0].length == 0) 
        	return 0;
        PriorityQueue<Cell> cells = new PriorityQueue<Cell>(1, new Comparator<Cell>() {
        	public int compare(Cell a, Cell b) {
        		return a.height - b.height;
        	}
        });
        
        int m = heightMap.length;
        int n = heightMap[0].length;
        boolean [][] visited = new boolean[m][n];
        System.out.println("Another loop from m");
        for (int i = 0;i<m;i++) {
        	visited[i][0] = true;
        	visited[i][n-1] = true;
        	cells.offer(new Cell(i, 0, heightMap[i][0]));
        	cells.offer(new Cell(i, n-1, heightMap[i][n-1]));
        	printCells(cells);
        }
        System.out.println("Another loop from n");
        for (int i = 0; i< n; i++) {
        	visited[0][i] = true;
        	visited[m-1][i] = true;
        	cells.offer(new Cell(0, i, heightMap[0][i]));
        	cells.offer(new Cell(m-1, i, heightMap[m-1][i]));
        	printCells(cells);
        }
        int [][] directions = new int[][]{{-1, 0}, {1, 0}, {0, -1},{0,1}};
        int volume = 0;
        System.out.println("Start iterate Queue");
        while(!cells.isEmpty()) {
        	Cell cell = cells.poll();
        	System.out.println("cells.poll() = {" + cell.row + "," + cell.col+","+cell.height+"}");
        	for (int[] direction: directions) {
        		int row = cell.row + direction[0];
        		int col = cell.col + direction[1];
        		if (row >= 0 && row < m && col >= 0 && col < n && !visited[row][col]) {
        			visited[row][col] = true;
        			System.out.println("volume = " + volume);
        			volume += Math.max(0, cell.height - heightMap[row][col]);
        			System.out.println("volume = " + volume);
        			System.out.println("new cell = {" + row + "," + col+","+Math.max(heightMap[row][col], cell.height)+"}");
        			cells.offer(new Cell(row, col, Math.max(heightMap[row][col], cell.height)));
        			printCells(cells);
        		}
        	}
        }
        return volume;
    }
    private void printCells(PriorityQueue<Cell> matrix) {
    	Iterator<Cell> cells = matrix.iterator();
    	while(cells.hasNext()) {
    		Cell cell = cells.next();
    		System.out.print("{" + cell.row + "," + cell.col + ","+cell.height +"}");
    		
    	}
    	System.out.print("\n");
    }
    /*
     * Another loop from m
		{0,0,1}{0,5,2}
		{0,0,1}{0,5,2}{1,0,3}{1,5,4}
		{0,0,1}{0,5,2}{2,5,1}{1,5,4}{2,0,2}{1,0,3}
		Another loop from n
		{0,0,1}{0,5,2}{2,5,1}{2,0,2}{2,0,2}{1,0,3}{0,0,1}{1,5,4}
		{0,0,1}{0,5,2}{2,5,1}{2,0,2}{2,0,2}{1,0,3}{0,0,1}{1,5,4}{0,1,4}{2,1,3}
		{0,0,1}{0,5,2}{2,5,1}{2,0,2}{2,0,2}{1,0,3}{0,0,1}{1,5,4}{0,1,4}{2,1,3}{0,2,3}{2,2,3}
		{0,0,1}{0,5,2}{2,5,1}{2,0,2}{2,0,2}{0,3,1}{0,0,1}{1,5,4}{0,1,4}{2,1,3}{0,2,3}{2,2,3}{1,0,3}{2,3,2}
		{0,0,1}{0,5,2}{2,5,1}{2,0,2}{2,0,2}{0,3,1}{0,0,1}{2,4,3}{0,1,4}{2,1,3}{0,2,3}{2,2,3}{1,0,3}{2,3,2}{0,4,3}{1,5,4}
		{0,0,1}{2,5,1}{2,5,1}{0,5,2}{2,0,2}{0,3,1}{0,0,1}{0,5,2}{2,0,2}{2,1,3}{0,2,3}{2,2,3}{1,0,3}{2,3,2}{0,4,3}{1,5,4}{2,4,3}{0,1,4}
		Start iterate Queue
		cells.poll() = {0,0,1}
		cells.poll() = {2,5,1}
		cells.poll() = {2,5,1}
		cells.poll() = {0,3,1}
		new cell = {1,3,3}
		{0,0,1}{0,5,2}{2,3,2}{0,5,2}{2,0,2}{2,2,3}{0,4,3}{2,4,3}{2,0,2}{2,1,3}{0,2,3}{0,1,4}{1,0,3}{1,5,4}{1,3,3}
		cells.poll() = {0,0,1}
		cells.poll() = {0,5,2}
		cells.poll() = {0,5,2}
		cells.poll() = {2,0,2}
		cells.poll() = {2,0,2}
		cells.poll() = {2,3,2}
		cells.poll() = {2,1,3}
		new cell = {1,1,3}
		{2,4,3}{1,3,3}{0,2,3}{1,1,3}{1,0,3}{2,2,3}{0,4,3}{1,5,4}{0,1,4}
		cells.poll() = {2,4,3}
		new cell = {1,4,3}
		{1,3,3}{1,1,3}{0,2,3}{1,4,3}{1,0,3}{2,2,3}{0,4,3}{1,5,4}{0,1,4}
		cells.poll() = {1,3,3}
		new cell = {1,2,3}
		{1,1,3}{1,4,3}{0,2,3}{1,2,3}{1,0,3}{2,2,3}{0,4,3}{1,5,4}{0,1,4}
		cells.poll() = {1,1,3}
		cells.poll() = {1,4,3}
		cells.poll() = {1,2,3}
		cells.poll() = {0,4,3}
		cells.poll() = {2,2,3}
		cells.poll() = {1,0,3}
		cells.poll() = {0,2,3}
		cells.poll() = {0,1,4}
		cells.poll() = {1,5,4}

     */
    /*
     *  Given n non-negative integers representing an elevation map where the width of each bar is 1, 
     *  compute how much water it is able to trap after raining.
		For example,
		Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6
     */
    public int trapWater(int[] heights) {
    	int i = 0;
    	int length = heights.length -1;
    	int max = 0;
    	int leftMax = 0;
    	int rightMax = 0;
    	while (i <= length) {
    		leftMax = Math.max(leftMax, heights[i]);
    		rightMax = Math.max(rightMax, heights[length]);
    		if (leftMax < rightMax) {
    			max += (leftMax -heights[i]);
    			i++;
    		} else {
    			max += (rightMax - heights[length]);
    			length--;
    		}
    	}
    	return max;
    }
    /* https://github.com/shawnfan/LintCode
     * The main idea is : if we want to find out how much water on a bar(bot), we need to find out the left larger bar's index (il), and right larger bar's 
     * index(ir), so that the water is (min(A[il],A[ir])-A[bot])*(ir-il-1), use min since only the lower boundary can hold water, and we also need to handle the 
     * edge case that there is no il.
		To implement this we use a stack that store the indices with decreasing bar height, once we find a bar who's height is larger, then let the top of the 
		stack be bot, the cur bar is ir, and the previous bar is il.
     */
    public int trap1(int[] A) {
        if (A==null) return 0;
        Stack<Integer> s = new Stack<Integer>();
        int i = 0, maxWater = 0, maxBotWater = 0;
        while (i < A.length){
            if (s.isEmpty() || A[i]<=A[s.peek()]){
                s.push(i++);
            }
            else {
                int bot = s.pop();
                maxBotWater = s.isEmpty()? // empty means no il
                0:(Math.min(A[s.peek()],A[i])-A[bot])*(i-s.peek()-1);
                maxWater += maxBotWater;
            }
        }
        return maxWater;
    }
    public int trapWithoutStack(int[] heights) {
    	if (heights == null) return 0;
    	int i = 0, length = heights.length, maxWater = 0, maxBotWater = 0;
    	int leftMax = 0, previous = 0;
    	while ( i < length) {
    		if (leftMax == 0 || heights[i] <= heights[leftMax]) {
    			previous = leftMax;
    			leftMax = i++;
    		} else {
    			int bot = leftMax;
    			maxBotWater = previous == 0? 0:(Math.min(heights[previous], heights[i]) - heights[bot]) * (i-previous -1);
    			maxWater += maxBotWater;
    		}
    	}
    	return maxWater;
    }
    public static void main(String[] args) {
    	TrapRainWater trw = new TrapRainWater();
    	int[][] matrix = new int[][]{{1,4,3,1,3,2}, {3,2,1,3,2,4}, {2,3,3,2,3,1}};
    	int res = trw.trapRainWater(matrix);
//    	int[] heights = new int[] {0,1,0,2,1,0,1,3,2,1,2,1};
    	int[] heights = new int[] {2,1,1,3};
    	trw.trap1(heights);
    }
}

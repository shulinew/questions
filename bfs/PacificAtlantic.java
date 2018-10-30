package bfs;

/*
 * Given an m x n matrix of non-negative integers representing the height of each unit cell in a 
 * continent, the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean"
 *  touches the right and bottom edges. Water can only flow in four directions (up, down, left, or 
 *  right) from a cell to another one with height equal or lower.
	Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.
	Given the following 5x5 matrix:
	
	  Pacific ~   ~   ~   ~   ~ 
	       ~  1   2   2   3  (5) *
	       ~  3   2   3  (4) (4) *
	       ~  2   4  (5)  3   1  *
	       ~ (6) (7)  1   4   5  *
	       ~ (5)  1   1   2   4  *
	          *   *   *   *   * Atlantic
	
	Return:
	
	[[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above 
	matrix).
 */
public class PacificAtlantic {
	/*
	 * 
    Two Queue and add all the Pacific border to one queue; Atlantic border to another queue.
    Keep a visited matrix for each queue. In the end, add the cell visited by two queue to the result.
    BFS: Water flood from ocean to the cell. Since water can only flow from high/equal cell to low cell, add the neighboor cell with height larger or equal to current cell to the queue and mark as visited.

	 */
    int[][]dir = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
    public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> res = new LinkedList<>();
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return res;
        }
        int n = matrix.length, m = matrix[0].length;
        //One visited map for each ocean
        boolean[][] pacific = new boolean[n][m];
        boolean[][] atlantic = new boolean[n][m];
        Queue<int[]> pQueue = new LinkedList<>();
        Queue<int[]> aQueue = new LinkedList<>();
        for(int i=0; i<n; i++){ //Vertical border
            pQueue.offer(new int[]{i, 0});
            aQueue.offer(new int[]{i, m-1});
            pacific[i][0] = true;
            atlantic[i][m-1] = true;
        }
        for(int i=0; i<m; i++){ //Horizontal border
            pQueue.offer(new int[]{0, i});
            aQueue.offer(new int[]{n-1, i});
            pacific[0][i] = true;
            atlantic[n-1][i] = true;
        }
        bfs(matrix, pQueue, pacific);
        bfs(matrix, aQueue, atlantic);
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(pacific[i][j] && atlantic[i][j])
                    res.add(new int[]{i,j});
            }
        }
        return res;
    }
    public void bfs(int[][]matrix, Queue<int[]> queue, boolean[][]visited){
        int n = matrix.length, m = matrix[0].length;
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            for(int[] d:dir){
                int x = cur[0]+d[0];
                int y = cur[1]+d[1];
                if(x<0 || x>=n || y<0 || y>=m || visited[x][y] || matrix[x][y] < matrix[cur[0]][cur[1]]){
                    continue;
                }
                visited[x][y] = true;
                queue.offer(new int[]{x, y});
            } 
        }
    }
    
    public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> res = new LinkedList<>();
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return res;
        }
        int n = matrix.length, m = matrix[0].length;
        boolean[][]pacific = new boolean[n][m];
        boolean[][]atlantic = new boolean[n][m];
        for(int i=0; i<n; i++){
            dfs(matrix, pacific, Integer.MIN_VALUE, i, 0);
            dfs(matrix, atlantic, Integer.MIN_VALUE, i, m-1);
        }
        for(int i=0; i<m; i++){
            dfs(matrix, pacific, Integer.MIN_VALUE, 0, i);
            dfs(matrix, atlantic, Integer.MIN_VALUE, n-1, i);
        }
        for (int i = 0; i < n; i++) 
            for (int j = 0; j < m; j++) 
                if (pacific[i][j] && atlantic[i][j]) 
                    res.add(new int[] {i, j});
        return res;
    }
    
    int[][]dir = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
    
    public void dfs(int[][]matrix, boolean[][]visited, int height, int x, int y){
        int n = matrix.length, m = matrix[0].length;
        if(x<0 || x>=n || y<0 || y>=m || visited[x][y] || matrix[x][y] < height)
            return;
        visited[x][y] = true;
        for(int[]d:dir){
            dfs(matrix, visited, matrix[x][y], x+d[0], y+d[1]);
        }
    }
    /*
     * I use two bits to save the information of pacific ocean and atlantic ocean.
		00: cannot reach any ocean
		01: can reach pacific ocean
		10: can reach atlantic ocean
		11: can reach two oceans
		
		Step 1: Update the status of border cells and put them into the queue
		Step 2: Iterate the queue and explore the four directions. We only put a new cell into the queue if :
		
		    row and col index are valid
		    the height of the new cell is larger or equals to the height of the current cell
		    the new cell can benifit from the current cell (check status)
     */
    
    public List<int[]> pacificAtlantic1(int[][] matrix) {
        List<int[]> res = new ArrayList<>();
        int m = matrix.length;
        if (m == 0) return res;
        int n = matrix[0].length;
        int[][] state = new int[m][n];
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            state[i][0] |= 1;
            if (i == m - 1 || n == 1) state[i][0] |= 2;
            if (state[i][0] == 3) res.add(new int[]{i, 0});
            q.add(new int[]{i, 0});
            if (n > 1) {
                state[i][n - 1] |= 2;
                if (i == 0) state[i][n - 1] |= 1;
                if (state[i][n - 1] == 3) res.add(new int[]{i, n - 1});
                q.add(new int[]{i, n - 1});
            }
        }
        for (int j = 1; j < n - 1; j++) {
            state[0][j] |= 1;
            if (m == 1) state[0][j] |= 2;
            if (state[0][j] == 3) res.add(new int[]{0, j});
            q.add(new int[]{0, j});
            if (m > 1) {
                state[m - 1][j] |= 2;
                if (state[m - 1][j] == 3) res.add(new int[]{m - 1, j});
                q.add(new int[]{m - 1, j});
            }
        }
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while (!q.isEmpty()) {
            int[] cell = q.poll();
            for (int[] dir : dirs) {
                int row = cell[0] + dir[0];
                int col = cell[1] + dir[1];
                if (row < 0 || col < 0 || row == m || col == n || matrix[row][col] < matrix[cell[0]][cell[1]] || ((state[cell[0]][cell[1]] | state[row][col]) == state[row][col])) continue;
                state[row][col] |= state[cell[0]][cell[1]];
                if (state[row][col] == 3) res.add(new int[]{row, col});
                q.add(new int[]{row, col});
            }
        }
        return res;
    }

}

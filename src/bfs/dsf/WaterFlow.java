/*
Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.

Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.

Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.
Pacific ~   ~   ~   ~   ~ 
       ~  1   2   2   3  (5) *
       ~  3   2   3  (4) (4) *
       ~  2   4  (5)  3   1  *
       ~ (6) (7)  1   4   5  *
       ~ (5)  1   1   2   4  *
          *   *   *   *   * Atlantic

*/

public class WaterFlow {
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
        List<int[]> results = new ArrayList<int[]>();
        int[][] canPacific = new int[matrix.length][matrix[0].length];
        int[][] canAtlantic = new int[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (dfs(matrix, i, j, canPacific, canAtlantic)) {
                    results.add(new int{i, j});
                }
            }
        }
        return results;
    }
    private boolean dfs(int[][] matrix, int i, int j, int[][] canPacific, int[][] canAtlantic) {
        if (i >= 0 && i < matrix.length && j > = 0 && j < matrix[0].length) {
            if (i == 0 || j == 0) {
                canPacific[i][j] = true;
            }
            if (j == matrix[0].length-1 || i == matrix.length-1) {
                canAtlantic[i][j] = true;
            }
            if (!canFlowPacific) {
                canFlowPacific = (matrix[i-1][j] <= matrix[i][j] && dfs(matrix, i-1, j)) ||
                                 (matrix[i][j-1] <= matrix[i][j] && dfs(matrix, i, j-1));
            }
            if (!canFlowAtlantic) {
                canFlowAtlantic = (matrix[i+1][j] <= matrix[i][j] && dfs(matrix, i+1, j)) ||
                    (matrix[i][j+1] <= matrix[i][j] && dfs(matrix, i, j+1));
            }
            if (canFlowAtlantic && canFlowPacific) {
                return true;
            }
        }
        return false;
    }
}
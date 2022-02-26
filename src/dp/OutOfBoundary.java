/*
Input: m = 2, n = 2, N = 2, i = 0, j = 0
Output: 6
*/
public class OutOfBoundary {
    public int findPaths(int m, int n, int N, int i, int j) {
        if (i==m || j == n || i < 0 || j < 0){
            return 1;
        }
        if (N == 0){
            return 0;
        }
        return findPaths(m, n, N - 1, i -1, j) + findPath(m, n, N - 1, i, j-1) + findPath(m, n, N -1, i+1, j) + findPath(m, n, N-1,i, j+1);
    }
    int M = 1000000007;
    public int findPath1(int m, int n, int N, int i, int j) {
        int [][][] memo = new int [m][n][N+1];
        for (int [][] row: memo){
            for(int[] col: row){
                Arrays.fill(col, -1);
            }
        }
        return findPath(m, n, N, i, j, memo);
    }
    private int findPath(int m, int n, int N, int i, int j, int[][][] memo) {
       if(i == m || j == n || i < 0 || j <0){
           return 1;
       }
       if (N == 0){
           return 0;
       }
       if (memo[i][j][N] >= 0) {
           return memo[i][j][N];
       }
       memo[i][j][N] = ((findPath(m, n, N-1, i, j -1, memo) + findPath(m, n, N-1, i, j+1, memo))%M + (findPath(m, n, N-1, i-1, j, memo) +findPath(m, n, N-1, i+1, j, memo))%M)%M;
       return memo[i][j][N];
    }
    public int findPathDP(int m, int n, int N, int x, int y){
       int M = 1000000000 + 7;
       int dp[][] = new int [m][n];
       dp[x][y] = 1;
       int count = 0;
       for (int moves = 1; moves <= N; moves++){
           int[][] temp = new int[m][n];
           for (int i = 0; i < m; i++){
               for(int j = 0; j < n; j++){
                    if (i == m - 1)
                        count = (count + dp[i][j]) % M;
                    if (j == n - 1)
                        count = (count + dp[i][j]) % M;
                    if (i == 0)
                        count = (count + dp[i][j]) % M;
                    if (j == 0)
                        count = (count + dp[i][j]) % M;
                    temp[i][j] = (((i > 0 ? dp[i - 1][j] : 0) + (i < m - 1 ? dp[i + 1][j] : 0)) % M + ((j > 0 ? dp[i][j - 1] : 0) + (j < n - 1 ? dp[i][j + 1] : 0)) % M) % M;
               }
           }
           dp = temp;
       }
       return count;
    }

    public int findPathDP1(int m, int n, int N, int x, int y){
        int M = 1000000000 + 7;
        int dp[][][] = new int [m][n][N+1];
        dp[x][y][0] = 1;
        int count = 0;
        for (int moves = 1; moves <= N; moves++){
           for (int i = 0; i < m; i++){
               for(int j = 0; j < n; j++){
                    if (i == m - 1)
                        count = (count + dp[i][j][moves-1]) % M;
                    if (j == n - 1)
                        count = (count + dp[i][j][moves-1]) % M;
                    if (i == 0)
                        count = (count + dp[i][j][moves-1]) % M;
                    if (j == 0)
                        count = (count + dp[i][j][moves-1]) % M;
                    dp[i][j][moves] = (((i > 0 ? dp[i - 1][j][moves-1] : 0) + (i < m - 1 ? dp[i + 1][j][moves-1] : 0)) % M + ((j > 0 ? dp[i][j - 1][moves-1] : 0) + (j < n - 1 ? dp[i][j + 1][moves-1] : 0)) % M) % M;
               }
           }
       }
       return count;
    }
}
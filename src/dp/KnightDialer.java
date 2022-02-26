public class KnightDialer {
    private static final int mod = 1000000007;
    public int knightDialer(int n) {
        int max = (int)Math.pow(10, 9)+7;
        long sum = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                sum = (sum + path(i, j, n))%max;
            }
        }
        return sum;
    }
    private long path(int i , int j, int n, int max) {
        if (i < 0 || j < 0 || j >=3 || (i == 3 && j != 1)) {
            return 0;  
        }
        if (n == 1) return 1;
        long sum = path(i-1, j-2, n-1) % max +
                    path(i-1, j+2, n-1) % max +
                    path(i+1, j-2, n-1) % max +
                    path(i+1, j+2, n-1) % max +
                    path(i-2, j+1, n-1) % max +
                    path(i-2, j-1, n-1) % max +
                    path(i+2, j-1, n-1) % max +
                    path(i+2, j+1, n-1) % max;
        return sum;
    }

    public int knightDialerDP(int N) {
        int max = (int)Math.pow(10, 9)+7;
        long sum = 0;
        long[][][] memo = new long[4][3][N+1];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                sum = (sum + path(i, j, N, max, memo))%max;
            }
        }
        return (int)sum;
    }
    private long path(int i , int j, int n, int max, long[][][] memo) {
        if (i < 0 || j < 0 || j >=3 || (i == 3 && j != 1) || i >= 4) {
            return 0;
        }
        if (n == 1) {
            memo[i][j][n] = 1;
            return memo[i][j][n];
        }
        if (memo[i][j][n] > 0) {
            return memo[i][j][n];
        }
        memo[i][j][n] = path(i-1, j-2, n-1, max, memo) % max +
                    path(i-1, j+2, n-1, max, memo) % max +
                    path(i+1, j-2, n-1, max, memo) % max +
                    path(i+1, j+2, n-1, max, memo) % max +
                    path(i-2, j+1, n-1, max, memo) % max +
                    path(i-2, j-1, n-1, max, memo) % max +
                    path(i+2, j-1, n-1, max, memo) % max +
                    path(i+2, j+1, n-1, max, memo) % max;
        return memo[i][j][n];
    }
    public int knightDialerDPTop(int N) {
        int [][] moves = new int[][] {{4,6},{6,8},{7,9},{4,8},{3,9,0},{},{1,7,0},{2,6},{1,3},{2,4}};
        int count = 0;
        int [][] memo = new int[N][10];
        for (int i = 0; i <=9; i++) {
           count = (count + helper(N-1, i, moves, memo))%mod;
        }
        return count;
    }
    private int helper(int n, int current, int[][] moves, int[][] memo) {
       if (n == 0) return 1;
       if (memo[n][current] != 0) {
           return memo[n][current];
       }
       int count = 0;
       for (int move:moves[current]) {
          count = (count + helper(n-1,move, moves, memo)) % mod;
       }
       memo[n][current] = count;
       return memo[n][current];
    }

    public int knightDialer(int N) {
        int MOD = 1_000_000_007;
        int[][] moves = new int[][]{
            {4,6},{6,8},{7,9},{4,8},{3,9,0},
            {},{1,7,0},{2,6},{1,3},{2,4}};

        int[][] dp = new int[2][10];
        Arrays.fill(dp[0], 1);
        for (int hops = 0; hops < N-1; ++hops) {
            Arrays.fill(dp[~hops & 1], 0);
            for (int node = 0; node < 10; ++node)
                for (int nei: moves[node]) {
                    dp[~hops & 1][nei] += dp[hops & 1][node];
                    dp[~hops & 1][nei] %= MOD;
                }
        }

        long ans = 0;
        for (int x: dp[~N & 1])
            ans += x;
        return (int) (ans % MOD);
    }
}
package dp;
/*
The connected two points in polygon shares one common edge,
these two points must be one and only one triangles.


Explanation

dp[i][j] means the minimum score to triangulate A[i] ~ A[j],
while there is edge connect A[i] and A[j].

We enumerate all points A[k] with i < k < j to form a triangle.

The score of this triangulation is dp[i][j], dp[i][k] + dp[k][j] + A[i] * A[j] * A[k]
*/

public class MiniscoreTriangulation {
    /*
    dp[i][j] means the minimum score to triangulate A[i] ~ A[j],
while there is edge connect A[i] and A[j].

We enumerate all points A[k] with i < k < j to form a triangle.

The score of this triangulation is dp[i][j], dp[i][k] + dp[k][j] + A[i] * A[j] * A[k]
    */
    public int minScoreTriangulationUp(int[] A) {
        int n = A.length;
        int[][] dp = new int[n][n];
        for (int d = 2; d < n; ++d) {
            for (int i = 0; i + d < n; ++i) {
                int j = i + d;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i + 1; k < j; ++k)
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j] + A[i] * A[j] * A[k]);
            }
        }
        return dp[0][n - 1];
    }

    public int minScoreTriangulationDown(int[] A) {
        int n = A.length;
        int[][] dp = new int[n][n];
        for (int j = 2; j < n; ++j) {
            for (int i = j - 2; i >= 0; --i) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i + 1; k < j; ++k)
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j] + A[i] * A[j] * A[k]);
            }
        }
        return dp[0][n - 1];
    }
	

    public int minScoreTriangulation (int[] A) {
        int[][] memo = new int[51][51]; // why 51
        return dp(0, A.length-1, A, memo);
    }
    private int dp(int pos1, int pos2, int[] A, int[][] memo) {
        if (pos2 - pos1 <= 1) {
            return 0;
        }
        if (memo[pos1][pos2] != -1) {
            return memo[pos1][pos2];
        }
        int result = Integer.MAX_VALUE;
        for (int i = pos1+1; i < pos2; i++) {
            result = Math.min(result, dp(pos1, i, A, memo) + dp(i, pos2, A, memo) + A[pos1]*A[pos2]*A[i]);
        }
        memo[pos1][pos2] = result;
        return result;
    }
}
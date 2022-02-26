package dp;

/*
This problem has an optimal substructure, meaning that the solutions to subproblems can be used to solve larger instances of this problem. This makes dynamic programming an ideal candidate.

Let dp(r, c) be the minimum total weight of a falling path starting at (r, c) and reaching the bottom row.

Then, dp(r, c) = A[r][c] + min(dp(r+1, c-1), dp(r+1, c), dp(r+1, c+1)), and the answer is min⁡cdp(0,c)\min\limits_c \text{dp}(0, c)cmin​dp(0,c).

Algorithm

Usually, we would make an auxillary array dp to cache intermediate values dp(r, c). However, this time we will use A to cache these values. Our goal is to transform the values of A into the values of dp.

We process each row, starting with the second last. (The last row is already correct.) We set A[r][c] = min(A[r+1][c-1], A[r+1][c], A[r+1][c+1]), handling boundary conditions gracefully.

Let's look at the recursion a little more to get a handle on why it works. For an array like A = [[1,1,1],[5,3,1],[2,3,4]], imagine you are at (1, 0) (A[1][0] = 5). You can either go to (2, 0) and get a weight of 2, or (2, 1) and get a weight of 3. Since 2 is lower, we say that the minimum total weight at (1, 0) is dp(1, 0) = 5 + 2 (5 for the original A[r][c].)

After visiting (1, 0), (1, 1), and (1, 2), A [which is storing the values of our dp], looks like [[1,1,1],[7,5,4],[2,3,4]]. We do this procedure again by visiting (0, 0), (0, 1), (0, 2). We get A = [[6,5,5],[7,5,4],[2,3,4]], and the final answer is min(A[0]) = 5
 */

public class MiniFailingPath {
    public int miniFailingPathSum(int[][] A) {
        int row = A.length;
        int col = A[0].length;
        int[][] dp = new int[row][col];
        for (int i = 1; i < row; i++) {
            for (int j = 0; j < col; j++) {
                A[i][j] += Math.min(A[i-1][j], Math.min(A[i-1][Math.max(0, j-1)], A[i-1][Math.min(j+1, col-1)]));
            }
        }
        int minSum = Integer.MAX_VALUE;
        for (int j = 0; j < col; j++) {
            minSum = Math.min(minSum, A[row-1][j]);
        }
        return minSum;
    }
}

package dp;/*
On an NxN chessboard, a knight starts at the r-th row and c-th column and attempts to make exactly K moves. The rows and columns are 0 indexed, so the top-left square is (0, 0), and the bottom-right square is (N-1, N-1).

A chess knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction, then one square in an orthogonal direction.
Each time the knight is to move, it chooses one of eight possible moves uniformly at random (even if the piece would go off the chessboard) and moves there.

The knight continues moving until it has made exactly K moves or has moved off the chessboard. Return the probability that the knight remains on the board after it has stopped moving.
*/

public class KnightProbability {
    public double knightProbability(int N, int K, int r, int c) {
        int [][] directions = {{-2,-1},{-2, 1},{-1,-2}, {-1, 2}, {1, 2}, {1, -2}, {2, 1}, {2, -1}};
        // dp[i][j] is possiblity of being on i, j
        double[][] dp = new double[N][N];
        dp[r][c] = 1;
        for (; K > 0; K--) {
            // possibility of being on (i, j) on steps K
            double[][] dp2 = new double[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    for (int k = 0; k < 8; k++) {
                        int row = i + directions[k][0];
                        int col = j + directions[k][1];
                        if (row >= 0 && row < N && col >= 0 && col < N) {
                            dp2[row][col] += dp[i][j] / 8.0;
                        }
                    }
                }
            }
            dp = dp2;
        }
        double answer = 0.0;
        for (double[] row: dp) {
            for (double p: row) {
                answer += p;
            }
        }
        return answer;
    }

    //https://leetcode.com/problems/knight-probability-in-chessboard/solution/
}
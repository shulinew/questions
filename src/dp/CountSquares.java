package dp;

public class CountSquares {
    public int countSquares(int[][] matrix) {
        int total = 0;
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    continue;
                }
                if (i == 0 || j == 0) {
                    total += matrix[i][j];
                    continue;
                }
                int min = Math.min(matrix[i-1][j], Math.min(matrix[i][j-1], matrix[i-1][j-1]));
                matrix[i][j] += min;
                total += matrix[i][j];
            }
        }
        return total;
    }
    public int countSquaresMoreMemory(int[][] matrix) {
        int total = 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if (i == 0 || j == 0){
                    dp[i][j] = matrix[i][j];
                } else if (matrix[i][j] > 0) {
                    dp[i][j] = Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1])) + 1;
                }
                total += dp[i][j];
            }
        }
        return total;
    }

}

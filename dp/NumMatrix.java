/*
Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
*/

public class NumMatrix {
    private int[][] matrix;
    public NumMatrix(int[][] matrix){
        this.matrix = matrix;
    }
    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = 0;
        for (int i = row1; i <= row2; i++) {
            for (int j = col1; j <= col2; j++){
                sum += matrix[i][j];
            }
        } 
    }

    ///////
    private int[][] dp;
    public NumMatrix(int[][] matrix) {
        int n = matrix.length;
        if (n > 0) {
            int m = matrix[0].length;
            dp = new int[n][m];
            dp[0][0] = matrix[0][0];
            for (int i = 1; i < n; i++){
                dp[i][0] = dp[i-1][0] + matrix[i][0];
            }
            for (int j = 1; j < m; j++){
                dp[0][j] = dp[0][j-1] + matrix[0][j];
            }
            for (int i =1; i < n; i++) {
                for (int j = 1; j < m; j++) {
                    dp[i][j] = matrix[i][j] + dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1];
                }
            }
        }
    }
    public int sumRegion(int row1, int col1, int row2, int col2) {
        if (row1==0 && col1==0) {
            return dp[row2][col2];
        }
        if (row1==0) {
            return dp[row2][col2] - dp[row2][col1-1];
        }
        if (col1==0) {
            return dp[row2][col2] - dp[row1-1][col2];
        }
        return dp[row2][col2] - dp[row2][col1-1] - dp[row1-1][col2] + dp[row1-1][col1-1];
    }

    // public NumMatrix(int[][] matrix) {
    //     if (matrix.length == 0 || matrix[0].length == 0) return;
    //     dp = new int[matrix.length][matrix[0].length + 1];
    //     for (int r = 0; r < matrix.length; r++) {
    //         for (int c = 0; c < matrix[0].length; c++) {
    //             dp[r][c + 1] = dp[r][c] + matrix[r][c];
    //         }
    //     }
    // }

    // public int sumRegion(int row1, int col1, int row2, int col2) {
    //     int sum = 0;
    //     for (int row = row1; row <= row2; row++) {
    //         sum += dp[row][col2 + 1] - dp[row][col1];
    //     }
    //     return sum;
    // }

}
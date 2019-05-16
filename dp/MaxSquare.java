public class MaxSquare {
    public int maximalSquare(char[][] matrix) {
        int rows = matrix.length;
        int cols = rows > 0 ? matrix[0].length:0;
        int max = 0;
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                if (matrix[i][j] == '1') {
                    boolean continueTraevrse = true;
                    int len = 1;
                    while (i + len < rows && j + len < cols && continueTraevrse) {
                       for (int k = j; k <= len+j; k++){
                           if (matrix[i+len][k] == '0'){
                               continueTraevrse = false;
                               break;
                           }
                       }
                       for (int k = i; k <= len+i; k++){
                           if (matrix[k][j+len] == '0'){
                               continueTraevrse = false;
                               break;
                           }
                       }
                       if (continueTraevrse) {
                           len++;
                       }
                    }
                    max = Math.max(max, len);
                }
            }
        }
        return max*max;
    }
    //dp(i,j) represents the side length of the maximum square whose bottom right corner is the cell with index (i,j) in the original matrix.
    public int maximalSquareDP(char[][] matrix) {
        int rows = matrix.length;
        int cols = rows > 0? matrix[0].length:0;
        int[][] dp = new int[rows+1][cols+1];
        int maxLen = 0;
        for (int i = 1; i <=rows; i++){
            for(int j = 1; j <= cols; j++) {
                if (matrix[i-1][j-1] == '1') {
                    dp[i][j] = Math.min(Math.min(dp[i-1][j-1], dp[i-1][j]), dp[i][j-1]) +1;
                    maxLen = Math.max(dp[i][j], maxLen);
                }
            }
        }
        return maxLen * maxLen;
    }

/*

*/
    public int maximalSquareDPOptimize(char[][] matrix) {
        int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;
        int[] dp = new int[cols + 1];
        int maxsqlen = 0, prev = 0;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                int temp = dp[j];
                if (matrix[i - 1][j - 1] == '1') {
                    dp[j] = Math.min(Math.min(dp[j - 1], prev), dp[j]) + 1;
                    maxsqlen = Math.max(maxsqlen, dp[j]);
                } else {
                    dp[j] = 0;
                }
                prev = temp;
            }
        }
        return maxsqlen * maxsqlen;
    }
//   ["1","0","1","0","0"],
//   ["1","0","1","1","1"],
//   ["1","1","1","1","1"],
//   ["1","0","0","1","0"]


}
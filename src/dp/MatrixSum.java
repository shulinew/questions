package dp;

public class MatrixSum {

//    https://leetcode.com/problems/maximum-product-of-splitted-binary-tree/
    /*
    https://leetcode.com/problems/matrix-block-sum/
    https://leetcode.com/problems/matrix-block-sum/discuss/477036/JavaPython-3-PrefixRange-sum-w-analysis-similar-to-LC-30478
    https://computersciencesource.wordpress.com/2010/09/03/computer-vision-the-integral-image/
    https://leetcode.com/problems/dice-roll-simulation/
    https://leetcode.com/problems/maximum-product-of-splitted-binary-tree/discuss/?currentPage=1&orderBy=most_votes&query=
    https://leetcode.com/problems/maximum-product-of-splitted-binary-tree/
    https://computersciencesource.wordpress.com/2010/09/03/computer-vision-the-integral-image/
    Next:
    https://leetcode.com/problems/dice-roll-simulation/
    https://leetcode.com/problems/linked-list-in-binary-tree/
     */
    public int[][] matrixBlockSumTEL(int[][] mat, int K) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] answer = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int sum = 0;
                for (int r = i - K; r <= i+K; r++) {
                    for (int c = j - K; c <= j + K; j++) {
                        if (r >= 0 && r <= m && c >= 0 && c <= n) {
                            sum += mat[r][c];
                        }
                    }
                }
                answer[i][j] = sum;
            }
        }
        return answer;
    }
    public int[][] matrixBlockSum(int[][] mat, int K) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] rangeSum = new int[m+1][n+1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rangeSum[i+1][j+1] = rangeSum[i+1][j] + rangeSum[i][j+1] - rangeSum[i][j] + mat[i][j];
            }
        }
        int[][] ans = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int r1 = Math.max(0, i - K);
                int r2 = Math.min(m, i + K + 1);
                int c1 = Math.max(0, j - K);
                int c2 = Math.min(n, j + K + 1);
                ans[i][j] = rangeSum[r2][c2] - rangeSum[r2][c1] - rangeSum[r1][c2] + rangeSum[r1][c1];
            }
        }
        return ans;
    }
    public int maxSideLength(int[][] mat, int threshold) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] prefixSum = new int[m+1][n+1];
        for (int i = 1; i <= m; i++) {
            int sum = 0;
            for(int j = 1; j <= n; j++) {
                sum += mat[i-1][j-1];
                prefixSum[i][j] = prefixSum[i-1][j] + sum;
            }
        }
        for (int k = Math.min(m, n) - 1; k >= 0; k--) {
            for (int i = 1; i + k <= m; i ++) {
                for(int j = 1; j + k <= n; j++) {
                    if ((prefixSum[i+k][j+k] - prefixSum[i-1][j+k] - prefixSum[i+k][j-1] + prefixSum[i-1][j-1]) <= threshold) {
                        return k+1;
                    }
                }
            }
        }
        return 0;
    }
    public int maxSideLengthBinarySearch(int[][] mat, int thredshold) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] sum = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++){
            for (int j = 1; j <= n; j++) {
                sum[i][j] = sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1] + mat[i-1][j-1];
            }
        }
        int low = 1, high = Math.min(m, n);
        int answer = 0;
        while(low <= high) {
            int mid = low + (high - low) / 2;
            if (foundSquare(mat, sum, mid, thredshold)) {
               answer = mid;
               low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return answer;
    }
    private boolean foundSquare(int[][] mat, int[][] sum, int mid, int threadshold) {
        int m = mat.length;
        int n = mat[0].length;
        for (int i = mid; i <= m; i++) {
            for(int j = mid; j <= n; j++) {
                if (sum[i][j] - sum[i-mid][j] - sum[i][j-mid] + sum[i-mid][j-mid] <= threadshold) {
                    return true;
                }
            }
        }
        return false;
    }

}

package dp;

//https://leetcode.com/problems/largest-sum-of-averages/discuss/122739/C%2B%2BJavaPython-Easy-Understood-Solution-with-Explanation
//https://leetcode.com/problems/largest-sum-of-averages/discuss/126280/Naive-Detailed-Step-by-Step-Approach-from-Recursive-to-DP-O(N)-solution
//https://leetcode.com/problems/largest-sum-of-averages/discuss/122775/Java-bottom-up-DP-with-Explanation
public class LargestSumOfAverage {
    public double largestSumOfAverages(int[] A, int K) {
        int len = A.length;
        double[] sum = new double[len+1];
        for(int i = 0; i < len; i++) {
            sum[i+1] = sum[i] + A[i];
        }
        double[] dp = new double[len];
        for(int i = 0; i < len; i++) {
            dp[i] = (sum[len] - sum[i]) / (len - i);
        }
        for (int k = 0; k < K-1; k++) {
            for ( int i = 0; i < len; i++) {
                for (int j = i + 1; j < len; j++) {
                    dp[i] = Math.max(dp[i], (sum[j] - sum[i]) / (j - i) + dp[j]);
                }
            }
        }
        return dp[0];
    }
    public double largestSumOfAveragesRecursive(int[] A, int K) {
        int[] sum = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            sum[i] = A[i] + (i > 0 ? sum[i-1] : 0);
        }
        return helper(A, K, sum, 0);
    }
    private double helper(int[] A, int K, int[] sum, int start) {
        int len = A.length;
        if (K == 1) {
            return (double) (sum[len-1] - sum[start] + A[start]) / (len - start);
        }
        double num = 0;
        // why i + K <= len??? Maximum K is length, so want to partition to K part, the index can't be exceed length-K
        for (int i = start; i + K <= len; i++) {
            num = Math.max(num, ((double) (sum[i] - sum[start] + A[start]) / (i - start + 1)) + helper(A, K -1, sum, i + 1));
        }
        return num;
    }

    public double largestSumOfAveragesRecursiveWithMemo(int[] A, int K) {
        int[] sum = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            sum[i] = (i == 0 ? 0 : sum[i-1] + A[i]);
        }
        double[][] memo = new double[A.length][K+1];
        return helper(A, K, sum, memo, 0);
    }
    private double helper(int[] A, int K, int[] sum, double[][] memo, int start) {
        if (memo[start][K] != 0) return memo[start][K];
        int len = A.length;
        if (K == 1) {
            memo[start][K] = ((double)(sum[len-1] - sum[start] + A[start]) / (len - start));
            return memo[start][K];
        }
        double num = 0;
        for (int i = start; i + K <= len; i++) {
            num = Math.max(num, ((double)(sum[i] - sum[start] + A[start]) / (i - start + 1)) + helper(A, K-1, sum, memo, i + 1));
            memo[start][K] = num;
        }
        return memo[start][K];
    }
    public double largestSumOfAverageDP(int[] A, int K) {
        int[] sum = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            sum[i] = (i == 0 ? 0 : sum[i-1]) + A[i];
        }
        double[][] dp = new double[A.length][K+1];

        for (int k = 1; k <= K; k++) {
            for(int start = 0; start + k <= A.length; start++){
                if (k == 1) {
                    dp[start][k] = ((double)(sum[A.length-1] - sum[start] + A[start]) / (A.length - start));
                    continue;
                }
                for (int j = start; j + k <= A.length; j++) {
                    dp[start][k] = Math.max(dp[start][k], dp[j+1][k-1] + (double)(sum[j] - sum[start] + A[start]) / (j - start + 1));
                }
            }
        }
        return dp[0][K];
    }

    public double largestSumOfAveragesDP1D(int[] A, int K) {
        int[] sum = new int[A.length];
        for(int i = 0; i < A.length; i++) {
            sum[i] = (i == 0 ? 0: sum[i-1]) + A[i];
        }
        double[] dp = new double[A.length];
        for (int k = 1; k <= K; k++) {
            for (int start = 0; start + k <= A.length; start++) {
                if (k == 1) {
                    dp[start] = ((double)(sum[A.length-1] - sum[start] + A[start]) / (A.length - start));
                    continue;
                }
                for (int j = start; j + k <= A.length; j++) {
                    dp[start] = Math.max(dp[start], dp[j+1] + (double)(sum[j] - sum[start] + A[start]) / (j - start + 1));
                }
            }
        }
        return dp[0];
    }

    /*
    Let f[i][j]be the largest sum of averages for first i + 1 numbers(A[0], A[1], ... , A[i]) tojgroups. f[i][j] consists of two parts: first j-1 groups' averages and the last group' s average. Considering the last group, its last number must be A[i] and its first number can be from A[0] to A[i]. Suppose the last group starts from A[p+1], we can easily get the average form A[p+1] to A[i]. The sum of first j-1 groups' average is f[p][j-1] which we have got before. So now we can write the DP equation:
f[i][j] = max {f[p][j-1] + (A[p+1] + A[p+2] + ... + A[i]) / (i - p)}, p = 0,1,...,i-1
     */
}

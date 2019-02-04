/*
n the computer world, use restricted resource you have to generate maximum benefit is what we always want to pursue.

For now, suppose you are a dominator of m 0s and n 1s respectively. On the other hand, there is an array with strings consisting of only 0s and 1s.

Now your task is to find the maximum number of strings that you can form with given m 0s and n 1s. Each 0 and 1 can be used at most once. 

Input: Array = {"10", "0001", "111001", "1", "0"}, m = 5, n = 3
Output: 4

Explanation: This are totally 4 strings can be formed by the using of 5 0s and 3 1s, which are “10,”0001”,”1”,”0”
Input: Array = {"10", "0", "1"}, m = 1, n = 1
Output: 2

Explanation: You could form "10", but then you'd have nothing left. Better form "0" and "1".
*/

public class OnesZeros {
    public int findMaxForms(String[] strs, int m, int n) {
       int len = strs.length;
       int[][][] dp = new int[len+1][m+1][n+1];
       for (int i = 0; i < len+1; i++) {
           int[] nums = new int[]{0,0};
           if (i > 0) {
               nums = calculate(strs[i-1]);
           }
           for (int j = 0; j < m+1; j++) {
               for (int k = 0; k < n+1; k++) {
                   if (i ==0) {
                       dp[i][j][k] = 0;
                   } else if (j >= nums[0] && k >= nums[1]) {
                      dp[i][j][k] = Math.max(dp[i-1][j][k], dp[i-1][j-nums[0]][k-nums[1]]+1);
                   } else {
                       dp[i][j][k] = dp[i-1][j][k];
                   }
               }
           }
           return dp[len][m][n];
       }
    }
    private int[] calculate(String str) {
        int[] res = new int[2];
        Arrays.fill(res, 0);
        for (char ch: str.toCharArray()) {
            if (ch == '0') {
                res[0]++;
            } else if (ch == '1') {
                res[1]++;
            }
        }
        return res;
    }

    public int findMaxForms2D(String [] strs, int m, int n) {
        int[][] dp = new int[m+1][n+1];
        for (String str: strs) {
            int[] count = count(str);
            for (int i = m; i >= count[0]; i--){
                for (int j = n; j >= count[1]; j--) {
                    dp[i][j] = Math.max(dp[i][j], 1+dp[i-count[0]][j-count[1]]);
                }
            }
        }
    }
    private int[] count(String str) {
        int[] strCount = new int[2];
         for (int i = 0; i < str.length(); i++){
             strCount[str.charAt(i)-'0']++;
         }
        return strCount;
    }
}
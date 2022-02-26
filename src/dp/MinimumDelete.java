package dp;/*
Given two strings s1, s2, find the lowest ASCII sum of deleted characters to make two strings equal.
Input: s1 = "sea", s2 = "eat"
Output: 231
Explanation: Deleting "s" from "sea" adds the ASCII value of "s" (115) to the sum.
Deleting "t" from "eat" adds 116 to the sum.
At the end, both strings are equal, and 115 + 116 = 231 is the minimum sum possible to achieve this.
*/

/*
Intuition and Algorithm

Let dp[i][j] be the answer to the problem for the strings s1[i:], s2[j:].

When one of the input strings is empty, the answer is the ASCII-sum of the other string. We can calculate 
this cumulatively using code like dp[i][s2.length()] = dp[i+1][s2.length()] + s1.codePointAt(i).

When s1[i] == s2[j], we have dp[i][j] = dp[i+1][j+1] as we can ignore these two characters.

When s1[i] != s2[j], we will have to delete at least one of them. We'll have dp[i][j] as the minimum of 
the answers after both deletion options.



    int minimumDeleteSum(string s1, string s2) {
        int m = s1.size(), n = s2.size();
        vector<int> dp(n+1, 0);
        for (int j = 1; j <= n; j++)
            dp[j] = dp[j-1]+s2[j-1];
        for (int i = 1; i <= m; i++) {
            int t1 = dp[0];
            dp[0] += s1[i-1];
            for (int j = 1; j <= n; j++) {
                int t2 = dp[j];
                dp[j] = s1[i-1] == s2[j-1]? t1:min(dp[j]+s1[i-1], dp[j-1]+s2[j-1]);
                t1 = t2;
            }
        }
        return dp[n];
    }
*/
// solve it by using longest common subsequence
public class MinimumDelete {
    public int minimumDeleteSumTopDown(String s1, String s2) {
        int s1len = s1.length();
        int s2len = s2.length();
        //
        int[][] dp = new int[s1len+1][s2len+1];
        for (int i = s1len-1; i >= 0; i--) {
            dp[i][s2len] = dp[i+1][s2len] + s1.codePointAt(i);
        }
        for (int j = s2len-1; j >= 0; j--) {
            dp[s1len][j] = dp[s1len][j+1] + s2.codePointAt(j);
        }
        for (int i = s1len -1; i >= 0; i--) {
            for (int j = s2len - 1; j >= 0; j--) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[i][j] = dp[i+1][j+1];
                } else {
                    dp[i][j] = Math.min(dp[i+1][j] + s1.codePointAt(i), dp[i][j+1] + s2.codePointAt(j));
                }
            }
        }
        return dp[0][0];
    }

    public int minimumDeleteSum(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        // dp[i][j] is the cost for s1.substr(0,i) and s2.substr(0, j)
        for (int i = 1; i < s1.length() + 1; i++) {
            dp[i][0] = dp[i-1][0] + s1.charAt(i-1);
        }
        for (int j = 1; j < s2.length() + 1; j++) {
            dp[0][j] = dp[0][j-1] + s2.charAt(j-1);
        }
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.min(dp[i-1][j] + s1.charAt(i-1), dp[i][j-1] + s2.charAt(j-1));
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }
}
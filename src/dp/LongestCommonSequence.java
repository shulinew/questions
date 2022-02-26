package dp;

import java.util.Arrays;

public class LongestCommonSequence {
    //https://leetcode.com/problems/longest-common-subsequence/discuss/349346/LC1143-Classic-DP-Longest-Common-Subsequence-With-Follow-up-Problems
    //http://yucoding.blogspot.com/2013/02/leetcode-question-123-wildcard-matching.html
    //"ezupkr", "ubmrapg"
    // "bsbininm", "jmjkbkjkv"
    // "oxcpqrsvwf","shmtulqrypy"

    public int longestCommonSubsequence(String text1, String text2) {
        // dp[i][j]: from text1 0 to i and text2 0 to j, how many common sequence
        int[][] dp = new int[text1.length()+1][text2.length()+1];
        char[] charArray1 = text1.toCharArray();
        char[] charArray2 = text2.toCharArray();
        for (int i = 1; i <= text1.length(); i++) {
            for (int j = 1; j <= text2.length(); j++) {
                if (charArray1[i-1] == charArray2[j-1]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        for (int i = 0; i <= text1.length(); i++) {
            for(int j = 0; j <= text2.length(); j++) {
                System.out.print(dp[i][j]);
                System.out.print(' ');
            }
            System.out.print("\t\n");
        }
        return dp[text1.length()][text2.length()];
    }
    // longest Palindromic subsequence
    // follow up: what is longest palindromic
    public int longestPalindromeSubseq(String s) {
        int[][] dp = new int[s.length()][s.length()];

        for (int left = s.length()-1; left >= 0; left--){
            dp[left][left] = 1;
            for (int right = left+1; right < s.length(); right++) {
                if(s.charAt(left) == s.charAt(right)) {
                    dp[left][right] = dp[left+1][right-1] + 2;
                } else {
                    dp[left][right] = Math.max(dp[left+1][right], dp[left][right-1]);
                }
            }
        }
        return dp[0][s.length()-1];
    }

    public int longestPalindromeSubseqRecursive(String s) {
        return panlindromeHelper(0, s.length()-1, s, new int[s.length()][s.length()]);
    }

    private int panlindromeHelper(int left, int right, String s, int[][] memo) {
        if (left > right) return 0;
        if (left == right) return 1;
        if (memo[left][right] != 0) {
            return memo[left][right];
        }
        int result = 0;
        if (s.charAt(left) == s.charAt(right)) {
            result = 2 + panlindromeHelper(left+1, right-1, s, memo);
        } else {
            result = Math.max(panlindromeHelper(left+1, right, s, memo), panlindromeHelper(left, right-1, s, memo));
        }
        memo[left][right] = result;
        return result;
    }

    /*
       Shortest Common Supersequence
       "abac"
        "cab"
     */
    private String longestCommonSubsequenceStr(String text1, String text2) {
        String[][] dp = new String[text1.length()+1][text2.length()+1];
        for (int i = 0; i <dp.length; i++) {
            Arrays.fill(dp[i], "");
        }
        for (int i =1; i <= text1.length(); i++) {
            for (int j = 1; j <= text2.length(); j++) {
                if (text1.charAt(i-1) == text2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + text1.charAt(i-1);
                } else {
                    dp[i][j] = dp[i-1][j].length() > dp[i][j-1].length() ? dp[i-1][j] : dp[i][j-1];
                }
            }
        }
        return dp[text1.length()][text2.length()];
    }

    public String shortestCommonSupersequence(String str1, String str2) {
        String lcs = longestCommonSubsequenceStr(str1, str2);
        StringBuilder sb = new StringBuilder();
        int pos1 = 0, pos2 = 0;
        for (int i = 0; i < lcs.length(); i++) {
            while (pos1 < str1.length() && str1.charAt(pos1) != lcs.charAt(i)) {
                sb.append(str1.charAt(pos1++));
            }
            while (pos2 < str2.length() && str2.charAt(pos2) != lcs.charAt(i)) {
                sb.append(str2.charAt(pos2++));
            }
            sb.append(lcs.charAt(i));
            pos1++;
            pos2++;
        }
        sb.append(str1.substring(pos1)).append(str2.substring(pos2));
        return sb.toString();
    }

    // Wildcard Matching
    public boolean isMatch(String s, String p) {
        // match dontes to whether s[0...i-1] match p[0...j-1]
        boolean[][] match = new boolean[s.length()+1][p.length()+1];
        match[0][0] = true; // match if nothing from s and p
        // nothing from p, no match
        for (int i = 1; i <= s.length(); i++) {
            match[i][0] = false;
        }
        // once p is not *, all the match[0][j] afterwards will be false
        for (int j = 1; j <= p.length(); j++) {
            if (p.charAt(j-1) == '*') {
                match[0][j] = true;
            } else {
                break;
            }
        }
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= p.length(); j++) {
                if (p.charAt(j-1) != '*') {
                    match[i][j] = (match[i-1][j-1] && (p.charAt(j-1) == s.charAt(i-1) || p.charAt(j-1) == '?'));
                } else {
                    match[i][j] = match[i-1][j] || match[i][j-1];
                }
            }
        }
        return match[s.length()][p.length()];
    }

    public boolean isMatchRecursive(String s, String p) {
        return isMatchHelper(s, p, s.length()-1, p.length()-1, new int[s.length()][p.length()]);
    }
    private boolean isMatchHelper(String s, String p, int i, int j, int[][] memo) {
        if (i < 0 && j >= 0) {
            while(j >= 0 && p.charAt(j) == '*') {
                j--;
            }
            return j < 0;
        } else if (i< 0 && j < 0) {
            return true;
        } else if (i >= 0 && j < 0) {
            return false;
        } else if (memo[i][j] != 0) {
            return memo[i][j] == 1;
        } else {
            if (p.charAt(j) == '?' || p.charAt(j) == s.charAt(i)) {
                boolean isMatch = isMatchHelper(s, p, i-1, j-1, memo);
                memo[i][j] = isMatch ? 1 : -1;
                return isMatch;
            } else if (p.charAt(j) == '*') {
                boolean isMatch = isMatchHelper(s, p, i-1, j, memo) || isMatchHelper(s, p, i, j-1, memo);
                memo[i][j] = isMatch ? 1: -1;
                return isMatch;
            } else {
                memo[i][j] = -1;
                return false;
            }
         }
    }
    /*
        '.' Matches any single character.
        '*' Matches zero or more of the preceding element. * can't be the first character
     */
    public boolean isMatchRegex(String s, String p) {
        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        dp[0][0] = true;
        for (int i = 1; i <= p.length(); i++){
            if (p.charAt(i-1) == '*' && dp[0][i-2]){
                dp[0][i] = true;

            }
        }
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= p.length(); j++) {
                if (p.charAt(j-1) == '.' || p.charAt(j-1) == s.charAt(i-1)) {
                    dp[i][j] = dp[i-1][j-1];
                }
                if (p.charAt(j-1) == '*') {
                    if (p.charAt(j-2) != s.charAt(i-1) && p.charAt(j-2) != '.') {
                        dp[i][j] = dp[i][j-2];
                    } else {
                        dp[i][j] = dp[i][j-1] || dp[i-1][j] || dp[i][j-2];
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }

    /*
    Edit distance: minimum operation of make word1 same as word2
     */
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] costs = new int[m+1][n+1];
        // follow up: why init need m+1
        //Cost of edit word2 when there is no char from word1
        for (int i = 0; i <= n; i++) {
            costs[0][i] = i;
        }
        //Cost of edit word1 when there is no char from word2
        for(int j = 0; j <= m; j++) {
            costs[j][0] = j;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (word1.charAt(i) == word2.charAt(j)) {
                    costs[i+1][j+1] = costs[i][j];
                } else {
                    // costs[i][j]: replace
                    // costs[i][j+1]: delete
                    // costs[i+1][j]: insert
                    costs[i+1][j+1] = Math.min(costs[i][j], Math.min(costs[i][j+1], costs[i+1][j])) + 1;
                }
            }
        }
        return costs[m][n];
    }

    // word ladder
    public int miniDistanceLessSpace(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        int[] costs = new int[n+1];
        for (int i = 0; i <= n; i++) {
            costs[i] = i;
        }
        for (int i = 1; i <= m; i++) {
            int previous = i;
            for (int j = 1; j <=n; j++) {
                int current;
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    current = costs[j-1];
                } else {
                    current = Math.min(costs[j], Math.min(costs[j-1], previous)) + 1;
                }
                costs[j-1] = previous;
                previous = current;
            }
            costs[n] = previous;
        }
        return costs[n];
    }
}

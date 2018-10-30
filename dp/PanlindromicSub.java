/*
 Given a string, your task is to count how many palindromic substrings in this string.

The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.

Example 1:

Input: "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".

Example 2:

Input: "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
*/

public class PanlindromicSub {
    int count = 0;
    
    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) return 0;
        
        for (int i = 0; i < s.length(); i++) { // i is the mid point
            extendPalindrome(s, i, i); // odd length;
            extendPalindrome(s, i, i + 1); // even length
        }
        
        return count;
    }
    
    private void extendPalindrome(String s, int left, int right) {
        while (left >=0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++; left--; right++;
        }
    }

    public int countSubstrings1(String s) {
        
        int sLen = s.length();
        char[] cArr = s.toCharArray();
        
        int totalPallindromes = 0;
        
        boolean[][] dp = new boolean[sLen][sLen];
        
        // Single length pallindroms
        for (int i = 0; i < sLen; i++) {
            dp[i][i] = true;
            totalPallindromes++;
        }
        
        // 2 length pallindromes
        for (int i = 0; i < sLen - 1; i++) {
            if (cArr[i] == cArr[i + 1]) {
                dp[i][i + 1] = true;
                totalPallindromes++;
            }
        }

        // Lengths > 3
        
        for (int subLen = 2; subLen < sLen; subLen++) {
            
            for (int i = 0; i < sLen - subLen; i++) {
                
                int j = i + subLen;
                
                if (dp[i + 1][j - 1] && cArr[i] == cArr[j]) {
                    dp[i][j] = true;
                    totalPallindromes++;
                }
            }
        }        
        return totalPallindromes;    
    }

/*
Intuition
Let N be the length of the string. The middle of the palindrome could be in one of 2N - 1 positions: 
either at letter or between two letters. For each center, let's count all the palindromes that have this
center. Notice that if [a, b] is a palindromic interval (meaning S[a], S[a+1], ..., S[b] is a palindrome), 
then [a+1, b-1] is one too. Algorithm For each possible palindrome center, let's expand our candidate 
palindrome on the interval [left, right] as long as we can. The condition for expanding is left >= 0 
and right < N and S[left] == S[right]. That means we want to count a new palindrome S[left], S[left+1], ..., S[right].
*/
    public int countSubstrings(String S) {
        int N = S.length(), ans = 0;
        for (int center = 0; center <= 2*N-1; ++center) {
            int left = center / 2;
            int right = left + center % 2;
            while (left >= 0 && right < N && S.charAt(left) == S.charAt(right)) {
                ans++;
                left--;
                right++;
            }
        }
        return ans;
    }

    public int countSubstrings(String s) {
        int n = s.length();
        int res = 0;
        boolean[][] dp = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 3 || dp[i + 1][j - 1]);
                if(dp[i][j]) ++res;
            }
        }
        return res;
    }
}
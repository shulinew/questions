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
package dp;

public class PanlindromicSub {
    int count = 0;

    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) return 0;
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            count = extendPalindrome(s, i, i, count);
            count = extendPalindrome(s, i, i+1, count);
        }
        return count;
    }
    private int extendPalindrome(String s, int left, int right, int count) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++;
            left--;
            right++;
        }
        return count;
    }

    public int countSubstrings2(String s) {
        
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
    public int countSubstrings1(String s) {
        int len = s.length();
        int count = 0;
        for (int center = 0; center <= 2*len-1; center++){
            int left = center / 2;
            int right = left + center % 2;
            while(left >= 0 && right < len && s.charAt(left) == s.charAt(right)){
                count++;
                left--;
                right++;
            }
        }
        return count;
    }

    public int countSubstringsDP(String s) {
        int len = s.length();
        int count = 0;
        // dp[left][right] whether from left to right is panlindromic
        boolean dp[][] = new boolean[len][len];
        for (int left = len-1; left >= 0; left--) {
            for (int right = left; right < len; right++) {
                dp[left][right] = s.charAt(left) == s.charAt(right) && (right - left < 3 || dp[left+1][right-1]);
                if (dp[left][right]) {
                    count++;
                }
            }
        }
        return count;
    }

    ///////////////////////////////////////Longest panlindromic//////////////////////////////////////////////////
    public String longestPalindrome(String s) {
        int len = s.length();
        int maxLen = 0;
        String subString = "";
        for (int center = 0; center <= 2*len-1; center++) {
            int left = center/2;
            int right = left + center%2;
            while(left >= 0 && right < len && s.charAt(left) == s.charAt(right)){
                if (right - left + 1 > maxLen) {
                    maxLen = right - left + 1;
                    subString = s.substring(left, right+1);
                }
                left--;
                right++;
            }
        }
        return subString;
    }
    int maxLen = 0;
    String subString;
    public String longestPalindrome1(String s) {
        int len = s.length();
        for (int i = 0; i < len; i++) {
            extendPalindrom(s, i, i);
            extendPalindrom(s, i, i+1);
        }
        return subString;
    }
    private void extendPalindrom(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            if (right - left + 1 > maxLen) {
                maxLen = right - left + 1;
                subString = s.substring(left, right+1);
            }
            left--;
            right++;
        }
    }
    public String longestPalindromeDP(String s) {
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        String result = "";
        for (int left = len -1; left >= 0; left--) {
            for (int right = left; right < len; right++){
                dp[left][right] = (s.charAt(left) == s.charAt(right) && (right - left < 3 || dp[left+1][right-1]));
                if (dp[left][right] && (result.length() == 0 || right - left + 1 > result.length())) {
                    result = s.substring(left, right+1);
                }
            }
        }
        return result;
    }
    public static void main (String[] arguments) {
        PanlindromicSub panlindromic = new PanlindromicSub();
        System.out.println(panlindromic.longestPalindrome1("babad"));
    }

}
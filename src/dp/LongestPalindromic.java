package dp;
public class LongestPalindromic {
    private int lo, maxLen;

    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2)
            return s;
        
        for (int i = 0; i < len-1; i++) {
            extendPalindrome(s, i, i);  //assume odd length, try to extend Palindrome as possible
            extendPalindrome(s, i, i+1); //assume even length.
        }
        return s.substring(lo, lo + maxLen);
    }

    private void extendPalindrome(String s, int j, int k) {
        while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
            j--;
            k++;
        }
        if (maxLen < k - j - 1) {
            lo = j + 1;
            maxLen = k - j - 1;
        }
    }

    public String longestPalindrome1(String s) {
        int n = s.length();
        String res = null;
            
        boolean[][] dp = new boolean[n][n];
            
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
            dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 3 || dp[i + 1][j - 1]);
                    
            if (dp[i][j] && (res == null || j - i + 1 > res.length())) {
                res = s.substring(i, j + 1);
            }
            }
        }
            
        return res;
    }
    public String longestPalindromeBrutal(String s) {
        int length = s.length();
        for (int i = 0; i < length; i++) {
            for (int len = 1; len <= length-i; len++) {
                String subString = s.substring(i, len);
            }
        }
    }
    private boolean isPalindrome(String s) {
        if (s.length() == 0 || s.length() == 1) {
            return true;
        }
        char[] chars = s.toCharArray();
        int left = 0, right = s.length()-1;
        while (left <= right && chars[left] == chars[right]) {
            left++;
            right--;
        }
        return (left >= right) ? true : false;
    }
    public static void main(String[] args) {
        LongestPalindromic test = new LongestPalindromic();
        System.out.println(test.isPalindrome("aaa"));
        System.out.println(test.isPalindrome("a"));
        System.out.println(test.isPalindrome("ab"));
    }
}
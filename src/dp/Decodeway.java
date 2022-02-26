package dp;/*
'A' -> 1
'B' -> 2
...
'Z' -> 26
Given a non-empty string containing only digits, determine the total number of ways to decode it.

Input: "12"
Output: 2
Explanation: It could be decoded as "AB" (1 2) or "L" (12).
*/

public class Decodeway {

    public int numDecodingsDP1(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = s.charAt(0) != '0' ? 1 : 0;
        for(int i = 2; i <= n; i++) {
            int first = Integer.valueOf(s.substring(i-1, i));
            int second = Integer.valueOf(s.substring(i-2, i));
            if(first >= 1 && first <= 9) {
               dp[i] += dp[i-1];  
            }
            if(second >= 10 && second <= 26) {
                dp[i] += dp[i-2];
            }
        }
        return dp[n];
    }

    public int numDecodings(String s) {
        if (s.length () == 0)
            return 0;
        char[] chs = s.toCharArray ();
        int[] dp = new int[chs.length];
        dp[0] = chs[0] > '0' ? 1 : 0;
        for (int i = 1; i < dp.length; i++) {
            if (chs[i] > '0')
                dp[i] += dp[i - 1];
            if (chs[i - 1] > '0' && ((chs[i - 1] - '0') * 10 + chs[i] - '0' <= 26))
                // if i-1..i are the first two digits, then this is actually the new base case: assign 1 rather than 0
                dp[i] += i - 2 >= 0 ? dp[i - 2] : 1; 
        }
        return dp[chs.length - 1];
    }

    public int numDecodingsDPLessMemory(String s) {
        if (s == null || s.length() == 0) return 0;
        // num1: decode way of i-1, num2: decode way of i-2
        int num1 = s.charAt(0) == '0' ? 0 : 1, num2 =1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                num1 = 0;
            }
            // two digit letter, num1 is sum of both, num2 is old num1
            if (s.charAt(i -1) == '1' || s.charAt(i - 1) == '2' && s.charAt(i) <= '6'){
                num1 = num2 + num1;
                num2 = num1 - num2;
            // one-digit letter, no new way added
            } else {
                num2 = num1;
            }
        }
        return num1;
    }

    public int numDecodings1(String s) {
        final int len = s.length();
        if (len == 0)
            throw new IllegalArgumentException("s can't be empty");

        int pre = 1, cur = s.charAt(0) == '0' ? 0 : 1, tmp;
        for (int i = 1; i < len && cur != 0; ++i) {
            tmp = cur;
            if (s.charAt(i - 1) == '1' || (s.charAt(i - 1) == '2' && s.charAt(i) <= '6')) {
                if (s.charAt(i) == '0')
                    cur = pre;
                else
                    cur += pre;
            } else if (s.charAt(i) == '0') {
                cur = 0;
            }
            pre = tmp;
        }
        return cur;
    }

    public int numDecodingsDP(String number) {
        char[] arr = number.toCharArray();
        int length = number.length();
        int[] dp = new int[length + 1];
        dp[length] = 1;
        dp[length - 1] = arr[length - 1] == '0' ? 0 : 1;
        for (int i = length - 2; i >= 0; i--) {
            if (Integer.parseInt(number.substring(i, i + 2)) <= 26) {
                dp[i] = dp[i + 1] + dp[i + 2];
            }
        }
        return dp[0];
    }

}
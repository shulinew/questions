package dp;

/*
nitially on a notepad only one character 'A' is present. You can perform two operations on this notepad for each step:

    Copy All: You can copy all the characters present on the notepad (partial copy is not allowed).
    Paste: You can paste the characters which are copied last time.

Given a number n. You have to get exactly n 'A' on the notepad by performing the minimum number of steps permitted. Output the minimum number of steps to get n 'A'. 
Input: 3
Output: 3
Explanation:
Intitally, we have one character 'A'.
In step 1, we use Copy All operation.
In step 2, we use Paste operation to get 'AA'.
In step 3, we use Paste operation to get 'AAA'.

We can break our moves into groups of (copy, paste, ..., paste). Let C denote copying and P denote 
pasting. Then for example, in the sequence of moves CPPCPPPPCP, the groups would be [CPP][CPPPP][CP].

Say these groups have lengths g_1, g_2, .... After parsing the first group, there are g_1 'A's. 
After parsing the second group, there are g_1 * g_2 'A's, and so on. At the end, there are 
g_1 * g_2 * ... * g_n 'A's.

We want exactly N = g_1 * g_2 * ... * g_n. If any of the g_i are composite, say g_i = p * q, then 
we can split this group into two groups (the first of which has one copy followed by p-1 pastes, 
while the second group having one copy and q-1 pastes).

Such a split never uses more moves: we use p+q moves when splitting, and pq moves previously. 
As p+q <= pq is equivalent to 1 <= (p-1)(q-1), which is true as long as p >= 2 and q >= 2.

Algorithm By the above argument, we can suppose g_1, g_2, ... is the prime factorization of N, 
and the answer is therefore the sum of these prime factors.

https://leetcode.com/problems/2-keys-keyboard/discuss/105932/Java-solutions-from-naive-DP-to-optimized-DP-to-non-DP
*/
public class TwoKeyBoard {
    public int miniSteps(int n) {
        int result = 0, d = 2;
        while (n > 1) {
            while (n % d == 0) {
                result += d;
                n /= d;
            }
            d++;
        }
        return result;
    }

    public int miniStepsDP(int n) {
//        Steps for i
        int[] dp = new int[n+1];
        for (int i = 2; i <= n; i++) {
            dp[i] = i;
            for (int j = i-1; j > 1; j--) {
                if (i % j == 0) {
                    dp[i] = dp[j] + (i/j);
                    break;
                }
            }
        }
        return dp[n];
    }

    public int minStepsRecursive(int n) {
        if (n == 1) return 0;
        int[] ans = new int[1];
        ans[0] = Integer.MAX_VALUE;
        helper(n, 1, 1, ans, 1); 
        return ans[0];
    }   

    private void helper(int n, int now, int p, int[] ans, int step) {
        if (n == now) {
            ans[0] = Math.min(ans[0], step);
            return;
        }   
        if (n < now || step > ans[0]) return;
        helper(n, now + now, now, ans, step + 2); //copy + paste
        helper(n, now + p, p, ans, step + 1); //copy
    }  
}
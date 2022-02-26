/* https://leetcode.com/problems/domino-and-tromino-tiling/discuss/116581/Detail-and-explanation-of-O(n)-solution-why-dpn2*dn-1%2Bdpn-3
https://cs.stackexchange.com/questions/66658/domino-and-tromino-combined-tiling
(In a tiling, every square must be covered by a tile. Two tilings are different if and only if there are two 4-directionally adjacent cells on the board such that exactly one of the tilings has both squares occupied by a tile.)
Here is my understanding of why dp[n] = dp[n-1] + dp[n-2] + dp[n-3] * 2 + ... dp[0] * 2:
For all f(n-1), it can be transformed to f(n) by appending a domino |, hence f(n - 1) * 1.
For all f(n - 2), though f(2) contains 2 cases: = and ||, the || case was covered by f(n - 1) plus |,
so literally there is only one distinct way to transform f(n-2) to f(n), hence f(n - 2) * 1.
For all f(n - i) where i > 2, all the ways complementing them to f(n) by dominos (either horizontal or vertical domino) have been covered by f(n - 1) and f(n - 2)
hence the only distinct way to transform them is appending trimino or combination of trimino and domino. And there are always only 2 ways to do this (as demonstrated in the picture).

dp[n]=dp[n-1]+dp[n-2]+ 2*(dp[n-3]+...+d[0])
=dp[n-1]+dp[n-2]+dp[n-3]+dp[n-3]+2*(dp[n-4]+...+d[0])
=dp[n-1]+dp[n-3]+(dp[n-2]+dp[n-3]+2*(dp[n-4]+...+d[0]))
=dp[n-1]+dp[n-3]+dp[n-1]
=2*dp[n-1]+dp[n-3]  // The goal is to get dp[n] depends on only n-1 and n-2

follow-up: if there is 2X1 and 1X1, how many ways to do 2XN vs MXN
*/

public class Domino {
    public int numTilings(int N) {
        int mold = 1000000007;
        int[] dp = new int[N+1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 5;
        if (N <= 3) {
            return dp[N];
        }
        for (int i = 4; i <= N; i++){
            dp[i] = (2*dp[n-1] % mold + dp[n-3] % mold)%mold;
        }
        return dp[N];
    }
}
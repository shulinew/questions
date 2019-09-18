/*
Alex and Lee play a game with piles of stones.  There are an even number of piles 
arranged in a row, and each pile has a positive integer number of stones piles[i].

The objective of the game is to end with the most stones.  The total number of 
stones is odd, so there are no ties.

Alex and Lee take turns, with Alex starting first.  Each turn, a player takes 
the entire pile of stones from either the beginning or the end of the row.  
This continues until there are no more piles left, at which point the person 
with the most stones wins.

Assuming Alex and Lee play optimally, return True if and only if Alex wins the 
game.

 

Example 1:

Input: [5,3,4,5]
Output: true
Explanation: 
Alex starts first, and can only take the first 5 or the last 5.
Say he takes the first 5, so that the row becomes [3, 4, 5].
If Lee takes 3, then the board is [4, 5], and Alex takes 5 to win with 10 points.
If Lee takes the last 5, then the board is [3, 4], and Alex takes 4 to win with 9 points.
This demonstrated that taking the first 5 was a winning move for Alex, so we return true.
https://leetcode.com/problems/stone-game/discuss/154610/DP-or-Just-return-true
*/
package dp;

public class StoneGame {
    public boolean stoneGame(int[] piles) {
        int length = piles.length;
        // dp[i+1][j+1] is value of the game piles[i], piles[i+1], ..., piles[j]
        int[][] dp = new int[length+2][length+2];
        for (int size = 1; size <= length; size++) {
            for(int i = 0; i + size <= length; i++) {
                int j = i + size -1;
                int parity = (j + i + length) %2;
                if (parity == 1) { // Alex's turn
                    dp[i+1][j+1] = Math.max(piles[i] + dp[i+2][j+1], piles[j] + dp[i+1][j+2]);
                } else {
                    dp[i+1][j+1] = Math.min(dp[i+2][j+1] - piles[i], dp[i+1][j+2] - piles[j]);
                }
//                dp[i][i + d] = Math.max(piles[i] - dp[i + 1][i + d], piles[i + d] - dp[i][i + d - 1]);
            }
        }
        return dp[1][length] > 0;
    }

    private int[] sums; // sum from pile[i] to end
    private int[][] memo;
    public int stoneGame2(int[] piles) {
        if (piles.length == 0) return 0;
        int length = piles.length;
        sums = new int[length];
        sums[length-1] = piles[length-1];
        for (int i = length-2; i >= 0; i--) {
            sums[i] = sums[i + 1] + piles[i];
        }
        memo = new int[length][length];
        return helper(piles, 0 , 1);
    }
    private int helper(int[] piles, int i, int M) {
        if (i == piles.length) return 0;
        if (2*M >= piles.length - i) {
            return sums[i];
        }
        if (memo[i][M] != 0) return memo[i][M];
        int min = Integer.MAX_VALUE; // min value the next player can get
        for (int x = 1; x <= 2*M; x++) { // x is number the other player will get
            min = Math.min(min, helper(piles, i+x, Math.max(M,x)));
        }
        memo[i][M] = sums[i] - min;
        return memo[i][M];
    }
    int stoneGameII(int[] piles) {
        int length = piles.length;
        // dp[i][j] is maxmimum stone Alex can get when start at i with M=j
        int dp[][] = new int[length+1][length+1];
        int sum[] = new int[length+1];
        for (int i = length - 1; i >= 0; i--) {
            sum[i] = sum[i + 1] + piles[i];
        }
        for (int i = 0; i <= length; i++) {
            dp[i][length] = sum[i];
        }
        for (int i = length - 1; i >= 0; i--) {
            for (int j = length - 1; j >= 1; j--) {
                for (int x = 1; x <= 2 * j && i + x <= length; x++) {
                    dp[i][j] = Math.max(dp[i][j], sum[i] - dp[i + x][Math.max(j, x)]);
                }
            }
        }
        return dp[0][1];
    }
}
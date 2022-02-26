/* https://leetcode.com/problems/guess-number-higher-or-lower-ii/discuss/84766/Clarification-on-the-problem-description.-Problem-description-need-to-be-updated-!!!
We are playing the Guess Game. The game is as follows:

I pick a number from 1 to n. You have to guess which number I picked.

Every time you guess wrong, I'll tell you whether the number I picked is higher or lower.

However, when you guess a particular number x, and you guess wrong, you pay $x. You win the game when you guess the number I picked.

n = 10, I pick 8.

First round:  You guess 5, I tell you that it's higher. You pay $5.
Second round: You guess 7, I tell you that it's higher. You pay $7.
Third round:  You guess 9, I tell you that it's lower. You pay $9.

Game over. 8 is the number I picked.

You end up paying $5 + $7 + $9 = $21.

*/



/*
We are playing the Guess Game. The game is as follows:

I pick a number from 1 to n. You have to guess which number I picked.

Every time you guess wrong, I'll tell you whether the number is higher or lower.

You call a pre-defined API guess(int num) which returns 3 possible results (-1, 1, or 0):

-1 : My number is lower
 1 : My number is higher
 0 : Congrats! You got it!

 n = 10, I pick 6.

Return 6.

*/
public class GuessGame {
    public int getMoneyAmount(int n) {
        int[][] memo = new int[n+1][n+1];
        return getMoneyAmount(memo, 1, n);
    }
    private int getMoneyAmount(int[][] memo, int start, int end) {
        if (start >= end) return 0;
        if (memo[start][end] != 0) return memo[start][end];
        int result = Integer.MAX_VALUE;
        for (int i = start; i <= end; i++) {
            int value = i + Math.max(getMoneyAmount(memo, start, i-1), 
                                    getMoneyAmount(memo, i+1, end));
            result = Math.min(result, value);
        }
        memo[start][end] = result;
        return result;
    }

    public int getMoneyAmount(int n) {
        int[][] table = new int[n+1][n+1];
        for(int j=2; j<=n; j++){
            for(int i=j-1; i>0; i--){
                int globalMin = Integer.MAX_VALUE;
                for(int k=i+1; k<j; k++){
                    int localMax = k + Math.max(table[i][k-1], table[k+1][j]);
                    globalMin = Math.min(globalMin, localMax);
                }
                table[i][j] = i+1==j?i:globalMin;
            }
        }
        return table[1][n];
    }

    // How to write the two for loops?
    // if we draw the matrix, then according to the induction rule: 
    // M[i][j] = k + Math.max(M[i][k - 1], M[k + 1][j]) for k = [i : j], 
    // we notice that M[i][k - 1] is in the left of M[i][j], and M[k + 1][j] is to the bottom of
    //  M[i][j]. So we need to compute these value before calculating M[i][j]. So we write the 
    //  matrix from left to right, from bottom to top and since i <= j, so we can just iterate 
    //  half of matrix by starting j from i.

    public int getMoneyAmountBottomUp(int n) {
        int[][] M = new int[n + 2][n + 2]; //why n+2
    
        for(int i = n; i >= 1; i--){ // from bottom to top
            for(int j = i; j <= n; j++){ // from left to right
                if(i == j){
                    M[i][j] = 0;
                }else{
                    M[i][j] = Integer.MAX_VALUE;
                    for(int k = i; k <= j; k++){
                        int temp = k + Math.max(M[i][k - 1], M[k + 1][j]);
                        M[i][j] = Math.min(M[i][j], temp);
                    }
                }
            }
        }
        return M[1][n];
    }

}
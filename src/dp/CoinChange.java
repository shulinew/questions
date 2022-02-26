/*You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
*/
public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        int dp[] = new int[amount+1];
        for(int i=1;i<=amount;i++){
            int min = Integer.MAX_VALUE;
            for(int coin:coins){
               if(i-coin>=0 && dp[i-coin] != -1)
                   min = dp[i-coin] < min ? dp[i-coin] :min; 
            }
            // Set dp[i] to -1 if i (current amount) can not be reach by  coins array
            dp[i] = min== Integer.MAX_VALUE ? -1 : 1+min;
        }
        return dp[amount];
    }

    public int coinChangeMemo(int[] coins, int amount){
        int min = Integer.MAX_VALUE;
        int[][] memo = new int[coins.length][amount+1];
        min = helperMin(0, coins, amount);
        return min == Integer.MAX_VALUE ? -1:min;
    }
    private int helperMin(int start, int[] coins, int amount){
        int result = Integer.MAX_VALUE;
        if(start == coins.length) {
            return amount == 0 ? 0:result;
        }
        if (amount < 0){
            return result;
        }
        if (amount == 0) {
            return 0;
        }
        if (memo[start][amount] > 0 ){
            return memo[start][amount];
        }
        int useCoin = helperMin(start, coins, amount-coins[start]);
        int notUseCoin = helperMin(start+1, coins, amount);
        if (useCoin != Integer.MAX_VALUE){
            result = Math.min(result, useCoin + 1);
        } 
        result = Math.min(result, notUseCoin);
        memo[start][amount] = result;
        return result;
    }

    public int coinChangeBrutalForce(int[] coins, int amount) {
        int[][][] memo = new int[coins.length][amount+1][amount+1];
        return coinChange(0, coins, amount, memo);
    }
    private int coinChange(int start, int[] coins, int amount, int[][][] memo){
       if (amount == 0){
           return 0;
       }
       if (start == coins.length || amount < 0) {
           return -1;
       }
       int maxVal = amount/coins[start];
       int minCost = Integer.MAX_VALUE;
       for (int i = 0; i <= maxVal; i++){
           if (amount >= i * coins[start]) {
               if (memo[start][amount][i] > 0) {
                   return memo[start][amount][i];
               }
               int result = coinChange(start+1, coins, amount-i*coins[start], memo);
               if (result != -1){
                   minCost = Math.min(minCost, result + i);
                   memo[start][amount][i] = minCost;
               }
           }
        }
       return (minCost == Integer.MAX_VALUE) ? -1: minCost;
    }
    public int coinChange(int[] coins, int amount) {
        return coinChange(coins, amount, new int[amount]);
    }
    //count[i]: minimum for amount is i
    private int coinChange(int[] coins, int amount, int[] count){
        if (amount < 0) {
            return -1;
        }
        if (amount == 0) {
            return 0;
        }
        if (count[amount-1] != 0) {
            return count[amount-1];
        }
        int min = Integer.MAX_VALUE;
        for (int coin: coins) {
            int result = coinChange(coins, amount - coin, count);
            if (result >= 0) {
                min = Math.min(min, result + 1);
            }
        }
        count[amount - 1] = (min == Integer.MAX_VALUE) ? -1: min;
        return count[amount-1];
    }
    //Bottom up
    public int coinChange(int[] coins, int amount) {
        int max = amount + 1;             
        int[] dp = new int[amount + 1];  
        Arrays.fill(dp, max);  
        dp[0] = 0;   
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
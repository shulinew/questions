/*


Say you have an array for which the ith element is the price of a given stock on day i.
If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.
Note that you cannot sell a stock before you buy one.

We are looking for the most profitable trade. On any given day, I can either be in a trade, or not. 
If I'm in a trade, my profit at the end of that day is Profit(i) = prices[i] - prices[i-1] + Profit(i-1): 
the price movement today, plus the profit from yesterday. If I'm not in a trade, my profit is 0. 
Since I'm looking for profitable trades, I can take the max of that:

Profit(i) = Math.max(prices[i] - prices[i-1] - Profit(i-1))

We can write a loop to find profit on day i like this:

    let profit = []; profit[0] = 0;
    for(let i=1;i<prices.length;i++) {
    profit[i] = Math.max(prices[i] - prices[i-1] + profit[i-1], 0);
    }

We don't actually need profit on all days, just max profit, so that's easy to simplify:

    let maxProfit = 0, prevProfit = 0;
    for(let i=1;i<prices.length;i++) {
    let profit = Math.max(prices[i] - prices[i-1] + prevProfit, 0);
    maxProfit = Math.max(profit, maxProfit);
    prevProfit = profit;
    }
return maxProfit;
https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/
*/

public class BuySellStockI {
    /*
     Ex: for {1, 7, 4, 11}, if he gives {0, 6, -3, 7}, you might end up being confused.

    Here, the logic is to calculate the difference (maxCur += prices[i] - prices[i-1]) of the original 
    array, and find a contiguous subarray giving maximum profit. If the difference falls below 0, reset it 
    to zero.
    Quick, informal proof on why this problem can be reduced to a contiguous subarray of differences question.

Suppose we have prices: [1, 7, 4, 11] In other words, [a, b, c, d]

The maximum here is 11 - 1, in other words d - a.
The maximum subarray would be: (b-a) + (c-b) + (d-c) = -a + b - b + c - c + d = d - a

Hence, the problem is a bijection to the subarray question.

    */
    public int maxProfit(int[] prices) {
        int maxCur = 0, maxSoFar = 0;
        for(int i = 1; i < prices.length; i++) {
            maxCur = Math.max(0, maxCur += prices[i] - prices[i-1]);
            maxSoFar = Math.max(maxCur, maxSoFar);
        }
        return maxSoFar;
    }

    public int maxProfit1(int[] prices) {
        int maxBuyPrice = prices[0];
        int maxSellPrice = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (maxBuyPrice > prices[i]) {
                maxBuyPrice = prices[i];
            }
            if (maxSellPrice < prices[i]) {
                maxSellPrice = prices[i];
            }
        }
        return maxSellPrice - maxBuyPrice;
    }
}
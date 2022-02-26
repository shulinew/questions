package dp;

/*
Your are given an array of integers prices, for which the i-th element is the price of a given stock on day i; 
and a non-negative integer fee representing a transaction fee.

You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction. 
You may not buy more than 1 share of a stock at a time (ie. you must sell the stock share before you buy again.)

Input: prices = [1, 3, 2, 8, 4, 9], fee = 2
Output: 8
Explanation: The maximum profit can be achieved by:
Buying at prices[0] = 1
Selling at prices[3] = 8
Buying at prices[4] = 4
Selling at prices[5] = 9
The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
Return the maximum profit you can make.
*/
public class BuySellStock {

    public int maxProfit1(int[] prices, int fee) {
        if(prices.length <= 1) return 0;
        int[] buy = new int[prices.length];
        int[] hold = new int[prices.length];
        int[] skip = new int[prices.length];
        int[] sell = new int[prices.length];
        // the moment we buy a stock, our balance should decrease
        buy[0] = 0 - prices[0]; 
        // assume if we have stock in the first day, we are still in deficit
        hold[0] = 0 - prices[0];
        for(int i = 1; i < prices.length; i++){
            // We can only buy on today if we sold stock
            // or skipped with empty portfolio yesterday
            buy[i] = Math.max(skip[i-1], sell[i-1]) - prices[i]; 
            // Can only hold if we bought or already holding stock yesterday
            hold[i] = Math.max(buy[i-1], hold[i-1]);
            // Can skip only if we skipped, or sold stock yesterday
            skip[i] = Math.max(skip[i-1], sell[i-1]);
            // Can sell only if we bought, or held stock yesterday
            sell[i] = Math.max(buy[i-1], hold[i-1]) + prices[i] - fee;
        }
        // Get the max of all the 4 actions on the last day.
        int max = Math.max(buy[prices.length - 1], hold[prices.length - 1]);
        max = Math.max(skip[prices.length - 1], max);
        max = Math.max(sell[prices.length - 1], max);
        return Math.max(max, 0);
    }

    public int maxProfitDP1(int[] prices, int fee) {
        if (prices.length == 0) return 0;
        int[] buy = new int[prices.length];
        int[] hold = new int[prices.length];
        int[] skip = new int[prices.length];
        int[] sell = new int[prices.length];
        buy[0] = -prices[0];
        hold[0] = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            buy[i] = Math.max(skip[i-1], sell[i-1]) - prices[i];
            hold[i] = Math.max(hold[i-1], buy[i-1]);
            skip[i] = Math.max(skip[i-1], sell[i-1]);
            sell[i] = Math.max(buy[i-1], hold[i-1]) + prices[i] - fee;
        }
        int max = Math.max(buy[prices.length -1], hold[prices.length-1]);
        max = Math.max(max, skip[prices.length-1]);
        max = Math.max(max, sell[prices.length-1]);
        return max;
    }

    public int maxProfitDPLessMemory(int[] prices, int fee) {
        if (prices.length == 0) return 0;
        int[] buy = new int[prices.length];
        int[] sell = new int[prices.length];
        buy[0] = -prices[0] - fee;
        for (int i = 1; i < prices.length; i++) {
            // buy[i-1] means hold
            buy[i] = Math.max(buy[i-1], sell[i-1] - prices[i] - fee);
            sell[i] = Math.max(buy[i-1] + prices[i], sell[i-1]); // keep the same as day i-1, or sell from buy status at day i-1
        }
        return sell[prices.length-1];
    }

    public int maxProfit(int[] prices, int fee) {
        if (prices.length == 0) return 0;
        int cash = 0;
        int hold = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            cash = Math.max (cash, hold + prices[i] - fee);
            hold = Math.max(hold, cash - prices[i]);
        }
        return cash;
    }

    /*
    1) buy status:
buy[i] represents the max profit at day i in buy status, given that the last action you took is a buy action at day K, where K<=i. And you have the right to sell at day i+1, or do nothing.
(2) sell status:
sell[i] represents the max profit at day i in sell status, given that the last action you took is a sell action at day K, where K<=i. And you have the right to buy at day i+1, or do nothing.

Let's walk through from base case.

Base case:
We can start from buy status, which means we buy stock at day 0.
buy[0]=-prices[0];
Or we can start from sell status, which means we sell stock at day 0.
Given that we don't have any stock at hand in day 0, we set sell status to be 0.
sell[0]=0;

Status transformation:
At day i, we may buy stock (from previous sell status) or do nothing (from previous buy status):
buy[i] = Math.max(buy[i - 1], sell[i - 1] - prices[i]);
Or
At day i, we may sell stock (from previous buy status) or keep holding (from previous sell status):
sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);

Finally:
We will return sell[last_day] as our result, which represents the max profit at the last day, given that you took sell action at any day before the last day.

We can apply transaction fee at either buy status or sell status.
    */

    public int maxProfitLessMemory(int[] prices, int fee) {
        int cash = 0, hold = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            cash = Math.max(cash, hold + prices[i] - fee);
            hold = Math.max(hold, cash - prices[i]);
        }
        return cash;
    }
    public int maxProfit2(int[] prices, int fee) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int len = prices.length;
        int buy = -prices[0], sell = 0;
        for (int i = 1; i < len; i++) {
            int tmpbuy = buy;
            buy = Math.max(buy, sell - prices[i]);
            sell = Math.max(sell, tmpbuy + prices[i] - fee);
        }
        return Math.max(buy, sell);
    }

}
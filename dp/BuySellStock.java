/*
Your are given an array of integers prices, for which the i-th element is the price of a given stock on day i; 
and a non-negative integer fee representing a transaction fee.

You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction. 
You may not buy more than 1 share of a stock at a time (ie. you must sell the stock share before you buy again.)

Return the maximum profit you can make.
*/
public class BuySellStock {
    public int maxProfit(int[] prices, int fee) {
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

    public int maxProfit(int[] prices, int fee) {
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
    public int maxProfit(int[] prices, int fee) {
        if (prices.length <= 1) return 0;
        int days = prices.length, buy[] = new int[days], sell[] = new int[days];
        buy[0]=-prices[0]-fee;
        for (int i = 1; i<days; i++) {
            buy[i] = Math.max(buy[i - 1], sell[i - 1] - prices[i] - fee); // keep the same as day i-1, or buy from sell status at day i-1
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]); // keep the same as day i-1, or sell from buy status at day i-1
        }
        return sell[days - 1];
    }
    public int maxProfit(int[] prices, int fee) {
        if (prices.length <= 1) return 0;
        int days = prices.length, buy[] = new int[days], sell[] = new int[days];
        buy[0]=-prices[0];
        for (int i = 1; i<days; i++) {
            buy[i] = Math.max(buy[i - 1], sell[i - 1] - prices[i]); // keep the same as day i-1, or buy from sell status at day i-1
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i] - fee); // keep the same as day i-1, or sell from buy status at day i-1
        }
        return sell[days - 1];
    }

}
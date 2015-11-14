package array;

import java.util.Arrays;


public class BuyStock {
	/*
	 * Say you have an array for which the ith element is the price of a given stock on day i.
		If you were only permitted to complete at most one transaction (ie, buy one and sell 
		one share of the stock), design an algorithm to find the maximum profit.
	 */
	
    public int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0 ;
        }      
        int max = 0; 
        int lowest = prices[0];
        for (int i = 0; i< prices.length;i++){
        	if (prices[i] <= lowest){
        		lowest = prices[i];
        	}else{
        		max = Math.max(max, prices[i] - lowest);
        	}
        }
        return max;
        
    }
    
    /*
     * Say you have an array for which the ith element is the price of a given stock on day i.
		Design an algorithm to find the maximum profit. You may complete as many transactions
		as you like (ie, buy one and sell one share of the stock multiple times). However, you 
		may not engage in multiple transactions at the same time (ie, you must sell the stock before
		 you buy again).
		 ==============
		 For Buy and Sell 1, we were limited to 1 transaction, so we had to find the largest sum of contiguous ints in an array of price differences.

Q: Why does finding the most profitable transaction boils down to finding the largest sum of contiguous ints in the array of price differences?

A: Define D[i] = Prices[i] - Prices[i-1] (difference between 2 consecutive prices)

D[i] is essentially a "delta" trade.

A transaction is defined as buying at Prices[X] and selling at Prices[Y], 
The problem is to find max(Prices[Y] - Prices[X]) which is equivalent to finding the largest sum of contiguous D's.

To illustrate, if D[Y+1] is positive, it means Prices[Y+1] > Prices[Y], which implies I should sell at Prices[Y+1] instead of Prices[Y]. Basically it means I just add D[Y+1] to D[Y] + ... + D[X+1].

Note that there could be a negative or zero D in the best running sequence. It doesn't matter so long the sum of the sequence is the largest.

Now we are allowed unlimited transactions. So if there is a negative D, we could just break the sequence into 2, that is, into 2 transactions so as to avoid the negative element.

This boils the whole problem down to adding up all positive sums of contiguous ints in D, which simplifies to just adding up all the positive ints.
     */

}

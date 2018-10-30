package math;
/*
 *  Given a positive integer n, break it into the sum of at least two positive integers and maximize the product of those integers. Return the maximum product you can get.

For example, given n = 2, return 1 (2 = 1 + 1); given n = 10, return 36 (10 = 3 + 3 + 4).

Note: You may assume that n is not less than 2 and not larger than 58. 
 */
public class IntegerBreak {
	public int integerBreak(int n){
		
//	       int[] dp = new int[n + 1];
//	       dp[1] = 1;
//	       for(int i = 2; i <= n; i ++) {
//	           for(int j = 1; j < i; j ++) {
//	               dp[i] = Math.max(dp[i], (Math.max(j,dp[j])) * (Math.max(i - j, dp[i - j])));
//	           }
//	       }
//	       return dp[n];
	    int[] dp = new int[n + 1];
	    dp[1] = 1;
	    for(int i = 2; i <= n; i ++) {
	        for(int j = 1; 2*j <= i; j ++) {
	            dp[i] = Math.max(dp[i], (Math.max(j,dp[j])) * (Math.max(i - j, dp[i - j])));
	        }
	    }
	    return dp[n];
	}
	/*
	 * Based on the concept that if a number is >4 we can break it into number of 3's and the product of 3's are always greater. 3333 > 44*4 for n=12
	 */
	public int integerBreak1(int n){
        if(n==2)return 1;
        if(n==3) return 2;
        int result=1;
        while(n>=3 && n!=4){
            result*=3;
            n-=3;
        }
        if(n==4 || n==2){
            result*=n;
        }
        return result;
	}
	
	/*
	 * 

I saw many solutions were referring to factors of 2 and 3. But why these two magic numbers? Why other factors do not work?
Let's study the math behind it.

For convenience, say n is sufficiently large and can be broken into any smaller real positive numbers. We now try to calculate which real number generates the largest product.
Assume we break n into (n / x) x's, then the product will be xn/x, and we want to maximize it.

Taking its derivative gives us n * xn/x-2 * (1 - ln(x)).
The derivative is positive when 0 < x < e, and equal to 0 when x = e, then becomes negative when x > e,
which indicates that the product increases as x increases, then reaches its maximum when x = e, then starts dropping.

This reveals the fact that if n is sufficiently large and we are allowed to break n into real numbers,
the best idea is to break it into nearly all e's.
On the other hand, if n is sufficiently large and we can only break n into integers, we should choose integers that are closer to e.
The only potential candidates are 2 and 3 since 2 < e < 3, but we will generally prefer 3 to 2. Why?

Of course, one can prove it based on the formula above, but there is a more natural way shown as follows.

6 = 2 + 2 + 2 = 3 + 3. But 2 * 2 * 2 < 3 * 3.
Therefore, if there are three 2's in the decomposition, we can replace them by two 3's to gain a larger product.

All the analysis above assumes n is significantly large. When n is small (say n <= 10), it may contain flaws.
For instance, when n = 4, we have 2 * 2 > 3 * 1.
To fix it, we keep breaking n into 3's until n gets smaller than 10, then solve the problem by brute-force.

	 */
	
	
	/*
	 * Given a number n lets say we have a possible product P = p1 * p2 * ... pk. Then we notice what would happen if we could break pi up into two more terms lets say one of the terms is 2 we would get the terms pi-2 and 2 so if 2(pi-2) > pi we would get a bigger product and this happens if pi > 4. since there is one other possible number less then 4 that is not 2 aka 3. Likewise for 3 if we instead breakup the one of the terms into pi-3 and 3 we would get a bigger product if 3*(pi-3) > pi which happens if pi > 4.5.

Hence we see that all of the terms in the product must be 2's and 3's. So we now just need to write n = a3 + b2 such that P = (3^a) * (2^b) is maximized. Hence we should favor more 3's then 2's in the product then 2's if possible.

So if n = a*3 then the answer will just be 3^a.

if n = a3 + 2 then the answer will be 2(3^a).

and if n = a3 + 22 then the answer will be 2 * 2 * 3^a

The above three cover all cases that n can be written as and the Math.pow() function takes O(log n) time to preform hence that is the running time.
	 */
    public int integerBreak3(int n) {
        if(n == 2)
            return 1;
        else if(n == 3)
            return 2;
        else if(n%3 == 0)
            return (int)Math.pow(3, n/3);
        else if(n%3 == 1)
            return 2 * 2 * (int) Math.pow(3, (n - 4) / 3);
        else 
            return 2 * (int) Math.pow(3, n/3);
    }

}

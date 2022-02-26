package dp;/*
 Given a positive integer n, break it into the sum of at least two positive integers and maximize the product of those integers. Return the maximum product you can get.

For example, given n = 2, return 1 (2 = 1 + 1); given n = 10, return 36 (10 = 3 + 3 + 4).

Note: You may assume that n is not less than 2 and not larger than 58. 
*/

public class IntegerBreak {
    public int integerBreakDP(int n) {
        // dp[i] is maximum product for i
        int[] dp = new int[n+1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            //let's say i = 8, we are trying to fill dp[8]:if 8 can only be broken into 2 parts, the answer could be among 1 * 7, 2 * 6, 3 * 5, 4 * 4... but these numbers can be further broken. so we have to compare 1 with dp[1], 7 with dp[7], 2 with dp[2], 6 with dp[6]...etc
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], (Math.max(j, dp[j]) * Math.max(i-j, dp[i-j])));
            }
        }
        return dp[n];
    }

    
// The first thing we should consider is : What is the max product if we break a number N into two factors?

// I use a function to express this product: f=x(N-x)

// When x=N/2, we get the maximum of this function.

// However, factors should be integers. Thus the maximum is (N/2)*(N/2) when N is even or (N-1)/2 *(N+1)/2 when N is odd.

// When the maximum of f is larger than N, we should do the break.

// (N/2)*(N/2)>=N, then N>=4

// (N-1)/2 *(N+1)/2>=N, then N>=5

// These two expressions mean that factors should be less than 4, otherwise we can do the break and 
// get a better product. The factors in last result should be 1, 2 or 3. Obviously, 1 should be abandoned.
//  Thus, the factors of the perfect product should be 2 or 3.

// The reason why we should use 3 as many as possible is

// For 6, 3 * 3>2 * 2 * 2. Thus, the optimal product should contain no more than three 2.

    public int integerBreakMath(int n) {
        if (n == 2) return 1;
        if (n == 3) return 2;
        int product = 1;
        while (n > 4) {
            product *= 3;
            n -= 3;
        }
        product *= n;
        return product;
    }

    public int integerBreak(int n) {
        if (n == 2 || n == 3) return n - 1;
        if (n == 4) return 4;
        n -= 5;
        return (int)Math.pow(3, (n / 3 + 1)) * (n % 3 + 2);
    }
    public int integerBreak1(int n) {
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

//     I saw many solutions were referring to factors of 2 and 3. But why these two magic numbers? Why other factors do not work?
// Let's study the math behind it.

// For convenience, say n is sufficiently large and can be broken into any smaller real positive 
// numbers. We now try to calculate which real number generates the largest product.
// Assume we break n into (n / x) x's, then the product will be xn/x, and we want to maximize it.

// Taking its derivative gives us n * xn/x-2 * (1 - ln(x)).
// The derivative is positive when 0 < x < e, and equal to 0 when x = e, then becomes negative when x > e,
// which indicates that the product increases as x increases, then reaches its maximum when x = e, then starts dropping.

// This reveals the fact that if n is sufficiently large and we are allowed to break n into real numbers,
// the best idea is to break it into nearly all e's.
// On the other hand, if n is sufficiently large and we can only break n into integers, we should choose integers that are closer to e.
// The only potential candidates are 2 and 3 since 2 < e < 3, but we will generally prefer 3 to 2. Why?

// Of course, one can prove it based on the formula above, but there is a more natural way shown as follows.

// 6 = 2 + 2 + 2 = 3 + 3. But 2 * 2 * 2 < 3 * 3.
// Therefore, if there are three 2's in the decomposition, we can replace them by two 3's to gain a larger product.

// All the analysis above assumes n is significantly large. When n is small (say n <= 10), it may contain flaws.
// For instance, when n = 4, we have 2 * 2 > 3 * 1.
// To fix it, we keep breaking n into 3's until n gets smaller than 10, then solve the problem by brute-force.
}
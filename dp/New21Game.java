/*
Alice plays the following game, loosely based on the card game "21".

Alice starts with 0 points, and draws numbers while she has less than K points.  
During each draw, she gains an integer number of points randomly from the range 
[1, W], where W is an integer.  Each draw is independent and the outcomes have
 equal probabilities.

Alice stops drawing numbers when she gets K or more points.  What is the probability 
that she has N or less points?

Example 1:

Input: N = 10, K = 1, W = 10
Output: 1.00000
Explanation:  Alice gets a single card, then stops.

Example 2:

Input: N = 6, K = 1, W = 10
Output: 0.60000
Explanation:  Alice gets a single card, then stops.
In 6 out of W = 10 possibilities, she is at or below N = 6 points.

Example 3:

Input: N = 21, K = 17, W = 10
Output: 0.73278

#psuedocode
dp[k] = 1.0 when K <= k <= N, else 0.0
# dp[x] = the answer when Alice has x points
for k from K-1 to 0:
    dp[k] = (dp[k+1] + ... + dp[k+W]) / W
return dp[0]


Approach #1: Dynamic Programming [Accepted]

Intuition

It is clear that the probability that Alice wins the game is only related to how many points x she starts the next draw with, so we can try to formulate an answer in terms of x.

Algorithm

Let f(x) be the answer when we already have x points. When she has between K and N points, then she stops drawing and wins. If she has more than N points, then she loses.

The key recursion is f(x)=(1W)∗(f(x+1)+f(x+2)+...+f(x+W))f(x) = (\frac{1}{W}) * (f(x+1) + f(x+2) + ... + f(x+W))f(x)=(W1​)∗(f(x+1)+f(x+2)+...+f(x+W)) This is because there is a probability of 1W\frac{1}{W}W1​ to draw each card from 111 to WWW.

With this recursion, we could do a naive dynamic programming solution as follows:

#psuedocode
dp[k] = 1.0 when K <= k <= N, else 0.0
# dp[x] = the answer when Alice has x points
for k from K-1 to 0:
    dp[k] = (dp[k+1] + ... + dp[k+W]) / W
return dp[0]

This leads to a solution with O(K∗W+(N−K))O(K*W + (N-K))O(K∗W+(N−K)) time complexity, which isn't efficient enough. Every calculation of dp[k] does O(W)O(W)O(W) work, but the work is quite similar.

Actually, the difference is f(x)−f(x−1)=1W(f(x+W)−f(x))f(x) - f(x-1) = \frac{1}{W} \big( f(x+W) - f(x) \big)f(x)−f(x−1)=W1​(f(x+W)−f(x)). This allows us to calculate subsequent f(k)f(k)f(k)'s in O(1)O(1)O(1) time, by maintaining the numerator S=f(x+1)+f(x+2)+⋯+f(x+W)S = f(x+1) + f(x+2) + \cdots + f(x+W)S=f(x+1)+f(x+2)+⋯+f(x+W).

As we calculate each dp[k] = S / W, we maintain the correct value of this numerator S⇒S+f(k)−f(k+W)S \Rightarrow S + f(k) - f(k+W)S⇒S+f(k)−f(k+W).

Complexity Analysis

    Time and Space Complexity: O(N+W)O(N + W)O(N+W).
    Note that we can reduce the time complexity to O(max⁡(K,W))O(\max(K, W))O(max(K,W)) and the space complexity to O(W)O(W)O(W) by only keeping track of the last WWW values of dp, but it isn't required.


     * dp[i] represents the total probability to get points i
     * dp[i] = dp[1] * 1/W + dp[2] * 1/W + dp[3] * 1/W + ... dp[i-2] * 1/W + dp[i-1] * 1/W
     * So dp[i] = (dp[1] + dp[2] + ... + dp[i - 1]) / W = Wsum / W
     * Conditional probability: keep a window with size K (assume K = 10), the probability of getting point i is the sum
     * of probability from point i - 10 to i, point i - 9 to i, ... , i -1 to i. Since every card has equal probability,
     * the probability to get any one of cards is 1/10. So the total probability of dp[i] is sum of all conditional
     * probability.
     * Once i is over than or equal to K, we can accumulate probability to final result
     *
     * （翻译）条件概率：dp[i]表示可以到达i分数的概率总和。假设我们的K为10的话，抽到每张牌的概率都是1/10。那么我们只需要从dp[i-10]
     * 开始加就可以了，所以就是维持一个size为K的window。比如12这个数，我们可以由抽到2的概率（dp[2]）乘以1/10或者抽到3的概率（dp[3]）
     * 乘以1/10得来...一直到dp[11] * 1/10，那么把他们相加就是可以到达point i的总概率了（就是dp[i]的值）。相当于是最基本的条件概率。
     * 那么总概率就是 dp[12] = dp[2] * 1/10 + dp[3] * 1/10 + dp[4] * 1/10 + ... + dp[11] * 1/10.
     * 再详细剖析：因为从2到11这10个数都有可能通过只抽一张牌就到达12分。以此类推，只要是point没到K，之前的数都有可能到达当前的数，size超过K的时候，
     * 我们就维持一个size为K的window，再通过条件概率的公式累加和就可以了。
     *
     * 现在我们已经有公式 dp[i] = dp[1] * 1/W + dp[2] * 1/W + dp[3] * 1/W + ... dp[i-2] * 1/W + dp[i-1] * 1/W，
     * 通过这个公式，我们可以换算成 (dp[1] + dp[2] + ... + dp[i - 1]) / W，这里的dp[1] + dp[2] + ... + dp[i - 1] 就是Wsum，所以dp[i] = Wsum / W。
     * 换句话来说Wsum就是通过一次抽排就能到当前分数的概率之和。当然，我们这个example公式的Wsum是当i没有超过W时候的值，
     * 如果i超过了W，那就不是从dp[1]开始加了，而是从dp[i - W]开始加，相当于向右挪动window， 最多只能是从point i - W
     * 开始才能通过只抽一张牌就到达point i
*/

public class New21Game {
    // https:///problems/new-21-game/discuss/132334/One-Pass-DP
    /*
    N: final points
    K: maximum points sum to stop game
    W: maximum points each time can get
    */
    public double new21Game(int N, int K, int W) {
        // if K is 0, possibility is 1
        // W is maximum points can get each time, K is sum of points. If total K + W less than N
        // for sure possibility is 1
        if (K == 0 || K + W <= N) return 1;
        // dp[i] possibility to get i points;
        double dp[] = new double[N+1];
        // initial sum of possibilty
        double wsum = 1;
        double result = 0;
        dp[0] = 1;
        // each loop is possiblity to only draw one card to get i points
        for (int i = 1; i <= N; i++){
            dp[i] = wsum/w;
            if (i < K) {
                wsum += dp[i];
            } else {
                result += dp[i];
            }
            // when i is more than W, need to remove possibility of i-w. Because can't get i point
            if (i - W >= 0) {
                wsum -= dp[i-W];
            }
        }
        return result;

    }
    public double new21Game1(int N, int K, int W) {
        double[] dp = new double[N + W + 1];
        // dp[x] = the answer when Alice has x points
        for (int k = K; k <= N; ++k)
            dp[k] = 1.0;

        double S = Math.min(N - K + 1, W);
        // S = dp[k+1] + dp[k+2] + ... + dp[k+W]
        for (int k = K - 1; k >= 0; --k) {
            dp[k] = S / W;
            S += dp[k] - dp[k + W];
        }
        return dp[0];
    }
    
}
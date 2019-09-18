/* https://leetcode.com/problems/last-stone-weight-ii/discuss/294888/JavaC%2B%2BPython-Easy-Knapsacks-DP
*/
public class StoneWeight {
    /*
    S1+S2 = S (sum of stones)
    S1-S2 = difference 
    S - difference = 2*S2
    difference = S - 2*S2
    to find out minimum of difference, S2 need to maximum
    */
    public int lastStoneWeightII(int[] stones) {
        // dp[i]: whether sume i is possible
        boolean dp[] = new boolean[1500];
        dp[0] = true;
        int sum = 0;
        for (int stone: stones) {
            sum += stone;
            for (int i = 1500; i >=stone; i--) {
                dp[i] = dp[i-stone] | dp[i];
            }
        }
        for (int i = sum/2; i >= 0; i--) {
            if (dp[i]) {
                return sum - 2*i;
            }
        }
        return 0;
    }
    public int lastStoneWeightI(int[] stones) {
    //    boolean[] dp = new dp[3001];
    //    int sum = 0;
    //    for (int stone: stones) {
    //        sum += stone;
    //        for (int i = sum; i >= stone; i--) {
    //            dp[i] = dp[i-stone] | dp[i+stone];
    //        }
    //    }
    //    for ()
    }
}
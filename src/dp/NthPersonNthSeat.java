package dp;

public class NthPersonNthSeat {
    public double nthPersonNthSeat(int n) {
        // dp[i] is (i+1)th person's possibility
        double[] dp = new double[n];
        dp[0] = 1.0;
        for (int i = 1; i < n; i++) {
            dp[i] = dp[i-1] * (i+1-2)/(i+1) + 1/(i+1);
        }
        return dp[n-1];
    }
}

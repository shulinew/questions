package dp;

import java.util.HashMap;
import java.util.Map;

public class NumRollsToTarget {
    public int numRollsToTarget(int d, int f, int target) {
        if (d == 0 && target == 0) return 1;
        if (d == 0 || target == 0) return 0;
        Map<String, Integer> memo = new HashMap<String, Integer>();
        int MOD = 1000000000 + 7;
        return dfs(d, f, target, memo, MOD);
    }
    private int dfs(int d, int f, int target, Map<String, Integer> memo, int MOD) {
        if (d == 0 && target == 0) return 1;
        if (d == 0 || target == 0) return 0;
        String key = d + " " + target;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        int result = 0;
        for (int i = 1; i <= f; i++) {
            if (target >= i) {
                result = (result + dfs(d-1, f, target - i, memo, MOD))%MOD;
            } else {
                break;
            }
        }
        memo.put(key, result);
        return result;
    }
    public int numRollsToTargetDP(int d, int f, int target) {
        int MOD = (int)Math.pow(10, 9) + 7;
        long[][] dp = new long[d+1][target+1];
        dp[0][0] = 1;
        for (int i = 1; i <= d; i++) {
            for(int j = 0; j <= target; j++) {
                for(int k = 1; k <= f && j >= k; k++) {
                    if (j >= k) {
                        dp[i][j] = (dp[i][j] + dp[i-1][j-k]) % MOD;
                    } else {
                        break;
                    }
                }
            }
        }
        return (int)dp[d][target];
    }
}

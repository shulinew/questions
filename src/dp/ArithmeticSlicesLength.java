package dp;

import java.util.HashMap;
import java.util.Map;

public class ArithmeticSlicesLength {
    public int longestArithSeqLength(int[] A) {
        int result = 2;
        int length = A.length;
        Map<Integer, Integer>[] dp = new HashMap[length];
        for (int i = 0; i < A.length; i++){
            dp[i] = new HashMap<>();
            for (int j = 0; j < i; j++) {
                int difference = A[j] - A[i];
                dp[i].put(difference, dp[j].getOrDefault(difference, 1) + 1);
                result = Math.max(result, dp[i].get(difference));
            }
        }
        return result;
    }
    public int longestSubsequence(int[] arr, int difference) {
        int result = 1;
        int length = arr.length;
        Map<Integer, Integer>[] dp = new HashMap[length];
        for (int i = 0; i < arr.length; i++){
            dp[i] = new HashMap<>();
            for (int j = 0; j < i; j++) {
                int diff = arr[i] - arr[j];
                dp[i].put(diff, dp[j].getOrDefault(diff, 1) + 1);
                if (diff == difference)
                    result = Math.max(result, dp[i].get(difference));
            }
        }
        return result;
    }
    public int longestSubsequence1(int[] arr, int difference) {
        int result = 0;
        int length = arr.length;
        Map<Integer, Integer> dp = new HashMap();
        for (int i = 0; i < arr.length; i++){
            dp.put(arr[i], dp.getOrDefault(arr[i] - difference, 0) + 1);
            result = Math.max(result, dp.get(arr[i]));
        }
        return result;
    }
}

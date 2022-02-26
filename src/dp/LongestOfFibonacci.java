package dp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestOfFibonacci {
    public int lenLongestFibSubseq(int[] A) {
        int len = A.length;
        Set<Integer> set = new HashSet<Integer>();
        for (int i: A) {
            set.add(i);
        }
        int count = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                int x = A[j];
                int y = A[i] + A[j];
                int length = 2;
                while (set.contains(y)) {
                    int temp = y;
                    y += x;
                    x = temp;
                    count = Math.max(count, ++length);
                }
            }
        }
        return count >= 3 ? count : 0;
    }
    public int lenLongestFibSubseqDP(int[] A) {
        int len = A.length;
        Map<Integer, Integer> index = new HashMap<Integer, Integer>();
        for (int i = 0; i < len; i++) {
            index.put(A[i], i);
        }
        Map<Integer, Integer> longest = new HashMap<Integer, Integer>();
        int count = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < i; j++) {
                int k = index.getOrDefault(A[i] - A[j], -1);
                if (k >=0 && k < j) {
                    int cand = longest.getOrDefault(k * len + j, 2) + 1;
                    longest.put(j * len + i, cand);
                    count = Math.max(count, cand);
                }
            }
        }
        return count;
    }
    public int lenLongestFibSubseqDP1(int[] A) {
        int count = 0;
        int[][] dp = new int[A.length][A.length];
        Map<Integer, Integer> index = new HashMap<Integer, Integer>();
        for (int i = 0; i < A.length; i++) {
            index.put(A[i], i);
            for (int j = 0; j < i; j++) {
                int k = index.getOrDefault(A[i] - A[j], -1);
                // A[k] + A[j] = A[i]
                dp[j][i] = (A[i] - A[j] < A[j] && k >= 0) ? dp[k][j] + 1 : 2;
                count = Math.max(count, dp[j][i]);
            }
        }
        return count > 2 ? count : 0;
    }
    public int lenLongestFibSubseqDP2(int[] A) {
        int len = A.length;
        int count = 0;
        int[][] dp = new int[len][len];
        for (int i = 1; i < len; i++) {
            int left = 0, right = i - 1;
            while (left < right) {
                int sum = A[left] + A[right];
                if (sum > A[i]) {
                    right--;
                } else if (sum < A[i]) {
                    left++;
                } else {
                    dp[right][i] = dp[left][right] + 1;
                    count = Math.max(count, dp[right][i]);
                    right--;
                    left++;
                }
            }
        }
        return count == 0 ? 0 : count + 2;
    }
}

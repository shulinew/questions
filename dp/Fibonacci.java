/*
A sequence X_1, X_2, ..., X_n is fibonacci-like if:

    n >= 3
    X_i + X_{i+1} = X_{i+2} for all i + 2 <= n

Given a strictly increasing array A of positive integers forming a sequence, find the length of the
 longest fibonacci-like subsequence of A.  If one does not exist, return 0.

(Recall that a subsequence is derived from another sequence A by deleting any number of elements 
(including none) from A, without changing the order of the remaining elements.  For example, 
[3, 5, 8] is a subsequence of [3, 4, 5, 6, 7, 8].)
*/

public class Fibonacci {
    public int lenLongestFibonacci (int[] A) {
        int len = A.length;
        Set<Integer> set = new HashSet<Integer>();
        for (Integer i: A) {
            set.add(i);
        }
        int result = 0;
        for (int i = 0; i < len; i++){
            for (int j = i+1; j < len; j++) {
                int maxLeng = 2;
                int xi1 = A[i] + A[j];
                int xi = A[j];
                while(set.contains(xi1) {
                    int temp = xi1;
                    xi1 += xi;
                    xi = temp;
                    result = Math.max(length++, result);
                }
            }
        }
        return result >=3 ? result: 0;
    }

    public int lenLongestFibonacciDP(int[] nums) {
        int len = nums.length;
        Map<Integer, Integer> index = new HashMap<Integer, Integer>();
        for (int i = 0; i < len; i++) {
            index.put(nums[i], i);
        }
        int result = 0;
        Map<Integer, Integer> longest = new HashMapI<Integer, Integer>();
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < i; j++) {
               int k = index.getOrDefault(nums[i]-nums[j], -1);
               if (k > 0 && k < j) {
                  int candidate = longest.getOrDefault(k*len+j, 2)+1;
                  longest.put(j*len+i, candidate);
                  result = Math.max(result, candidate);
               }
            }
        }
        return result >= 3 ? result : 0;
    }
    public int lenLongestFibSubseq(int[] A) {
        int res = 0;
        int[][] dp = new int[A.length][A.length];
        Map<Integer, Integer> index = new HashMap<>();
        for (int j = 0; j < A.length; j++) {
            index.put(A[j], j);
            for (int i = 0; i < j; i++) {
                int k = index.getOrDefault(A[j] - A[i], -1);
                dp[i][j] = (A[j] - A[i] < A[i] && k >= 0) ? dp[k][i] + 1 : 2;
                res = Math.max(res, dp[i][j]);
            }
        }
        return res > 2 ? res : 0;
    }

}
package dp;

public class MaxSubArraySum {
    public int maximumSum(int[] arr) {
        int len = arr.length;
        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j + i <= len; j++) {
                max = Math.max(max, helper(arr, j, i));
            }
        }
        return max;
    }
    private int helper(int[] arr, int start, int len){
        int sum = 0, max = Integer.MIN_VALUE;
        if (len == 1) {
            return arr[start];
        }
        for (int i = start; i <= start + len - 1; i++) {
            sum += arr[i];
        }
        for (int i = start; i <= start + len -1; i++) {
            max = Math.max(max, sum - arr[i]);
        }
        return max;
    }

    public int maximumSumMemo(int[] arr) {
        int len = arr.length;
        int max = Integer.MIN_VALUE;
        Integer[][] memo = new Integer[len+1][len];
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j + i <= len; j++) {
                max = Math.max(max, helper(arr, j, i, memo));
            }
        }
        return max;
    }
    private int helper(int[] arr, int start, int len, Integer[][] memo){
        int sum = 0, max = Integer.MIN_VALUE;
        if (memo[len][start] != null) {
            return memo[len][start];
        }
        if (len == 1) {
            memo[len][start] = arr[start];
            return arr[start];
        }
        for (int i = start; i <= start + len - 1; i++) {
            sum += arr[i];
        }
        for (int i = start; i <= start + len -1; i++) {
            max = Math.max(max, sum - arr[i]);
        }
        memo[len][start] = max;
        return max;
    }
    public int maxmumSumDP(int[] arr) {
        if (arr.length == 1) return arr[0];
        int len = arr.length;
        //maxmum sum ends at i
        int[] dp1 = new int[len];
        dp1[0] = arr[0];
        //maxmum sum starts at i;
        int[] dp2 = new int[len];
        dp2[len-1] = arr[len-1];
        int max = arr[0];
        for (int i = 1; i < len; i++) {
            dp1[i] = dp1[i - 1] > 0 ? dp1[i - 1] + arr[i] : arr[i];
            max = Math.max(max, dp1[i]);
        }
        for(int i = len - 2; i >= 0; i--) {
            dp2[i] = dp2[i + 1] > 0 ? dp2[i + 1] + arr[i] : arr[i];
        }
        for (int i = 1; i < len -1; i++) {
            if (arr[i] < 0) {
                max = Math.max(max, dp1[i-1] + dp2[i + 1]);
            }
        }
        return max;
    }
    public int maxmumSumDPLessSpace(int[] arr) {
        int len = arr.length;
        int maxNoDelete = arr[0];
        int maxWithDelete = arr[0];
        int maxSoFar = arr[0];
        for (int i = 1; i < len; i++) {
            maxWithDelete = Math.max(maxNoDelete, maxWithDelete + arr[i]);
            maxNoDelete = Math.max(maxNoDelete + arr[i], arr[i]);
            maxSoFar = Math.max(maxWithDelete, Math.max(maxSoFar, maxNoDelete));
        }
        return maxSoFar;
    }
}

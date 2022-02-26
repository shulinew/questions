package src.pattern.slidingWindow;

class MaxSumSubArrayOfSizeK {
    public static int findMaxSumSubArray(int K, int[] arr) {
        int max = 0;
        for (int i = 0; i < arr.length - K + 1; i++) {
            int currentMax = 0;
            int sum = 0;
            for (int j = i; j < i + K; j++) {
               sum += arr[j] ;
            }
            max = Math.max(currentMax, max);
        }
        return max;
    }
    public static int findMaxSumSubArray1(int K, int[] arr) {
        int max = 0;
        int windowStart = 0;
        int sum = 0;
        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            sum += arr[windowEnd];
            if (windowEnd >= K - 1) {
                max = Math.max(max, sum);
                sum -= arr[windowStart];
                windowStart++;
            }
        }
        return max;
    }
    public static void main(String[] args) {
        System.out.println("Maximum sum of subarray of size K: "
                + MaxSumSubArrayOfSizeK.findMaxSumSubArray1(3, new int[]{2,3,4,1,5}));
    }
}
package pattern.slidingWindow;

public class MinSizeSubarraySum {
    public static int findMinSubarraySum(int S, int[] arr) {
        int minLen = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int len = 0;
            int sum = 0;
            for(int j = i;  j < arr.length; j++) {
                sum += arr[j];
                if (sum >= S) {
                    len = j - i + 1;
                    minLen = Math.min(minLen, len);
                    break;
                }
            }
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    public static int findMinSubarraySum1(int S, int[] arr) {
        int minLen = Integer.MAX_VALUE;
        int sum = 0;
        int windowStart = 0;
        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            sum += arr[windowEnd];
            while (sum >= S) {
                minLen = Math.min(minLen, windowEnd - windowStart + 1);
                sum -= arr[windowStart];
                windowStart++;
            }
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
    public static void main(String[] args) {
        int[] arr1 = new int[]{2,1,5,2,3,2};
        System.out.println("Sliding window: " + MinSizeSubarraySum.findMinSubarraySum1(7, arr1));
        System.out.println("Brutal force: " + MinSizeSubarraySum.findMinSubarraySum(7, arr1));

        int[] arr2 = new int[]{2,1,5,2,8};
        System.out.println("Sliding window: " + MinSizeSubarraySum.findMinSubarraySum1(7, arr2));
        System.out.println("Brutal force: " + MinSizeSubarraySum.findMinSubarraySum(7, arr2));

        int[] arr3 = new int[]{3,4,1,1,6};
        System.out.println("Sliding window: " + MinSizeSubarraySum.findMinSubarraySum1(8, arr3));
        System.out.println("Brutal force: " + MinSizeSubarraySum.findMinSubarraySum(8, arr3));
    }
}

package pattern.slidingWindow;

public class ReplacingOnes {
    public static int findLength(int[] arr, int k) {
        int maxLeng = 0;
        int windowStart = 0;
        int maxReplceCount = 0;
        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            if (arr[windowEnd] == 0) {
                maxReplceCount++;
            }
            while (maxReplceCount > k) {
                if (arr[windowStart] == 0)
                    maxReplceCount--;
                windowStart++;
            }
            maxLeng = Math.max(maxLeng, windowEnd - windowStart + 1);
        }
        return maxLeng;
    }

    public static int findLength1(int[] arr, int k) {
        int maxLeng = 0;
        int windowStart = 0;
        int maxOnes = 0;
        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            if (arr[windowEnd] == 1) {
                maxOnes++;
            }
            if (windowEnd - windowStart + 1 - maxOnes > k) {
                if (arr[windowStart] == 1)
                    maxOnes--;
                windowStart++;
            }
            maxLeng = Math.max(maxLeng, windowEnd - windowStart + 1);
        }
        return maxLeng;
    }
}

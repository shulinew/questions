package dp;

public class KConcatenationMaxSum {
    public int kConcatenationMaxSum(int[] arr, int k) {
        long maxSoFar, max, sum;
        int mod = (int)Math.pow(10, 9) + 7;
        maxSoFar = arr[0];
        sum = arr[0];
        max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sum += arr[i];
            maxSoFar = maxSoFar > 0 ? (maxSoFar + arr[i]) % mod : arr[i];
            max = Math.max(max, maxSoFar);
        }
        // find suffix sum
        maxSoFar =0;
        long suffixSum = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            maxSoFar = (maxSoFar + arr[i]) % mod;
            suffixSum = Math.max(maxSoFar, suffixSum);
        }
        // find prefix sum
        maxSoFar = 0;
        long prefixMax = Integer.MIN_VALUE;
        for (int i = arr.length - 1; i >= 0; i--) {
            maxSoFar = (maxSoFar + arr[i]) % mod;
            prefixMax = Math.max(prefixMax, maxSoFar);
        }
        if (sum <= 0) {
            return (int)Math.max(max % mod, prefixMax % mod + suffixSum % mod );
        } else {
            return (int)Math.max(max % mod, ((sum * (k - 2)) % mod + prefixMax % mod + suffixSum % mod) % mod);
        }
    }

    public int kConcatenationMaxSumFast(int[] arr, int k) {
        int mod = 1000000007;
        int n = arr.length;
        int maxSoFar = arr[0];
        int maxEndingHere = arr[0];
        for(int i = 1; i < n; i++){
            maxEndingHere = Math.max(maxEndingHere + arr[i], arr[i]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }

        if(k < 2) return maxSoFar;

        int leftSum = arr[0];
        int rightSum = arr[n - 1];
        int lMax = Math.max(0, arr[0]);
        int rMax = Math.max(0, arr[n-1]);

        for(int i = 1; i < n; i++){
            leftSum += arr[i];
            lMax = Math.max(lMax, leftSum);
        }

        for(int i = n - 2; i >= 0; i--){
            rightSum += arr[i];
            rMax = Math.max(rMax, rightSum);
        }

        int headTailMax = lMax + rMax;

        if(leftSum < 0) return Math.max(maxSoFar, headTailMax);

        else return Math.max(maxSoFar, (int)(headTailMax + ((k - 2) * (long)leftSum) % modulo));
    }
}

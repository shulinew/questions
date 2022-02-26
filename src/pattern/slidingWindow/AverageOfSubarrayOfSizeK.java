package src.pattern.slidingWindow;

class AverageOfSubarrayOfSizeK {
    public double[] finddAverages(int K, int[] arr) {
        double[] results = new double[arr.length - K + 1]; //len-K+1 how many slinding windows
        for (int i = 0; i <= arr.length - K; i++) {
            double sum = 0;
            for (int j = 0; j <i + K; j++) {
                sum += arr[j];
            }
            results[i] = sum / K;
        }
        return results;
    }

    public double[] findAverages2(int K, int[] arr) {
        double[] result = new double[arr.length - K + 1];
        double sum = 0;
        int windowStart = 0;
        for (int windowEnd = 0;  windowEnd < arr.length; windowEnd++) {
            sum += arr[windowEnd]; //Add next element
            // if winodwEnd less than K, no need to slide
            if (windowEnd >= K - 1) {
                result[windowStart] = sum / K;
                sum -= arr[windowStart];
                windowStart++;
            }
        }
        return result;
    }
}
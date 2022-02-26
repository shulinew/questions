package dp;

public class LongestTurbulenceSubarray {
//    [9,4,2,10,7,8,8,1,9]
    public int maxTurbulenceSize(int[] A) {
        int len = A.length;
        int result = 1;
        int anchor = 0;

        for (int i = 1; i < len; i++) {
            int c = Integer.compare(A[i-1], A[i]);
            if (c == 0) {
                anchor = i;
            } else if ( i == len -1 || c * Integer.compare(A[i], A[i+1]) != -1) {
                result = Math.max(i - anchor + 1, result);
                anchor = i;
            }
        }
        return result;
    }
}

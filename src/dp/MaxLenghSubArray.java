/*
*/

public class MaxLengthSubArray {
    public int findLength(int[] A, int[] B) {
        int len = 1;
        int max = 0;
        for (int i = 0; i < A.length; i++) {
            max = Math.max(subArrayLength(i, A, B), max);
        }
        return max;
    }
    private int subArrayLength(int start, int[] A, int[] B){
        int maxLength = 0;
        int j = start;
        for (int i = 0; i < B.length; i++) {
            int k = i;
            int tempMax = 0;
            while (A[k++] == B[j++] && k < B.length) {
                tempMax++;
            }
            maxLength = Math.max(tempMax, maxLength);
            j = 0;
        }
        return maxLength;
    }

// The space is O(log(min(m,n)) * M)
    public boolean check(int length, int[] A, int[] B) {
        Set<String> seen = new HashSet();
        for (int i = 0; i + length <= A.length; ++i) {
            seen.add(Arrays.toString(Arrays.copyOfRange(A, i, i+length)));
        }
        for (int j = 0; j + length <= B.length; ++j) {
            if (seen.contains(Arrays.toString(Arrays.copyOfRange(B, j, j+length)))) {
                return true;
            }
        }
        return false;
    }

    public int findLength(int[] A, int[] B) {
        int lo = 0, hi = Math.min(A.length, B.length) + 1;
        while (lo < hi) {
            int mi = (lo + hi) / 2;
            if (check(mi, A, B)) lo = mi + 1;
            else hi = mi;
        }
        return lo - 1;
    }
    public int findlengthDP(int[] A, int[] B){
        int[][] dp = new int[A.length+1][B.length+1];
        int result = 0;
        // for (int i = A.length-1; i>=0; i--) {
        //     for (int j = B.length-1; j >=0; j--){
        //         if (A[i] == B[j]) {
        //             dp[i][j] = dp[i+1][j+1] + 1;
        //         }
        //         result = Math.max(result, dp[i][j]);
        //     }
        // }
        for (int i = 0; i < A.length; i++){
            for (int j =0; j < B.length; j++) {
                if (A[i] == B[j]) {
                    if (i > 0 || j > 0) {
                        dp[i][j] = dp[i-1]dp[j-1];
                    }
                    dp[i][j]++;
                }
                result = Math.max(result, dp[i][j]);
            }
        }
        return result;
    }

    public int findLength(int[] A, int[] B) {   
        int maxLen = 0;
        for (int j = 0; j < B.length; j++) {
            int maxLenEnding = 0;
            for (int i = 0, k = j; i < A.length && k < B.length; i++, k++) {
                if (A[i] != B[k]) maxLenEnding = 0;
                else {
                    maxLenEnding++;
                    maxLen = Math.max(maxLen, maxLenEnding);
                }
            }
        }
        
        for (int i =1; i < A.length; i++) {
            int maxLenEnding = 0;
            for (int j = 0, k = i; k < A.length && j < B.length; j++, k++) {
                if (A[k] != B[j]) maxLenEnding = 0;
                else {
                    maxLenEnding++;
                    maxLen = Math.max(maxLen, maxLenEnding);
                }
            }
        }      
        return maxLen;   
    }

    public int findLength(int[] A, int[] B) {
        int n1 = A.length, n2 = B.length;
        int res = 0;
        for (int offset = -n1; offset < n2; offset++) {
            int count = 0;
            for (int i = Math.max(offset, 0); i - offset < n1 && i < n2; i++) {
                if (A[i - offset] == B[i]) {
                    count++;
                    res = Math.max(res, count);
                } else {
                    count = 0;
                }
            }
        }
        return res;
    }
}
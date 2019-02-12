public class UglyNumberII {
    public int nthUglyNumber(int n) {
        int count = 1;
        int current = 2;
        if (n == 1) {
            return 1;
        }
        while (count < n) {
            int temp = current;
            while (temp % 2 == 0) {
                temp = temp / 2;
            }
            while (temp % 3 == 0) {
                temp = temp / 3;
            }
            while (temp % 5 == 0) {
                temp = temp / 5;
            }
            if (temp == 1) {
                count++;
            }
            current++;
        }
        return count;
    }
    public int nthUglyNumberDP(int num) {
        int[] ugly = new int[num];
        ugly[0] = 1;
        int ugly2Index = 0;
        int ugly3Index = 0;
        int ugly5Index = 0;
        int nextMultiply2 = 2;
        int nextMultiply3 = 3;
        int nextMultiply5 = 5;
        for(int i = 1; i < num; i++){
            int minUgly = Math.min(nextMultiply5, Math.min(nextMultiply2, nextMultiply3));
            ugly[i] = minUgly;
            if (nextMultiply2 == minUgly) {
                nextMultiply2 = ugly[++ugly2Index] * 2;
            }
            if (nextMultiply3 == minUgly) {
                nextMultiply3 = ugly[++ugly3Index] * 3;
            }
            if (nextMultiply5 == minUgly) {
                nextMultiply5 = ugly[++ugly5Index] * 5;
            }
        }
        return ugly[num-1];
    }
}
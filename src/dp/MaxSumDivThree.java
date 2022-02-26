package dp;

public class MaxSumDivThree {
    public int maxSumDivThree(int[] nums) {
        int sum = 0;
        int candidateOne = 20000;
        int candidateTwo = 20000;
        for (int n : nums) {
            sum += n;
            if(n % 3 == 1) {
                candidateTwo = Math.min(candidateTwo, candidateOne + n);
                candidateOne = Math.min(candidateOne, n);
            }
            if(n % 3 == 2) {
                candidateOne = Math.min(candidateOne, candidateTwo + n);
                candidateTwo = Math.min(candidateTwo, n);
            }
        }
        if (sum % 3 == 0) return sum;
        if (sum % 3 == 1) {
            return sum - candidateOne;
        } else {
            return sum - candidateTwo;
        }
    }

    /*
    1. previous maximum possible sum that it is divisible by three
               preSum % 3 == 0       (example: preSum=12 if lastNum=3)
            2. preSum % 3 == 1       (example: preSum=13 if lastNum=2)
            3. preSum % 3 == 2       (example: preSum=14 if lastNum=1)
        2. This recusion + "subroutines" pattern hints Dynamic Programming

    dp state:
        dp[i] = max sum such that the reminder == i when sum / 3
    Transition:
        dp_cur[(rem + num) % 3]
            = max(dp_prev[(rem + num) % 3], dp_prev[rem]+num)
            where "rem" stands for reminder for shorter naming
        meaning:
            "Current max sum with reminder 0 or 1 or 2" could be from
            EITHER prevSum with reminder 0 or 1 or 2 consecutively
            OR     prevSum with some reminder "rem" + current number "num"

            Since (dp_prev[rem]+num) % 3 = (rem+num) % 3 = i, we are able to correctly
            update dp[i] for i = 1,2,3 each time
     */
    public int maxSumDivThreeDP(int[] A) {
        int[] dp = new int[]{0, Integer.MIN_VALUE, Integer.MIN_VALUE};
        for (int a : A) {
            int[] dp2 = new int[3];
            for (int i = 0; i < 3; ++i)
                dp2[(i + a) % 3] = Math.max(dp[(i + a) % 3], dp[i] + a);
            dp = dp2;
        }
        return dp[0];
    }
}

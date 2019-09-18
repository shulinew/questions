package dp;
/*
https://leetcode.com/problems/target-sum/discuss/97335/Short-Java-DP-Solution-with-Explanation
https://leetcode.com/problems/target-sum/discuss/97334/Java-(15-ms)-C%2B%2B-(3-ms)-O(ns)-iterative-DP-solution-using-subset-sum-with-explanation
The idea behind this approach is as follows. Suppose we can find out the number of times a 
particular sum, say sumisum_isumi​ is possible upto a particular index, say iii, in the 
given numsnumsnums array, which is given by say counticount_icounti​. Now, we can find out 
the number of times the sum sumi+nums[i]sum_i + nums[i]sumi​+nums[i] can occur easily as counticount_icounti​. Similarly, the number of times the sum sumi−nums[i]sum_i - nums[i]sumi​−nums[i] occurs is also given by counticount_icounti​.

Thus, if we know all the sums sumjsum_jsumj​'s which are possible upto the jthj^{th}jth index by using various assignments, along with the corresponding count of assignments, countjcount_jcountj​, leading to the same sum, we can determine all the sums possible upto the (j+1)th(j+1)^{th}(j+1)th index along with the corresponding count of assignments leading to the new sums.

Based on this idea, we make use of a dpdpdp to determine the number of assignments which can lead to the given sum. dp[i][j]dp[i][j]dp[i][j] refers to the number of assignments which can lead to a sum of jjj upto the ithi^{th}ith index. To determine the number of assignments which can lead to a sum of sum+nums[i]sum + nums[i]sum+nums[i] upto the (i+1)th(i+1)^{th}(i+1)th index, we can use dp[i][sum+nums[i]]=dp[i][sum+nums[i]]+dp[i−1][sum]dp[i][sum + nums[i]] = dp[i][sum + nums[i]] + dp[i-1][sum]dp[i][sum+nums[i]]=dp[i][sum+nums[i]]+dp[i−1][sum]. Similarly, dp[i][sum−nums[i]]=dp[i][sum+nums[i]]+dp[i−1][sum]dp[i][sum - nums[i]] = dp[i][sum + nums[i]] + dp[i-1][sum]dp[i][sum−nums[i]]=dp[i][sum+nums[i]]+dp[i−1][sum]. We iterate over the dpdpdp array in a rowwise fashion i.e. Firstly we obtain all the sums which are possible upto a particular index along with the corresponding count of assignments and then proceed for the next element(index) in the numsnumsnums array.

But, since the $$sum can range from -1000 to +1000, we need to add an offset of 1000 to the sum indices (column number) to map all the sums obtained to positive range only.

At the end, the value of dp[n−1][S+1000]dp[n-1][S+1000]dp[n−1][S+1000] gives us the required number of assignments. Here, nnn refers to the number of elements in the numsnumsnums array.

The animation below shows the way various sums are generated along with the corresponding indices. The example assumes sumsumsum values to lie in the range of -6 to +6 just for the purpose of illustration. This animation is inspired by @Chidong
*/

import java.util.Arrays;

public class TargetSum {
    int count = 0;

    public int findTargetSumWaysBruteForce(int[] nums, int S) {
        int count = 0;
        count = calculate(nums, 0, 0, S, count);
        return count;
    }

    private int calculate(int[] nums, int i, int sum, int S, int count) {
        if (i == nums.length) {
            if (sum == S) {
                count++;
            }
        } else {
            count = calculate(nums, i + 1, sum + nums[i], S, count);
            count = calculate(nums, i + 1, sum - nums[i], S, count);
        }
        return count;
    }

    public int findTargetSumWaysMemo(int[] nums, int S) {
        int[][] memo = new int[nums.length][2001];
        for (int[] row : memo)
            Arrays.fill(row, Integer.MIN_VALUE);
        return calculateMemo(nums, 0, 0, S, memo);
    }

    private int calculateMemo(int[] nums, int i, int sum, int S, int[][] memo) {
        if (i == nums.length) {
            if (sum == S)
                return 1;
            else
                return 0;
        } else {
            if (memo[i][sum+1000] != Integer.MIN_VALUE) {
                return memo[i][sum+1000];
            }
            int add = calculateMemo(nums, i + 1, sum + nums[i], S, memo);
            int subtract = calculateMemo(nums, i + 1, sum - nums[i], S, memo);
            memo[i][sum+1000] = add + subtract;
            return memo[i][sum+1000];
        }
    }

    public int findTargetSumWaysDfs(int[] nums, int S) {
        if (nums == null) return 0;
        int n = nums.length;
        int[] sums = new int[n];
        sums[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            sums[i] = nums[i] + sums[i + 1];
        }
        return dfs(nums, sums, S, 0, 0);
    }

    public int dfs(int[] nums, int[] sums, int S, int index, int sum) {
        int res = 0;
        if (index == nums.length) {
            if (sum == S) res++;
            return res;
        }
        if (sums[index] < Math.abs(S - sum)) return 0;
        res += dfs(nums, sums, S, index + 1, sum + nums[index]);
        res += dfs(nums, sums, S, index + 1, sum - nums[index]);
        return res;
    }

    public int findTargetSumWaysDP(int[] nums, int S) {
        int sum = 0;
        for (int num: nums) sum += num;
        if (S > sum || S < -sum)
            return 0;
        int[][] dp = new int[nums.length][sum*2+1];
        dp[0][nums[0]+sum] = 1;
        dp[0][sum-nums[0]] += 1;
        for (int i = 1; i < nums.length; i++){
            for (int j = -sum; j <= sum; j++) {
                if (dp[i-1][j+sum] > 0) {
                    dp[i][j+nums[i]+sum] += dp[i-1][j+sum];
                    dp[i][j-nums[i]+sum] += dp[i-1][j+sum];
                }
            }
        }
        return dp[nums.length-1][S+sum];
    }

    public int findTargetSumWaysDPOndemision(int[] nums, int S) {
        int sum = 0;
        for (int i : nums) sum += i;
        if (S > sum || S < -sum) return 0;
        int[] dp = new int[2*sum+1];
        dp[0+sum] = 1;
        for(int i = 0; i < nums.length; i++) {
            int[] next = new int[2*sum+1];
            for(int k = 0; k < 2*sum+1; k++) {
                if (dp[k] != 0) {
                    next[k+nums[i]] += dp[k];
                    next[k-nums[i]] += dp[k];
                }
            }
            dp = next;
        }
        return dp[S+sum];
    }


//    public int findTargetSumWays(int[] nums, int s) {
//        int sum = 0;
//        for (int n : nums)
//            sum += n;
//        return sum < s || (s + sum) % 2 > 0 ? 0 : subsetSum(nums, (s + sum) >>> 1);
//    }

//    public int subsetSum(int[] nums, int s) {
//        int[] dp = new int[s + 1];
//        dp[0] = 1;
//        for (int n : nums)
//            for (int i = s; i >= n; i--)
//                dp[i] += dp[i - n];
//        return dp[s];
//    }

//    public int findTargetSumWays(int[] nums, int S) {
//        int totalSum = 0;
//        for (int num : nums) {  //calculate the totalSum keeping all the elements in the array positive
//            totalSum += num;
//        }
//        if (totalSum < S || -totalSum > S) { //If the target sum S is not reachable by the range
//            return 0;
//        }
//        int[] dp = new int[2 * totalSum + 1];
//        //dp[i] -> the number of ways to have sum = i - totalSum
//        dp[totalSum] = 1;
//        //We start from no elements in the array, so there is one way to have sum = 0 that there is no element
//        for (int i = 0; i < nums.length; i++) { //Start from deciding to add the first element as positive or negative
//            int[] next = new int[2 * totalSum + 1];
//            for (int j = 0; j < 2 * totalSum + 1; j++) {
//                if (dp[j] != 0) {  //if current sum j - totalSum is already reached by the previous searched numbers
//                    next[j + nums[i]] += dp[j]; //plus the num of ways to have sum = j - totalSum to the num of ways to have sum = j + nums[i] - totalSum
//                    next[j - nums[i]] += dp[j];
//                }//The previous reached range is  -totalSum < [-currSum, currSum ] < totalSum.
//                //Since currSum + nums[i] no larger than totalSum, -currSum - nums[i] no smaller than -totalSum, so it won't exceed the boundary
//            }
//            dp = next;
//        }
//        return dp[S + totalSum]; //return the num of ways to have sum = S
//    }

//    public int findTargetSumWays(int[] nums, int S) {
//        if (nums == null || nums.length == 0) {
//            return 0;
//        }
//        return helper(nums, 0, 0, S, new HashMap<>());
//    }

//    private int helper(int[] nums, int index, int sum, int S, Map<String, Integer> map) {
//        String encodeString = index + "->" + sum;
//        if (map.containsKey(encodeString)) {
//            return map.get(encodeString);
//        }
//        if (index == nums.length) {
//            if (sum == S) {
//                return 1;
//            } else {
//                return 0;
//            }
//        }
//        int curNum = nums[index];
//        int add = helper(nums, index + 1, sum - curNum, S, map);
//        int minus = helper(nums, index + 1, sum + curNum, S, map);
//        map.put(encodeString, add + minus);
//        return add + minus;
//    }
    public static void main(String[] args) {
        TargetSum targetSum = new TargetSum();
//        System.out.println(targetSum.findTargetSumWaysDPOndemision(new int[]{1, 1, 1, 1, 1}, 2));

        StoneGame sg = new StoneGame();
//        System.out.println(sg.stoneGame2(new int[]{2,7,9,4,4}));
        LongestCommonSequence lcs = new LongestCommonSequence();
//        System.out.println(lcs.longestCommonSubsequence("bbbab",
//                "babbb"
//        ));
        System.out.println(lcs.longestPalindromeSubseq("bbbab"));


    }
}
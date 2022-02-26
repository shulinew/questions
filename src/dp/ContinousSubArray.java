import java.util.Set;

/*
Haven't seen anyone post the math or theory behind the solutions yet, so I'm sharing mine. Let me know if there is any better one.
In short, start with mod =0, then we always do mod = (mod+nums[i])%k, if mod repeats, that means between these two mod = x occurences the sum is multiple of k.
Math: c = a % k, c = b % k, so we have a % k = b % k.
Where a is the mod at i and b is the mod at j and a <= b, i < j, because all nums are non-negative. And c is the mod that repeats.
Suppose b-a=d, then we have b % k = ((a+d) % k)%k = (a%k + d%k)%k
In order to make the equation valid: a % k = (a%k + d%k)%k
d%k has to be 0, so d, the different between b and a, is a multiple of k
Example:
[23, 2, 1, 6, 7] k=9
mod = 5, 7, 8, 5 <-- at here we found it

https://leetcode.com/problems/continuous-subarray-sum/discuss/173165/Java-solution-beats-100
*/

public class ContinousSubArray {
    public boolean checkSubarraySum(int[] nums, int k) {
        // Since the size of subarray is at least 2.
        if (nums.length <= 1) return false;
        // Two continuous "0" will form a subarray which has sum = 0. 0 * k == 0 will always be true.
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == 0 && nums[i + 1] == 0) return true;
        }

        // [0,1,0,3,0,4,0,4,0]
        if (k == 0) return false;
        // Let's only check positive k. Because if there is a n makes n * k = sum, it is always true -n * -k = sum.
        if (k < 0) k = -k;

        Map<Integer, Integer> sumToIndex = new HashMap<>();
        int sum = 0;
        sumToIndex.put(0, -1);

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            // Validate from the biggest possible n * k to k
            for (int j = (sum / k) * k; j >= k; j -= k) {
                if (sumToIndex.containsKey(sum - j) && (i - sumToIndex.get(sum - j) > 1)) return true;
            }
            if (!sumToIndex.containsKey(sum)) sumToIndex.put(sum, i);
        }

        return false;
    }
    public boolean checkSubarraySum1(int[] nums, int k) {
        if (nums.length <= 1) return false;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == 0 && nums[i + 1] == 0) return true;
        }
        if (k==0) return false;
        if ((nums.length+2) > k*2) return true;
        int preSum = 0;
        Map<Integer,Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            preSum += nums[i];
            int currentMod = preSum % k;
            if (map.get(currentMod) != null) {
                int j = map.get(currentMod);
                if (j - i >= 2) return true;
            }
            else{
                map.put(currentMod, i);
            }
        }
        return false;
    }

    public boolean checkSubArray(int[] nums, int k) {
        if (nums.length < 2) return false;
        
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == 0 && nums[i+1] == 0) return true;
        }
        if (k == 0) return false; /* since we know we don't have continuous 0s in nums anymore after the previous for loop*/
        
        int[] sum = new int[nums.length];
        int preSum = 0;
        for (int i = 0; i < sum.length; i++) {
            sum[i] = nums[i] + preSum;
            preSum += nums[i];
        }
        
        /* There are two kind of possible solutions: 1) sum[J] itself is divisible by k. 2) sum[J] - sum[I] is divisible by k*/
        Map<Integer, Integer> remainderToIndex = new HashMap<>();
        remainderToIndex.put(0, -1); /* for case 1). if sum[J] is divisible by k, then the length is J + 1 (= J - (-1))*/ 
        for (int i = 0; i < sum.length; i++) {
            int remainder = sum[i] % k;
            if (remainderToIndex.containsKey(remainder)) {
                if (i - remainderToIndex.get(remainder) > 1) {
                    return true; /* i is J, remainderToIndex.get(remainder) is I
                                  If sum[J]%k = sum[I]%k, then (sum[J]-sum[I]) % k = 0. This is case 2)*/
                }
            } else {
                remainderToIndex.put(remainder, i);
            }
        }
        return false;
    }
}
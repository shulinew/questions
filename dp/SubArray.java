/*
Given a list of non-negative numbers and a target integer k, write a function
 to check if the array has a continuous subarray of size at least 2 that sums up 
 to the multiple of k, that is, sums up to n*k where n is also an integer. 

Input: [23, 2, 4, 6, 7],  k=6
Output: True
Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
Input: [23, 2, 6, 4, 7],  k=6
Output: True
Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.
*/

public class SubArray {
    /*
    We iterate through the input array exactly once, keeping track of the running 
    sum mod k of the elements in the process. If we find that a running sum value 
    at index j has been previously seen before in some earlier index i in the array, 
    then we know that the sub-array (i,j] contains a desired sum.*/
    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>(){{put(0,-1);}};;
        int runningSum = 0;
        for (int i=0;i<nums.length;i++) {
            runningSum += nums[i];
            if (k != 0) runningSum %= k; 
            Integer prev = map.get(runningSum);
            if (prev != null) {
                if (i - prev > 1) return true;
            }
            else map.put(runningSum, i);
        }
        return false;
    }


    public boolean checkSubarraySumEasyUnderstand(int[] nums, int k) {
        if (nums == null || nums.length == 0)   return false;
        
        int[] preSum = new int[nums.length+1];
        
        for (int i = 1; i <= nums.length; i++) {
            preSum[i] = preSum[i-1] + nums[i-1];
        }
        
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+2; j <= nums.length; j++) {
                if (k == 0) {
                    if (preSum[j] - preSum[i] == 0) {
                        return true;
                    }
                } else if ((preSum[j] - preSum[i]) % k == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkSubarraySum(int[] nums, int k) {
        int[] sums = new int[nums.length+1];
        sums[0]=0;
        for(int i=1; i<sums.length; i++){
            sums[i] = sums[i-1]+nums[i-1];
        }
        for(int j=2; j<sums.length; j++){
            for(int i=j-2; i>=0; i--){
// sums[j]-sums[i] is the sum of (nums[j-1]....nums[i]) and its length is always greater than 2
                if(k==0 && sums[j]-sums[i]==k)
                    return true;
                if(k!=0 && (sums[j]-sums[i])%k==0)
                    return true;
            }
        }
        return false;
    }

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
    */

}
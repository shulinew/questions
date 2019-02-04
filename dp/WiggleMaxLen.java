/* https://leetcode.com/problems/wiggle-subsequence/solution/
A sequence of numbers is called a wiggle sequence if the differences between successive numbers strictly alternate between positive and negative. The first difference (if one exists) may be either positive or negative. A sequence with fewer than two elements is trivially a wiggle sequence.

For example, [1,7,4,9,2,5] is a wiggle sequence because the differences (6,-3,5,-7,3) are alternately positive and negative. In contrast, [1,4,7,2,5] and [1,7,4,5,5] are not wiggle sequences, the first because its first two differences are positive and the second because its last difference is zero.

Given a sequence of integers, return the length of the longest subsequence that is a wiggle sequence. A subsequence is obtained by deleting some number of elements (eventually, also zero) from the original sequence, leaving the remaining elements in their original order.
Input: [1,7,4,9,2,5]
Output: 6
Explanation: The entire sequence is a wiggle sequence.
Input: [1,17,5,10,13,15,10,5,16,8]
Output: 7
Explanation: There are several subsequences that achieve this length. One is [1,17,10,13,10,16,8].
Input: [1,2,3,4,5,6,7,8,9]
Output: 2
*/

public class WiggleMaxLen {
    public int wiggleMaxLength(int[] nums) {
         int len = nums.length;
         if (len <= 2) {
             return len;
         }
         int [] sequency = new int[len];
         int max = Math.max(findMaxLen(nums, nums[0],  0, -1, sequency), findMaxLen(nums, nums[0], 0, 1, sequency));
         return max;
    }
    private int findMaxLen(int[] nums, int pre, int start, int sign, int [] sequency) {
         int len = nums.length;
         if (start == len-1) {
        	 if (((pre - nums[start])*sign) >=0) {
            	 sequency[start] = nums[start];
                 return 1; 
        	 } else {
        		 return 0;
        	 }
         } 
         if (((pre - nums[start+1])*sign) >0) {
        	 sequency[start] = pre;
             return 1 + findMaxLen(nums, nums[start+1], start+1, -1*sign, sequency);
         } else {
             return findMaxLen(nums, pre, start+1, sign, sequency);
         }  
    }

    public int wiggleMaxLengthDP(int[] nums) {
        int len = nums.length;
        int[] up = new int[len];
        int[] down = new int[len];
        if (len <= 2) {
            return len;
        }
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    up[i] = Math.max(up[i], down[j]+1);
                } else{
                    down[i] = Math.max(down[i], up[j]+1);
                }
            }
        }
        return Math.max(up[len-1], down[len-1])+1;
    }

    public int wiggleMaxLengthDP2(int[] nums) {
        int len = nums.length;
        int[] up = new int[len];
        int[] down = new int[len];
        if (len < 2) {
            return len;
        }
        up[0] = down[0] = 1;
        for (int i = 1; i < len; i++) {
            if (nums[i] - nums[i-1] > 0) {
                up[i] = down[i-1] +1;
                down[i] = down[i-1];
            } else if (nums[i] < nums[i-1]) {
                down[i] = up[i-1] + 1;
                up[i] = up[i-1];
            } else {
                down[i] = down[i-1];
                up[i] = up[i-1];
            }
        }
        return Math.max(up[len-1], down[len-1]);
    }

    public int wiggleMaxLengthDPNoSpace(int nums[]) {
        int len = nums.lenght;
        if (len < 2) {
            return len;
        }
        int down = 1; 
        int up = 1;
        for (int i =1; i < len; i++) {
            if (nums[i] > nums[i-1]) {
                up = down+1;
            } else if (nums[i] < nums[i-1]) {
                down = up + 1;
            }
        }
        return Math.max(up, down);
    }
}
package trie;

import java.util.HashSet;
import java.util.Set;

import math.CountSay;

/*
 * Given a non-empty array of numbers, a0, a1, a2, … , an-1, where 0 ≤ ai < 231.
	
	Find the maximum result of ai XOR aj, where 0 ≤ i, j < n.
	
	Could you do this in O(n) runtime?
	Input: [3, 10, 5, 25, 2, 8]
	
	Output: 28
	
	Explanation: The maximum result is 5 ^ 25 = 28.
 */
public class FindMaximumXor {
    /*
     * to iteratively determine what would be each bit of the final result from left to right. And it narrows 
     * down the candidate group iteration by iteration. e.g. assume input are a,b,c,d,...z, 26 integers in total.
     *  In first iteration, if you found that a, d, e, h, u differs on the MSB(most significant bit), so you are 
     *  sure your final result's MSB is set. Now in second iteration, you try to see if among a, d, e, h, u there 
     *  are at least two numbers make the 2nd MSB differs, if yes, then definitely, the 2nd MSB will be set in 
     *  the final result. And maybe at this point the candidate group shinks from a,d,e,h,u to a, e, h. 
     *  Implicitly, every iteration, you are narrowing down the candidate group, but you don't need to track how 
     *  the group is shrinking, you only cares about the final result.
     */
    public int findMaximumXOR(int[] nums) {
        int max = 0, mask = 0;
        for(int i = 31; i >= 0; i--){
            mask = mask | (1 << i);
            Set<Integer> set = new HashSet<Integer>();
            for(int num : nums){
                set.add(num & mask);
            }
            int tmp = max | (1 << i);
            for(int prefix : set){
                if(set.contains(tmp ^ prefix)) {
                    max = tmp;
                    break;
                }
            }
        }
        return max;
    }
    
    class Trie {
        Trie[] children;
        public Trie() {
            children = new Trie[2];
        }
    }
    
    public int findMaximumXORTrie(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        // Init Trie.
        Trie root = new Trie();
        for(int num: nums) {
            Trie curNode = root;
            for(int i = 31; i >= 0; i --) {
                int curBit = (num >>> i) & 1;
                if(curNode.children[curBit] == null) {
                    curNode.children[curBit] = new Trie();
                }
                curNode = curNode.children[curBit];
            }
        }
        int max = Integer.MIN_VALUE;
        for(int num: nums) {
            Trie curNode = root;
            int curSum = 0;
            for(int i = 31; i >= 0; i --) {
                int curBit = (num >>> i) & 1;
                if(curNode.children[curBit ^ 1] != null) {
                    curSum += (1 << i);
                    curNode = curNode.children[curBit ^ 1];
                }else {
                    curNode = curNode.children[curBit];
                }
            }
            max = Math.max(curSum, max);
        }
        return max;
    }
    
    /*
     * example: Given [14, 11, 7, 2], which in binary are [1110, 1011, 0111, 0010].
S	since the MSB is 3, I'll start from i = 3 to make it simplify.

    i = 3, set = {1000, 0000}, max = 1000
    i = 2, set = {1100, 1000, 0100, 0000}, max = 1100
    i = 1, set = {1110, 1010, 0110, 0010}, max = 1100
    i = 0, set = {1110, 1011, 0111, 0010}, max = 1100

     */
    public int findMaximumXOR2(int[] nums) {
        int max = 0, mask = 0;
        for (int i = 31; i >= 0; i--) {
            mask |= (1 << i);
            HashSet<Integer> set = new HashSet<Integer>();
            for (int num : nums) {
                set.add(num & mask); // reserve Left bits and ignore Right bits
            }
            
            /* Use 0 to keep the bit, 1 to find XOR
             * 0 ^ 0 = 0 
             * 0 ^ 1 = 1
             * 1 ^ 0 = 1
             * 1 ^ 1 = 0
             */
            int tmp = max | (1 << i); // in each iteration, there are pair(s) whoes Left bits can XOR to max
            for (int prefix : set) {
                if (set.contains(tmp ^ prefix)) {
                    max = tmp;
                }
            }
        }
        return max;
    }
    
    public static void main(String[] args) {
//    	FindMaximumXor test = new FindMaximumXor();
//    	int [] nums = {3, 10, 5, 25, 2, 8};
//    	test.findMaximumXOR(nums);
    	CountSay countSay = new CountSay();
    	System.out.println("1: " + countSay.countAndSay(1));
    	System.out.println("2: " + countSay.countAndSay(2));
    	System.out.println("3: " + countSay.countAndSay(3));
    	System.out.println("4: " + countSay.countAndSay(4));
    }

}

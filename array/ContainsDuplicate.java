package array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * Given an array of integers and an integer k, find out whether there are two distinct 
 * indices i and j in the array such that nums[i] = nums[j] and the difference between 
 * i and j is at most k. 
 */
public class ContainsDuplicate {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
    	Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    	for (int i = 0; i<nums.length;i++){
    		Integer j = map.get(nums[i]);
    		if (j == null){
    			map.put(nums[i],i);
    		}else{
    			if (i - j <= k){
    				return true;
    			}else{
    			    map.put(nums[i],i);
    			}
    		}
    	}
    	return false;
        
    }
    public boolean containsNearbyDuplicate1(int[] nums, int k) {
    	Set<Integer> sets = new HashSet<Integer>();
    	for (int i = 0; i<nums.length;i++){
    		if (i > k){
    			sets.remove(nums[i-k-1]);
    		}
    		if (!sets.add(nums[i])){
    			return true;
    		}
    	}
    	return false;
        
    }
    
    public  boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        for(int i : nums)
            if(!set.add(i))// if there is same
                return true; 
        return false;
    }
    
    public  boolean containsDuplicate2(int[] nums) {

    	Arrays.sort(nums);
    	for (int i=1;i<nums.length;i++){
    		if (nums[i]==nums[i--])
    			return true;
    	}
    	return false;
    }

}

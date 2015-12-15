package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
 *  Given a set of distinct integers, nums, return all possible subsets.
	Note:
	    Elements in a subset must be in non-descending order.
	    The solution set must not contain duplicate subsets.
 */

public class SubSet {
    public List<List<Integer>> subsets(int[] nums) {
    	List<List<Integer>> results = new ArrayList<List<Integer>>();
    	results.add(new ArrayList<Integer>());
    	Arrays.sort(nums);
    	for (int i: nums){
    		List<List<Integer>> temp = new ArrayList<List<Integer>>();
    		for (List<Integer> oneSubSet: results){
    			List<Integer> existing = new ArrayList<Integer>(oneSubSet);
    			existing.add(i);
    			temp.add(existing);
    		}
    		results.addAll(temp);
    	}
    	return results;
        
    }
    
    public List<List<Integer>> subsets2(int[] nums) {
    	Arrays.sort(nums);
    	List<List<Integer>> results = new ArrayList<List<Integer>>();
    	results.add(new ArrayList<Integer>());
    	for (int i = 0; i<nums.length;i++){
    		int size = results.size();
    		for(int j = 0; j<size;j++){
    			List<Integer> temp = new ArrayList<Integer>(results.get(j));
    			temp.add(nums[i]);
    			results.add(temp);
    		}
    	}
    	return results;
    }
    
    //Use bit manipulation
    public List<List<Integer>> subsets1(int[] nums) {
    	List<List<Integer>> subsets = new ArrayList<List<Integer>>();
    	int length = nums.length;
    	for (int i= 0; i< Math.pow(2, length);i++){
    		List<Integer> oneSubset = new ArrayList<Integer>();
    		for (int j = 0; j< length;j++){
    			//if (((1 << j) & i) != 0)
    			if (((i>>j)&1) != 0){
    				oneSubset.add(nums[j]);
    			}
    		}
    		Collections.sort(oneSubset);
    		subsets.add(oneSubset);
    	}
    	return subsets;
    }
    
    /*
     * Given a collection of integers that might contain duplicates, nums, 
     * return all possible subsets. 
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        
    	Arrays.sort(nums);
    	List<List<Integer>> results = new ArrayList<List<Integer>>();
    	results.add(new ArrayList<Integer>());
    	int size = 0;
    	for (int i = 0; i<nums.length;i++){
    		int startIndex = (i >=1 && nums[i]==nums[i-1])?size:0;
    		size = results.size();
    		for(int j = startIndex; j<size;j++){
    			List<Integer> temp = new ArrayList<Integer>(results.get(j));
    			temp.add(nums[i]);
    			results.add(temp);
    		}
    	}
    	return results;
    }
    
    
    public static void main(String[] args){
    	SubSet subSet = new SubSet();
    	int nums [] = {1,2,2};
    	subSet.subsetsWithDup(nums);
    }

}

package math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Given a collection of distinct numbers, return all possible permutations. 
 * https://discuss.leetcode.com/topic/6377/my-ac-simple-iterative-java-python-solution/5
 * https://discuss.leetcode.com/topic/46162/a-general-approach-to-backtracking-questions-in-java-subsets-permutations-combination-sum-palindrome-partioning
 */
public class Permutations {
    public List<List<Integer>> permute(int[] nums) {
    	List<List<Integer>> list = new ArrayList<List<Integer>>();
    	backTrack(list, new ArrayList<Integer>(), nums);
    	return list;
    }
    private void backTrack(List<List<Integer>> list, List<Integer> tempList, int[] nums){
    	if (tempList.size() == nums.length) {
    		list.add(new ArrayList<Integer>(tempList));
    	} else {
	    	for (int i =  0; i< nums.length; i++){
	    		if (tempList.contains(nums[i])){
	    			continue;
	    		}
	    		tempList.add(nums[i]);
	    		backTrack(list, tempList, nums);
	    		tempList.remove(tempList.size()-1);
	    	}
    	}
    }
    public List<List<Integer>> permuteUnique(int[] nums) {
    	List<List<Integer>> list = new ArrayList<List<Integer>>();
    	Arrays.sort(nums);
    	backTrackUnique(list, new ArrayList<Integer>(), nums, new boolean[nums.length]);
    	return list;
        
    }
    private void backTrackUnique(List<List<Integer>> list, List<Integer> tempList, int[] nums, boolean[] used){
    	if (tempList.size() == nums.length) {
    		list.add(new ArrayList<Integer>(tempList));
    	} else {
	    	for (int i =  0; i< nums.length; i++){
	    		if (used[i] || i>0 && nums[i-1] == nums[i] && !used[i-1]){
	    			continue;
	    		}
	    		used[i] = true;
 	    		tempList.add(nums[i]);
 	    		backTrackUnique(list, tempList, nums, used);
	    		used[i] = false;
	    		tempList.remove(tempList.size()-1);
	    	}
    	}
    }
    List<List<Integer>> toRet;
    public List<List<Integer>> permuteDsf(int[] nums) 
    {
        toRet = new ArrayList<List<Integer>>();
        dfs(nums, 0, nums.length - 1);
        return toRet;
    }
    void swap(int[] nums, int f, int l)
    {
        if(f != l)
        {
            int t = nums[f];
            nums[f] = nums[l];
            nums[l] = t;
        }
    }
    void dfs(int[] nums, int ind, int len)
    {
        if(ind == len)
        {
            List<Integer> trex = new ArrayList<Integer>();
            for(int i = 0; i <= len; i++)
                trex.add(nums[i]);
            toRet.add(trex);
            return;
        }
        for(int i = ind; i <= len; i++)
        {
            swap(nums, ind, i);
            dfs(nums, ind + 1, len);
            swap(nums, i, ind);
        }
    }
    public static void main(String[] args){
//    	int[] nums = {1,2,3};
    	int[] nums = {3,1,1};
    	Permutations pu = new Permutations();
    	List<List<Integer>> list = pu.permuteUnique(nums);
    }
}

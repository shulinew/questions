package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CombinedSum {
	/*
	 *  Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
		The same repeated number may be chosen from C unlimited number of times.
		Note:
		    All numbers (including target) will be positive integers.
		    Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
		    The solution set must not contain duplicate combinations.
		For example, given candidate set 2,3,6,7 and target 7,
		A solution set is:
		[7]
		[2, 2, 3]
	 */
	
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
    	Arrays.sort(candidates);
    	List<List<Integer>> result = new ArrayList<List<Integer>>();
    	findCombinations(result, new ArrayList<Integer>(), 0, target, candidates);
        return result;
    }
    private void findCombinations(List<List<Integer>> result, List<Integer> list, int i, int target, int[] can){
    	if (target == 0){
    		result.add(list);
    		return;
    	}
  
    	for (int j = i; j< can.length; i++){
    		int newTarget = target - can[j];
    		if (newTarget >= 0){
    			List<Integer> copy = new ArrayList<Integer>(list);
    			copy.add(can[j]);
    			findCombinations(result, copy, j, newTarget, can);
    		}else{
    			break;
    		}
    	}
    }
    
    /*
     *  Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
		Each number in C may only be used once in the combination.
		Note:
		
		    All numbers (including target) will be positive integers.
		    Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
		    The solution set must not contain duplicate combinations.
		For example, given candidate set 10,1,2,7,6,1,5 and target 8,
		A solution set is:
		[1, 7]
		[1, 2, 5]
		[2, 6]
		[1, 1, 6]
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    	Arrays.sort(candidates);
    	List<List<Integer>> result = new ArrayList<List<Integer>>();
    	dfs1(result, new ArrayList<Integer>(), 0, target, candidates);
        return result;
    }
    private void dfs(List<List<Integer>> result, List<Integer> list, int current, int target, int[] can){
    	if (target == 0){
    		result.add(list);
    		return;
    	}
    	if (target < 0){
    		return;
    	}
    	for (int i = current; i< can.length;i++ ){
    		if (i > current && can[i] == can[i-1])
    			continue;
			List<Integer> copy = new ArrayList<Integer>(list);
			copy.add(can[i]);
			int newTarget = target - can[i];
			dfs(result, copy, i+1, newTarget, can);
			copy.remove(copy.size() - 1);
    	}
    }
    private void dfs1(List<List<Integer>> result, List<Integer> list, int current, int target, int[] can){
    	for (int i = current; i< can.length;i++ ){
    		if (i > current && can[i] == can[i-1])
    			continue;
    		if ( can[i] == target){
    			List<Integer> copy = new ArrayList<Integer>(list);
    			copy.add(can[i]);
    			result.add(copy);
    			return;
    		}else if (can[i] < target){
    			List<Integer> copy = new ArrayList<Integer>(list);
    			copy.add(can[i]);
    			dfs1(result, copy, i+1, target - can[i], can);
    		}else{
    			break;
    		}
    	}
    }
    
    /*
     * 
		Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.
		Ensure that numbers within the set are sorted in ascending order.
		Example 1:
		Input: k = 3, n = 7
		Output: [[1,2,4]]
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
    	List<List<Integer>> result = new ArrayList<List<Integer>>();
    	findCombinations(result, new ArrayList<Integer>(), 1, k, n);
    	return result;
    }
    private void findCombinations(List<List<Integer>> result, List<Integer> list, int start, int n, int sum){
    	if (n == 1){
    		if (sum <= 9 && sum > start){
    			list.add(sum);
    			result.add(list);
    			return;
    		}
    	}
    	for (int i = start;i< sum/n; i++){
            List<Integer> subList = new LinkedList<Integer>( list );
            subList.add( i );
    		findCombinations(result, subList, i+1, n-1, sum -i);
    	}
    }

    public List<List<Integer>> combination3Backtrrace(int k, int n) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        backTrace(result, new ArrayList<Integer>(), 1, n, k);
        return result;
    }
    private void backTrace(List<List<Integer>> result, List<Integer> tempList, int start, int sum, int count) {
        if (count == 0 && sum == 0) {
            result.add(new ArrayList<Integer>(tempList));
        } else {
            for (int i = start; i <= 9; i++) {
                if (!tempList.contains(i) && sum - i >=0) {
                    tempList.add(i);
                    backTrace(result, tempList, i +1, sum-i, count-1);
                    tempList.remove(tempList.size()-1);
                }
            }
        }
    }
    //TODO
    public List<List<Integer>> combinationSumStudy(int[] cands, int target) {
        Arrays.sort(cands); 
        List<List<List<Integer>>> dp = new ArrayList<List<List<Integer>>>();
        for (int i = 1; i <= target; i++) { // run through all targets from 1 to t
            List<List<Integer>> newList = new ArrayList<List<Integer>>(); // combs for curr i
            // run through all candidates <= i
            for (int j = 0; j < cands.length && cands[j] <= i; j++) {
                // special case when curr target is equal to curr candidate
                if (i == cands[j]) newList.add(Arrays.asList(cands[j]));
                // if current candidate is less than the target use prev results
                else for (List<Integer> l : dp.get(i-cands[j]-1)) {
                    if (cands[j] <= l.get(0)) {
                        List<Integer> cl = new ArrayList<Integer>();
                        cl.add(cands[j]); 
                        cl.addAll(l);
                        newList.add(cl);
                    }
                }
            }
            dp.add(newList);
        }
        return dp.get(target-1);
    }
    
    /*
     * ==================================================================================================
     * 
     */
    /*
     * Think about the recurrence relation first. How does the # of combinations of the target related to the # of combinations of numbers that are smaller than the target?

So we know that target is the sum of numbers in the array. Imagine we only need one more number to reach target, this number can be any one in the array, right? So the # of combinations of target, comb[target] = sum(comb[target - nums[i]]), where 0 <= i < nums.length, and target >= nums[i].

In the example given, we can actually find the # of combinations of 4 with the # of combinations of 3(4 - 1), 2(4- 2) and 1(4 - 3). As a result, comb[4] = comb[4-1] + comb[4-2] + comb[4-3] = comb[3] + comb[2] + comb[1].

Then think about the base case. Since if the target is 0, there is only one way to get zero, which is using 0, we can set comb[0] = 1.

EDIT: The problem says that target is a positive integer that makes me feel it's unclear to put it in the above way. Since target == 0 only happens when in the previous call, target = nums[i], we know that this is the only combination in this case, so we return 1.

Now we can come up with at least a recursive solution.
     */
    public int combinationSum4(int[] nums, int target) {
        if (target == 0) {
            return 1;
        }
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (target >= nums[i]) {
                res += combinationSum4(nums, target - nums[i]);
            }
        }
        return res;
    }
    /*
     * Now for a DP solution, we just need to figure out a way to store the intermediate results, to avoid the same combination sum being calculated many times. We can use an array to save those results, and check if there is already a result before calculation. We can fill the array with -1 to indicate that the result hasn't been calculated yet. 0 is not a good choice because it means there is no combination sum for the target.
     */
    private int[] dp;

    public int combinationSum4(int[] nums, int target) {
        dp = new int[target + 1];
        Arrays.fill(dp, -1);
        dp[0] = 1;
        return helper(nums, target);
    }

    private int helper(int[] nums, int target) {
        if (dp[target] != -1) {
            return dp[target];
        }
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (target >= nums[i]) {
                res += helper(nums, target - nums[i]);
            }
        }
        dp[target] = res;
        return res;
    }
    
    // Top down
    public int combinationSum4(int[] nums, int target) {
        int[] comb = new int[target + 1];
        comb[0] = 1;
        for (int i = 1; i < comb.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i - nums[j] >= 0) {
                    comb[i] += comb[i - nums[j]];
                }
            }
        }
        return comb[target];
    }
}

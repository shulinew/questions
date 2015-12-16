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
}

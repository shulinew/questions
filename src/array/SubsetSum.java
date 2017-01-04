package array;

import java.util.HashMap;
import java.util.Map;

/*
 * Given a non-empty array containing only positive integers, find if the array can be partitioned into two
 *  subsets such that the sum of elements in both subsets is equal. 
 */
public class SubsetSum {
	
    public boolean canPartition(int[] nums) {
    	int sum = 0;
    	for (int i: nums){
    		sum += i;
    	}
    	if (sum %2 != 0){
    		return false;
    	}
    	boolean p[] = new boolean[sum/2+1];
    	p[0] = true;
    	int max = 0;
    	for (int i : nums){
    		if (i > sum /2){
    			return false;
    		}
    		for (int j = 0; j<= max; j++){
    			if (p[j] && (j+i) <= sum/2){
    				p[j+i] = true;
    				max = Math.max(max, j+i);
    				if (max == sum/2){
    					return true;
    				}
    			}
    		}
    	}
    	return p[sum/2];
//        int sum = 0;
//        for(int n:nums) sum += n;
//        if(1==(sum&1)) return false;
//        int v = (sum>>1);
//        boolean[] dp = new boolean[v+1];
//        dp[0] = true;
//        for(int n:nums){
//            for(int i=v; i>=n; --i) dp[i] |= dp[i-n];
//            if(dp[v]) return true;
//        }
//        return dp[v];
        
    }
    
    public boolean canPartition1(int[] nums) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int sum = 0;
        for(int i : nums){
            if(map.containsKey(i)){
                map.put(i, map.get(i) + 1);
            }else{
                map.put(i, 1);
            }
            sum += i;
        }
        if(sum % 2 == 1) return false;
        return helper(map, sum / 2);
    }
    
    private boolean helper(Map<Integer, Integer> map, int target){
        if(map.containsKey(target) && map.get(target) > 0) return true;
        /*dfs*/
        for(int key : map.keySet()){
            if(key < target && map.get(key) > 0){
                map.put(key, map.get(key) - 1);
                if(helper(map, target - key)) return true;
                map.put(key, map.get(key) + 1);
            }
        }
        return false;
    }

}

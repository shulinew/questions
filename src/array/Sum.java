package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

public class Sum {
	/*
	 * Given an array S of n integers, find three integers in S such that the sum is closest 
	 * to a given number, target. Return the sum of the three integers. You may assume that 
	 * each input would have exactly one solution.
	 */
    public int threeSumClosest(int[] nums, int target) {
    	Arrays.sort(nums);
    	int diff  = Integer.MAX_VALUE;
    	int result = 0;
    	for (int i = 0; i < nums.length-2; i++){
    		int low = i+1;
    		int high = nums.length - 1;
    		while (low < high){
    			int sum = nums[i] + nums[low] + nums[high];
    			if (Math.abs(sum - target) < diff){
    				diff = Math.abs(sum - target);
    				result = Math.abs(sum - target);
    			}
    			if (sum - target > 0 ){
    				high --;
    			} else{
    				low ++;
    			}
     		}
    	}
    	return result;
        
    }
    
    /*
     * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0?
     *  Find all unique triplets in the array which gives the sum of zero.
     */
    public List<List<Integer>> threeSum(int[] nums) {
    	List<List<Integer>> result = new ArrayList<List<Integer>>();
    	for (int i = 0; i < nums.length-2; i++){
    		for (int j = i+1; j< nums.length; j++){
    			int twoSum = nums[i] + nums[j];
    			for (int k = j+1; k< nums.length;k++){
    				int threeSum = twoSum + nums[k];
    				if (threeSum == 0){
    					List<Integer> oneResult = new ArrayList<Integer>();
    					oneResult.add(nums[i]);
    					oneResult.add(nums[j]);
    					oneResult.add(nums[k]);
    					result.add(oneResult);
    					
    				}
    			}
    		}
    	}
    	return result;
    }
    
    
    public List<List<Integer>> threeSum1(int[] nums){
        Arrays.sort(nums);
        List<List<Integer>> res = new LinkedList<List<Integer>>(); 
        for (int i = 0; i < nums.length-2; i++) {
            if (i == 0 || (i > 0 && nums[i] != nums[i-1])) {
                int lo = i+1, hi = nums.length-1, sum = 0 - nums[i];
                while (lo < hi) {
                    if (nums[lo] + nums[hi] == sum) {
                        res.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
                        while (lo < hi && nums[lo] == nums[lo+1]) lo++;
                        while (lo < hi && nums[hi] == nums[hi-1]) hi--;
                        lo++;
                        hi--;
                    } else if (nums[lo] + nums[hi] < sum) {
                    	lo++;
                    }  else 
                    	hi--;
               }
            }
        }
        return res;
    }
    
    /*
     * Given an array S of n integers, are there elements a, b, c, and d in S such that 
     * a + b + c + d = target? Find all unique quadruplets in the array which gives the
     *  sum of target.Elements in a quadruplet (a,b,c,d) must be in non-descending order. 
     *  (ie, a ≤ b ≤ c ≤ d)
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
    	Arrays.sort(nums);
    	List<List<Integer>> result = new LinkedList<List<Integer>>();
    	if (nums.length < 4)
    		return result;
        for (int i = 0; i < nums.length - 3; i++){
        	if (i > 0 && nums[i] == nums[i-1])
        		continue;
        	for (int j = i + 1; j < nums.length - 2;j++){
        		if (j > i+1 && nums[j] == nums[j-1])
        			continue;
        		int low = j+1;
        		int high = nums.length - 1;
        		while (low < high){
        			int sum = nums[i] + nums[j] + nums[low] + nums[high];
        			if (sum == target){
        				result.add(Arrays.asList(nums[i], nums[j], nums[low], nums[high]));
        				while (low < high && nums[low] == nums[low+1]){
        					low ++;
        				}
        				while (low <high && nums[high] == nums[high-1]){
        					high--;
        				}
        			}else if (sum > target){
        				while (low < high && nums[high] == nums[high-1]){
        					high--;
        				}
        				high --;
        			}else{
        				while (low < high && nums[low] == nums[low+1]){
        					low ++;
        				}
        				low++;
        			}
        		}
        	}
        }
        return result;
        
        
        
    }
    
    class Pair {
        int a;
        int ai;
        int b;
        int bi;

        public Pair(int a, int ai, int b, int bi){
            this.a = a;
            this.ai = ai;
            this.b = b;
            this.bi = bi;
        }

        boolean same(Pair p){
            return p != null && p.a == a && p.b == b;
        }
    }

    public List<List<Integer>> fourSum2(int[] num, int target) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(num.length < 4){
            return res;
        }
        Arrays.sort(num);
        TreeMap<Integer, List<Pair>> map = new TreeMap<Integer, List<Pair>>();
        for(int i = 0; i < num.length; i++){
            for(int j = i + 1; j < num.length; j++){
                Pair pair = new Pair(num[i], i, num[j], j);
                int sum = num[i] + num[j];
                List<Pair> list;
                if(map.containsKey(sum)){
                    list = map.get(sum);
                }
                else{
                    list = new ArrayList<Pair>();
                    map.put(sum, list);
                }
                list.add(pair);
            }
        }
        Integer first = map.firstKey();
        Integer last = map.lastKey();
        while(first != null && last != null && first <= last){
            if(first + last > target){
                last = map.lowerKey(last);
            }
            else if(first + last < target){
                first = map.higherKey(first);
            }
            else if(first == last){
                List<Pair> list = map.get(first);
                for(int i = 0; i < list.size(); i++){
                    Pair a = list.get(i);
                    if(a.a == a.b){
                        for(int j = i + 1; j < list.size(); j++){
                            Pair b = list.get(j);
                            if(b.a == b.b){
                                if(a.bi < b.ai){
                                    res.add(Arrays.asList(new Integer[]{a.a, a.b, b.a, b.b}));
                                    break;
                                }
                            }
                            else{
                                break;
                            }
                        }
                        break;
                    }
                }
                last = map.lowerKey(last);
                first = map.higherKey(first);
            }
            else{
                Pair lastA = null;
                for(Pair a : map.get(first)){
                    if(a.same(lastA)){
                        continue;
                    }
                    lastA = a;
                    Pair lastB = null;
                    for(Pair b: map.get(last)){
                        if(a.bi < b.ai){
                            if(b.same(lastB)){
                                continue;
                            }
                            lastB = b;
                            res.add(Arrays.asList(new Integer[]{a.a, a.b, b.a, b.b}));
                        }
                    }
                }
                last = map.lowerKey(last);
                first = map.higherKey(first);
            }
        }
        return res;
    }
    

}

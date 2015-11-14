package array;

import java.util.ArrayList;
import java.util.List;


public class MajorElement {
	/*
	 * Given an integer array of size n, find all elements that appear more than n/3 times. 
	 * The algorithm should run in linear time and in O(1) space.
	 */
    public List<Integer> majorityElement(int[] nums) {
    	List<Integer> result = new ArrayList<Integer>();
    	int candidate1 = 0, candidate2 = 0, count1 = 0, count2 = 0;
    	for (int i = 0 ;i<nums.length;i++){
    		int candidate = nums[i];
    		if (count1 > 0 && count2 > 0){
    			if (candidate!= candidate1 && candidate!= candidate2){
    				count1--;
    				count2--;
    			} else if (candidate == candidate1){
    				count1++;
    			}else{
    				count2++;
    			}
    		} else if (count1 > 0){
    			if (candidate == candidate1){
    				count1++;
    			} else{
    				candidate2 = candidate;
    				count2++;
    			}
    		}else if (count2 > 0){
    			if (candidate == candidate2){
    				count2++;
    			} else{
    				candidate1 = candidate;
    				count1++;
    			}
    		} else{
				candidate1 = candidate;
				count1++;
    		}
    	}
    	count1 = count2 = 0;
    	for (int i = 0; i< nums.length;i++){
    		if (nums[i] == candidate1){
    			count1++;
    		}else if (nums[i] == candidate2){
    			count2++;
    		}
    	}
    	if (count1 > nums.length/3){
    		result.add(candidate1);
    	}
    	if (count2 > nums.length/3){
    		result.add(candidate2);
    	}
    	return result;
    	
        
    }
    /*
     * Given an integer array of size n, find all elements that appear more than n/2 times. 
     * The algorithm should run in linear time and in O(1) space.
     */
    public int majorityElement1(int[] nums) {
        int major = 0;
        int count = 0;
        for (int i = 0; i< nums.length;i++){
            if (count == 0){
                major = nums[i];
                count++;
            }else if (major == nums[i]){
                count++;
            }else{
                count--;
            }
        }
        return major;
    }

}

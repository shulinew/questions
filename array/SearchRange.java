package array;

/*
 * Given a sorted array of integers, find the starting and ending position of a given target value.
	Your algorithm's runtime complexity must be in the order of O(log n).
	If the target is not found in the array, return [-1, -1].
	
	For example,
	Given [5, 7, 7, 8, 8, 10] and target value 8,
	return [3, 4]. 
 */
public class SearchRange {
    public int[] searchRange(int[] nums, int target) {
    	int[] range = new int[]{-1, -1};
    	int i = 0;
    	int j = nums.length - 1;
    	
    	while (j > i){
    		int mid = (i + j)/2;
    		if (nums[mid] < target){
    			i = mid + 1;
    		} else {
    			j = mid;
    		}
    	}
    	if (nums[i] == target){
    		range[0] = i;
    		
	    	i = 0;
	    	j = nums.length - 1;
	    	while (j > i){
	    		int mid = (i+j)/2 + 1;
	    		if (nums[mid] > target){
	    			j = mid -1;
	    		}else{
	    			i = mid;
	    		}
	    	}
	    	if (nums[j] == target){
	    		range[1] = j;
	    	}
    	}
    	return range;      
    }


}

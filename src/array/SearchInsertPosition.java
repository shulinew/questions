package array;

/*
 * Given a sorted array and a target value, return the index if the target is found. If not, 
 * return the index where it would be if it were inserted in order.You may assume no duplicates
 *  in the array.
 */
public class SearchInsertPosition {
    public int searchInsert(int[] nums, int target) {
        int i = 0;
        int j = nums.length - 1;
        while (j >= i){
        	int mid = (i+j)/2;
        	if (nums[mid] == target){
        		return mid;
        	}else if (nums[mid] > target){
        		j = mid - 1;       		
        	}else{
        		i = mid + 1;
        	}
        }
        int pos = 0;
        if (j >= 0){
        	pos = j;
        }
        if (nums[pos] > target){
        	return pos-1 >=0? pos-1:0;
        }else if (nums[pos] < target){
        	return pos+1;
        }else{
        	return pos;
        }
    }
    
    public int searchInsertPos(int[] nums, int target){
    	int low = 0;
    	int high = nums.length - 1;
    	while(low <= high){
    		int mid = (high + low) /2;
    		if (nums[mid] == target){
    			return mid;
    		}else if (nums[mid] > target){
    			high = mid -1;
    		}else{
    			low = mid +1;
    		}
    	}
    	return low;
    }
}

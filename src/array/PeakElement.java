package array;

/*
 * A peak element is an element that is greater than its neighbors.
	Given an input array where num[i] != num[i+1], find a peak element and return its index.
	The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
	You may imagine that num[-1] = num[n] = .
	For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.
 */
public class PeakElement {
    public int findPeakElement(int[] nums) {
        if (nums.length != 1){
         	for (int i = 0; i< nums.length ;i++){
        		if (i == 0 && nums[i]> nums[i+1]){
        			return i;
        		}
        		if (i == nums.length -1 && nums[i] > nums[i-1])
        			return i;
        		if (i > 0 && i < nums.length && nums[i] > nums[i-1] && nums[i] > nums[i+1]){
        			return i;
        		}
        	}
        }
        return 0; 
    }
    
    public int findPeakElement1(int[] nums) {
    	int  peak = findPeakRecursive(nums, 0, nums.length);
    	return peak;
    	
    }
    private int findPeakRecursive(int[] nums, int start, int end){
    	if (start == end)
    		return start;
    	int pos = (end + start) /2;
    	if (nums[pos] > nums[pos +1])
    		return findPeakRecursive(nums, start, pos);
    	else
    		return findPeakRecursive(nums, pos+1, end);
    	
    }

}

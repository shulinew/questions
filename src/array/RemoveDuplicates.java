package array;

import java.util.Arrays;

/*
 *  Follow up for "Remove Duplicates":
	What if duplicates are allowed at most twice?
	For example,
	Given sorted array nums = [1,1,1,2,2,3],
	Your function should return length = 5, with the first five elements of nums being 
	1, 1, 2, 2 and 3. It doesn't matter what you leave beyond the new length. 
 */
public class RemoveDuplicates {
    public int removeDuplicates(int[] nums) {
    	int j = 1;
    	boolean maybeRemoved = false;
    	for (int i = 1;i<nums.length;i++){
    		if (nums[i] == nums[i-1]){
    			if (!maybeRemoved){
    				nums[j++] = nums[i];
    				maybeRemoved = true;
    			}
    		}else{
    			maybeRemoved = false;
    			nums[j++] = nums[i];
    		}
    	}
    	return j;
        
    }
    
    public int removeDuplicate2(int[] nums){
        int i = 0;
        for (int n : nums)
            if (i < 2 || n > nums[i-2])
                nums[i++] = n;
        return i;
    }
    public static void main(String[] args){
    	int [] test = {1,1,1,2};
    	RemoveDuplicates remove = new RemoveDuplicates();
    	remove.removeDuplicates(test);
    }

}

package array;
/*
 *  Given an array nums, write a function to move all 0's to the end of it while maintaining 
 *  the relative order of the non-zero elements.
 *  For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be 
 *  [1, 3, 12, 0, 0]
 */
public class MoveZeros {
    public void moveZeroes(int[] nums) {
//        int end = nums.length -1;
//        for (int i = 0; i< nums.length;i++){
//        	if (i <= end){
//	        	if (nums[i] == 0){
//	        		for (int j = i+1;j<nums.length; j++){
//	        			nums[j-1] = nums[j];
//	        		}
//	        		nums[end--] = 0;
//	        		i--;
//	        	}
//        	}
//        }
    	   if (nums == null || nums.length == 0) return;        

    	    int insertPos = 0;
    	    for (int num: nums) {
    	        if (num != 0) nums[insertPos++] = num;
    	    }        

    	    while (insertPos < nums.length) {
    	        nums[insertPos++] = 0;
    	    }
    }
    
    public static void main (String [] args){
    	int [] test = {1,2,0,12};
    	MoveZeros re = new MoveZeros();
    	re.moveZeroes(test);
    }

}

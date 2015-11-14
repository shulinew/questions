package array;


/*
 * Rotate an array of n elements to the right by k steps.
	For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] 
	is rotated to [5,6,7,1,2,3,4]
 */

public class RotateArray {
    public static void rotate(int[] nums, int k) {
        int length = nums.length;
        int step = k%length;
        int [] result = new int[step];
        for (int m = 0;m<step;m++){
        	result[m] = nums[length-step+m];
        }
        for (int j = length - 1;j >=step;j--){
        	nums[j] = nums[j-step];
        }
        for (int l = 0;l<step;l++){
        	nums[l] = result[l];
        }
    }
    public static void rotate1(int[] nums, int k) {
    	
    }
    public static void main(String[] args){
    	int[] test = {1,2};
    	RotateArray.rotate(test,1);
    }
}

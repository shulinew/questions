package array;

/*
 *  Given an array with n objects colored red, white or blue, sort them so that objects of the same 
 *  color are adjacent, with the colors in the order red, white and blue. Here, we will use the 
 *  integers 0, 1, and 2 to represent the color red, white, and blue respectively. 
 */
public class SortColors {
    public void sortColors(int[] nums) {
        int second=nums.length-1, zero=0;
        for (int i=0; i<=second; i++) {
            while (nums[i]==2 && i<second){
            	int temp = nums[i];
            	nums[i] = nums[second];
            	nums[second] = temp;
            	second--;
            	
            }
            while (nums[i]==0 && i>zero){
            	int temp = nums[i];
            	nums[i] = nums[zero];
            	nums[zero] = temp;
            	zero++;
            }
        }
    }
}

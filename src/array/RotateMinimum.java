package array;

/*
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 */
public class RotateMinimum  {
    public int findMin(int[] nums) {
    	int[] subMums = nums;
    	int min = findMin(subMums, 0, nums.length-1);
    	return min;
    }
    private int findMin(int[] subs, int start, int end){
    	if (start == end)
    		return subs[start];
    	if (start + 1 == end){
    		return Math.min(subs[start], subs[end]);
    	}
    	int pos = (end-start+1)/2;
    	int minA = findMin(subs, start, start+pos);
    	int minB = findMin(subs, start+pos+1, end);
    	return Math.min(minA, minB);
    }
    /*
     * The minimum element must satisfy one of two conditions: 1) If rotate, A[min] < A[min - 1]; 2) If not, A[0]. 
     * Therefore, we can use binary search: check the middle element, if it is less than previous one, then it is minimum. 
     * If not, there are 2 conditions as well: If it is greater than both left and right element, then minimum element 
     * should be on its right, otherwise on its left.
     */
    
    public static void main(String[] args){
    	int[] test = {4,5,6,7,0,1,2};
    	RotateMinimum mim = new RotateMinimum();
    	System.out.println(mim.findMin(test));
    }
}

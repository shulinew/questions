package array;


/*
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.
 */
public class SearchRotateArray {
//    public boolean search(int[] nums, int target) {
//        return binarySearch(nums, 0, nums.length-1, target);
//    }
//    
//    private boolean binarySearch(int[] nums, int start, int end, int target){
//    	int midPos = (end+start)/2;
//    	if (start > end)
//    		return false;
//    	if (nums[midPos] == target)
//    		return true;
//    	if (nums[midPos] > target){
//    		if (nums[midPos] > nums[midPos +1]){
//    			return binarySearch(nums, midPos+1, end, target);
//    		}else
//    			return binarySearch(nums, start, midPos-1, target);
//    	} else{
//    		if (nums[midPos] < nums[midPos+1]){
//    			return binarySearch(nums, midPos+1, end, target);
//    		}else{
//    			return binarySearch(nums, start, midPos-1, target);
//    		}
//    	}
//    }
	
	/*
	 *         int lo=0,hi=n-1;
        // find the index of the smallest value using binary search.
        // Loop will terminate since mid < hi, and lo or hi will shrink by at least 1.
        // Proof by contradiction that mid < hi: if mid==hi, then lo==hi and loop would have been terminated.
        while(lo<hi){
            int mid=(lo+hi)/2;
            if(A[mid]>A[hi]) lo=mid+1;
            else hi=mid;
        }
        // lo==hi is the index of the smallest value and also the number of places rotated.
        int rot=lo;
        lo=0;hi=n-1;
        // The usual binary search and accounting for rotation.
        while(lo<=hi){
            int mid=(lo+hi)/2;
            int realmid=(mid+rot)%n;
            if(A[realmid]==target)return realmid;
            if(A[realmid]<target)lo=mid+1;
            else hi=mid-1;
        }
        return -1;
	 */
    
    public int search(int[] nums, int target) {
        return binarySearch(nums, 0, nums.length-1, target);
    }
    
    private int binarySearch(int[] nums, int start, int end, int target){
    	int midPos = (end+start)/2;
    	if (start > end || (start == end && nums[start] != target))
    		return -1;
    	if (nums[midPos] == target )
    		return midPos;
		int result = binarySearch(nums, midPos+1, end, target);
		if( result == -1)
			return binarySearch(nums, start, midPos-1, target);
		return result;
    }
    public static void main (String[] args){
    	SearchRotateArray test = new SearchRotateArray();
    	int[] arr = {3,4,5,6,1,2};
    	System.out.println(test.search(arr, 2));
    }

}

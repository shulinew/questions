package array;

import java.util.ArrayList;
import java.util.List;

/*
 *  Given a sorted integer array without duplicates, return the summary of its ranges.
	For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"]
 */
public class SummaryRanges {
    public List<String> summaryRanges(int[] nums) {
    	List<String> result = new ArrayList<String>();
    	
    	if (nums.length == 1){
    		result.add(String.valueOf(nums[0]));
    		return result;
    	}
    	for (int i = 0; i< nums.length;i++){
    		int num = nums[i];
    		while(i+1<nums.length && nums[i]+1 == nums[i+1]){
    			i++;
    		}
    		
    		if (num != nums[i]){
    			result.add(num+"->"+nums[i]);
    		}else{
    			result.add(num+"");
    		}
    	}
    	return result;
        
    }

}

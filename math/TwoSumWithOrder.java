package math;

import java.util.HashMap;
import java.util.Map;

public class TwoSumWithOrder {
    public int[] twoSumWithOrder(int[] numbers, int target) {
    	int [] indices = new int[2];
    	int head = 0;
    	int tail = numbers.length-1;
    	int min = target - numbers[tail];
    	while (numbers[head] < min) head++;
    	int max = target - numbers[0];
    	while(numbers[tail] > max) tail--;
    	while (head < tail){
    		if (numbers[head] + numbers[tail] == target){
    			indices[0] = head+1;
    			indices[1] = tail+1;
    			return indices;
    		}else if (numbers[head] < target - numbers[tail]) {
    			head++;
    		}else{
    			tail--;
    		}
    		
    	}
    	return indices;
        
    }
    
    public int[] twoSum(int[] nums, int target) {
    	int [] indices = new int[2];
    	Map<Integer,Integer> map = new HashMap<Integer,Integer>();
    	for (int i=0;i<nums.length;i++){
    		int second = target - nums[i];
    		if (map.containsKey(nums[i])){
    			int temp = map.get(nums[i]);
				indices[0] = temp;
				indices[1] = i;
    		} else{
    			map.put(second, i);
    		}
    	}
    	return indices; 
    }
    public static void main(String[] args){
    	TwoSumWithOrder wp = new TwoSumWithOrder();
    	int[] test = {3,2,4};
    	System.out.println(wp.twoSum(test, 6));
    	int people[][]={{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}};
    	System.out.println(people[0].length);
    	for (int  i = 0; i<people.length;i++){
    		for (int j = 0;j<people[0].length;j++){
    			System.out.print(people[i][j]);
    		}
    		System.out.print("\n\t");
    	}
    }

}

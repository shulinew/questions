package array;

import java.util.Random;

public class ShuffleArray {
	private int[] nums;
	private Random random;
	
	public ShuffleArray(int[] nums){
		this.nums = nums;
		random = new Random();
	}
    public int[] reset() {
        return this.nums;
    }
    public int[] shuffle() {
        int[] tempArr = nums.clone();
        for (int i = 1; i< nums.length;i++){
	        int randomIndex = random.nextInt(i+1);
	        int temp = tempArr[randomIndex];
	        tempArr[randomIndex] = tempArr[i];
	        tempArr[i] = temp;
        }
        return tempArr;
    }
}

package dynamicProgramming;

public class NumArray {
	int[] left;

    public NumArray(int[] nums) {
       left = new int[nums.length+1];
       int count=0;
       for(int i:nums){
           left[count +1] = left[count++] + i;
       }
    }

    public int sumRange(int i, int j) {
        return left[j+1] - left[i];
    }
}
/*
public class NumArray1 {
    
    private int[] sum;

    public NumArray1(int[] nums) {
        sum = nums;
        //sum[i] = sum[0] + sum[1] + ... + sum[i]
        for(int i=1; i<nums.length; i++) {
            sum[i] += sum[i-1];
            System.out.println(sum[i]);
        }
    }

    public int sumRange(int i, int j) {
        if(i == 0) return sum[j];
        return sum[j]-sum[i-1];
    }
}
*/
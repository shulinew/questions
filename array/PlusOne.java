package array;

import java.util.Arrays;

/*
 * Given a non-negative number represented as an array of digits, plus one to the number.
	The digits are stored such that the most significant digit is at the head of the list.
 */

public class PlusOne {
    public int[] plusOne(int[] digits) {
    	int[] result = new int[digits.length+1];
    	int carryOn = 0;
    	int add = 1;
        for (int i = digits.length -1; i>=0;i--){
        	int sum = add + carryOn + digits[i];
        	if (sum >= 10){
        		result[i] = (sum) % 10;
        		carryOn = 1;
        	}else{
        		result[i] = sum;
        		carryOn = 0;
        	}
        	add = 0;
        }
        if (carryOn != 0){
        	result[0] = 1;
        }else {
        	result = Arrays.copyOf(result, digits.length);
        }
        return result;
    }
    public static void main (String [] args){
    	int [] test = {8,9,9,9};
    	PlusOne re = new PlusOne();
    	re.plusOne(test);
    }

}

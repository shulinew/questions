package math;

/*
 * Write an algorithm to determine if a number is "happy". A happy number is a number defined by the
 *  following process: Starting with any positive integer, replace the number by the sum of the squares 
 *  of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops 
 *  endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy 
 *  numbers.
 */
public class HappyNumber {
    public boolean isHappy(int n) {
        int temp = 0;
        while (true) {
            temp += Math.pow(n % 10, 2);
            if (n >= 10)
                n = n / 10;
            else if (n < 10) {
                if (temp == 1) return true;
                else if (temp == 2 || temp == 3 || temp == 4) return false;
                else if (temp > 4 && temp < 10) n = (int) Math.pow(temp, 2);
                else n = temp;
                temp = 0;
            }
        } 
    }
    public boolean isHappyNumber(int n){
    	while (n != 1 && n != 4){
    		int sum = 0;
    		while (n != 0){
    			sum += Math.pow(n%10, 2);
    			n = n/10;
    		}
    		n = sum;
    		sum = 0;
    	}
    	return (n == 1);
    }
    public boolean isHappyNumber1(int n){
    	int slow, fast;
    	slow = fast = n;
    	do {
    		slow = sum(n);
    		fast = sum(n);
    		fast = sum(n);
    	}while (slow != fast);
    	return slow == 1;
    	
    }
    private int sum(int n){
    	int sum = 0;
    	while (n != 0){
    		sum += Math.pow(n%10, 2);
    		n = n/10;
    	}
    	return sum;
    }

}

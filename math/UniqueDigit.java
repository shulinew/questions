package math;

/*
 * Given a non-negative integer n, count all numbers with unique digits, x, where 0 ≤ x < 10^n.
 */
public class UniqueDigit {
    public int countNumbersWithUniqueDigits(int n) {
    	if (n == 0) return 1;
    	int count = 10;
    	int uniqueDigits = 9;
    	int range = (n >= 11)?10:n;
    	int availableDigits = 10;
    	for (int i = 2; i<= range;i++){
    		uniqueDigits= uniqueDigits * --availableDigits;
    		count+= uniqueDigits;
    	}

    	return count;
    }
}

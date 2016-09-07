package math;

public class NumberOfOne {
    public int hammingWeight(int n) {
    	int count = 0;
    	while (n != 0){
    		count+= n & 1;
    		n = n >>>1;
    	}
        return count;
    }
    public int hammingWeight2(int n) {
        int count = 0;
        for (;n!=0;n = n & (n-1))
            count++;
        return count;
    }
}

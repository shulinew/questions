package math;

/*
 *  Given an array of integers A and let n to be its length.
	Assume Bk to be an array obtained by rotating the array A k positions clock-wise, we define a 
	"rotation function" F on A as follow:
	F(k) = 0 * Bk[0] + 1 * Bk[1] + ... + (n-1) * Bk[n-1].
	Calculate the maximum value of F(0), F(1), ..., F(n-1). 
	
	A = [4, 3, 2, 6]
	F(0) = (0 * 4) + (1 * 3) + (2 * 2) + (3 * 6) = 0 + 3 + 4 + 18 = 25
	F(1) = (0 * 6) + (1 * 4) + (2 * 3) + (3 * 2) = 0 + 4 + 6 + 6 = 16
	F(2) = (0 * 2) + (1 * 6) + (2 * 4) + (3 * 3) = 0 + 6 + 8 + 9 = 23
	F(3) = (0 * 3) + (1 * 2) + (2 * 6) + (3 * 4) = 0 + 2 + 12 + 12 = 26
	
	So the maximum value of F(0), F(1), F(2), F(3) is F(3) = 26.
 */

/*
 * Consider we have 5 coins A,B,C,D,E

According to the problem statement
F(0) = (0A) + (1B) + (2C) + (3D) + (4E)
F(1) = (4A) + (0B) + (1C) + (2D) + (3E)
F(2) = (3A) + (4B) + (0C) + (1D) + (2*E)

This problem at a glance seem like a difficult problem. I am not very strong in mathematics, so this is how I visualize this problem

We can construct F(1) from F(0) by two step:
Step 1. taking away one count of each coin from F(0), this is done by subtracting "sum" from "iteration" in the code below
after step 1 F(0) = (-1A) + (0B) + (1C) + (2D) + (3*E)

Step 2. Add n times the element which didn't contributed in F(0), which is A. This is done by adding "A[j-1]len" in the code below.
after step 2 F(0) = (4A) + (0B) + (1C) + (2D) + (3E)

At this point F(0) can be considered as F(1) and F(2) to F(4) can be constructed by repeating the above steps.

Hope this explanation helps, cheers!

int allSum = 0;
int len = A.length;
int F = 0;
for (int i = 0; i < len; i++) {
    F += i * A[i];
    allSum += A[i];
}
int max = F;
for (int i = len - 1; i >= 1; i--) {
    F = F + allSum - len * A[i];
    max = Math.max(F, max);
}
return max;   

 */
public class RotateFunction {
    public int maxRotateFunction(int[] A) {
    	if (A == null || A.length == 0) return 0;
    	int max = Integer.MIN_VALUE;
    	for (int i = 0;i<A.length;i++){
    		int sum = sumRotateFunction(A, i);
    		max = Math.max(max, sum);
    	}
        return max;
    }
    private int sumRotateFunction(int[] A, int k){
    	int sum = 0;
    	for (int i = 0;i < A.length;i++){
    		int pos = (A.length - k + i) % A.length;
    		sum += A[pos] * i;
    	}
    	return sum;
    }
    
    
}

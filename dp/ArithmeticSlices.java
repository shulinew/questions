/*
A sequence of number is called arithmetic if it consists of at least three elements and if the difference between any two consecutive 
elements is the same.

For example, these are arithmetic sequence:

1, 3, 5, 7, 9
7, 7, 7, 7
3, -1, -5, -9

The following sequence is not arithmetic.

1, 1, 2, 5, 7


A zero-indexed array A consisting of N numbers is given. A slice of that array is any pair of integers (P, Q) such that 0 <= P < Q < N.

A slice (P, Q) of array A is called arithmetic if the sequence:
A[P], A[p + 1], ..., A[Q - 1], A[Q] is arithmetic. In particular, this means that P + 1 < Q.

The function should return the number of arithmetic slices in the array A. 
A = [1, 2, 3, 4]

return: 3, for 3 arithmetic slices in A: [1, 2, 3], [2, 3, 4] and [1, 2, 3, 4] itself.
*/  

public Arithmeticslices {
    public int numberOfArithmeticSlices(int[] A) {
        int curr = 0, sum = 0;
        for (int i=2; i<A.length; i++)
            if (A[i]-A[i-1] == A[i-1]-A[i-2]) {
                curr += 1;
                sum += curr;
            } else {
                curr = 0;
            }
        return sum;
    }
    /*
    trying to find the relationship between f(n) and f(n - 1) when A[n] can be part of
     current arithmetic slice. then easy to find that if A[n] can be the end of the 
     current arithmetic slice, then the total number of arithmetic slices will be 
     incremented by the length of current slice(including A[n]) - 3 + 1;

e.g.
when 1 2 3 --> (1, 2, 3) increment is 3 - 3 + 1 = 1
when 1 2 3 4 --> (2, 3, 4), (1, 2, 3,4), increment is 4 - 3 + 1 = 2
when 1 2 3 4 5 --> (3, 4, 5), (2, 3, 4, 5), (1, 2, 3, 4, 5), increment is 5 - 3 + 1 = 3.

so the first step is to loop and store the length of arithmetic.
second loop is to added up all the increments.

e.g. [1 2 3 4 0 0 7 8 9]
first loop [0 0 3 4 0 0 0 0 3];
second loop sum = (3 - 3 + 1) + (4 - 3 + 1) + 0 + 0 + 0 + 0 + (3 - 3 + 1) = 4
*/
    public int numberOfArithmeticSlices1(int[] A) {
        if(A == null || A.length == 0) return 0;
        int[] index = new int[A.length];
        for(int i = 2; i < index.length; i++)
        {
        	if(A[i] - A[i - 1] == A[i - 1] - A[i - 2])
        	{
        		if(index[i - 1] == 0) index[i] = 3;
        		else index[i] = index[i - 1] + 1;
        	}
        	else index[i] = 0;
        }

        int sum = 0;
        for(int i = 0; i < index.length; i++)
        {
        	if(index[i] != 0)
        	{
        		sum += index[i] - 3 + 1;
        	}
        }
        return sum;
    }

}
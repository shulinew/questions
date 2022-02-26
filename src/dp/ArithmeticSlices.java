package dp;/*
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArithmeticSlices {

	public int numberOfArithmeticSlicesDP2D(int[] A) {
		int len = A.length;
		if (len < 3) return 0;
		int count = 0;
		// whether it is arithmetics for element i, i+j-1
		boolean[][] dp = new boolean[len][len];
		// minimum length is 3
		for (int i = 0; i < len-3+1; i++) {
			if (A[i+1] - A[i] == A[i+2] - A[i+1]) {
				dp[i][i+3-1] = true;
				count++;
			}
		}
		// start from length = 4
		for (int i = 4; i <= len; i++) {
			for (int j = 0; j < len-i+1; j++) {
				if (dp[j+1][j+i-1] && A[j+1] - A[j] == A[j+2] - A[j+1]) {
					dp[j][j+i-1] = true;
					count++;
				} else if (dp[j][j+i-2] && A[j+i-1] - A[j+i-2] == A[j+i-2] - A[j+i-3]) {
					dp[j][j+i-1] = true;
					count++;
				}

			}
		}
		return count;
	}

	//dp[i]=(A[i]-A[i-1] = = A[i-1]-A[i-2])? 1+dp[i-1] : 0
	public int numberOfArithmeticSlices1D(int[] A) {
		int len = A.length;
		if (len < 3) return 0;
		// count of arithmetics till i
		int[] dp = new int[len];
		dp[0] = 0;
		dp[1] = 0;
		int count = 0;
		for (int i = 2; i < len; i++) {
			if (A[i] - A[i-1] == A[i-1] - A[i-2]) {
				dp[i] = dp[i-1]+1;
			} else {
				dp[i] = 0;
			}
			count += dp[i];
		}
		return count;
	}
	public int numberOfArithmeticSlices(int[] A) {
		int count = 0;
		int current = 0;
		for (int i = 2; i < A.length; i++) {
			if (A[i-1] - A[i-2] == A[i] - A[i-1]) {
				current++;
				count += current;
			} else {
				current = 0;
			}
		}
		return count;
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

    public int numberOfArithmeticSlices2(int[] A) {
    	int len = A.length;
    	int[] dp = new int[len+1];
		for(int i = 4; i <= len; i++)
		{
			dp[i] = 2 * dp[i - 1] + 1 - dp[i - 2]; // formula
		}

		int n = 2, prev_diff = A[1] - A[0], count = 0;
		for(int i = 1; i < len - 1; i++)
		{
			if(A[i + 1] - A[i] == prev_diff) n++;
			else
			{
				count += dp[n];
				n = 2;
				prev_diff = A[i + 1] - A[i];

			}
		}
		if(n >= 3) count += dp[n];
		return count;
	}
	public int numberOfArithmeticSlices3(int[] A) {
		int ans = 0;
		if (A.length < 3) {
			return ans;
		}
		int count = 2;
		int lastdiff = A[1] - A[0];
		for (int i=2; i<A.length; i++) {
			int diff = A[i] - A[i-1];
			if (lastdiff == diff) {
				count++;
			} else {
				if (count >= 3) {
					ans+=(count-1)*(count-2)/2;
				}
				lastdiff = diff;
				count = 2;
			}
		}

		if (count >= 3) {
			ans += (count-1)*(count-2)/2;
		}
		return ans;
	}
	int sum = 0;
	public int numberOfArithmeticSlicesRecursive(int[] A) {
    	slices(A, A.length -1);
    	return sum;
	}
	private int slices(int[] A, int i) {
    	if (i < 2) return 0;
    	int currentCount = 0;
    	if (A[i] - A[i-1] == A[i-1] - A[i-2]) {
    		currentCount = 1 + slices(A, i-1);
    		sum = sum + currentCount;
		} else {
			slices(A, i -1);
		}
    	return currentCount;
	}
	//https://leetcode.com/problems/arithmetic-slices-ii-subsequence/solution/
	// Run the code again
	private int answer;
	public int numberOfArithmeticSlicesII(int[] A) {
		int answer = 0;
		List<Long> current = new ArrayList<Long>();
		dfs(0, A, current);
		return answer;
	}
	private void dfs(int i, int[] A, List<Long> current) {
		int len = A.length;
		if (i == len) {
			if (current.size() < 3) {
				return;
			}
			long difference = current.get(1) - current.get(0);
			for (int j = 1; i < current.size(); j++) {
				if (current.get(j) - current.get(j-1) != difference) {
					return;
				}
			}
			answer++;
			return;
		}
		dfs(i + 1, A, current);
		current.add((long)A[i]);
		dfs(i + 1, A, current);
		current.remove((long)A[i]);
	}

	public int numberOfArithmeticsSlicesIIDP(int[] A) {
		int len = A.length;
		long result = 0;
		Map<Integer, Integer>[] counts = new Map[len];
		for (int i = 0; i < len; i++) {
			counts[i] = new HashMap<Integer, Integer>(i);
			for (int j = 0; j < i; j++) {
				long delta = (long)A[i] - (long)A[j];
				if (delta < Integer.MIN_VALUE && delta > Integer.MAX_VALUE) continue;
				int diff = (int)delta;
				int sumj = counts[j].getOrDefault(diff, 0);
				int sumi = counts[i].getOrDefault(diff, 0);
				counts[i].put(diff, sumi + sumj + 1);
				result += sumj;
			}
		}
		return (int)result;
	}
}
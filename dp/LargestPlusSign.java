/*
 In a 2D grid from (0, 0) to (N-1, N-1), every cell contains a 1, except those cells in the given 
 list mines which are 0. What is the largest axis-aligned plus sign of 1s contained in the grid? 
 Return the order of the plus sign. If there is none, return 0.

An "axis-aligned plus sign of 1s of order k" has some center grid[x][y] = 1 along with 4 arms of 
length k-1 going up, down, left, and right, and made of 1s. This is demonstrated in the diagrams 
below. Note that there could be 0s or 1s beyond the arms of the plus sign, only the relevant area 
xof the plus sign is checked for 1s. 
Order 1:
000
010
000

Order 2:
00000
00100
01110
00100
00000

Order 3:
0000000
0001000
0001000
0111110
0001000
0001000
0000000

exp 2
Input: N = 5, mines = [[4, 2]]
Output: 2
Explanation:
11111
11111
11111
11111
11011
In the above grid, the largest plus sign can only be order 2.  One of them is marked in bold.

Input: N = 2, mines = []
Output: 1
Explanation:
There is no plus sign of order 2, but there is of order 1.
*/

public class LargestPlusSign {
    public int orderOfLargestPlusSign(int N, int[][] mines) {
    	int[][] memo = new int[N][N];
    	Set<Integer> bans = new HashSet<Integer>();
    	for (int [] mine: mines){
    		bans.add(mine[0]*N + mine[1]);
    	}
    	int count,result=0;
    	for (int row = 0; row < N; row++) {
    		count = 0;
    		for (int column = 0; column < N; column++) {
    			count = bans.contains(row*N + column) ? 0: ++count;
    			memo[row][column] = count;
    		}
    		count = 0;
    		for (int column = N-1; column >= 0; column--) {
    			count = bans.contains(row*N + column) ? 0: ++count;
    			memo[row][column] = Math.min(memo[row][column], count);
    		}
    	}
    	for (int column = 0; column < N; column++) {
    		count = 0;
    		for (int row = 0; row < N; row++) {
    			count = bans.contains(row*N + column)? 0: ++count;
    			memo[row][column] = Math.min(memo[row][column], count);
    		}
    		count = 0;
    		for (int row = N-1; row >= 0; row--) {
    			count = bans.contains(row*N + column)? 0: ++count;
    			memo[row][column] = Math.min(memo[row][column], count);
    			result = Math.max(memo[row][column], result);
    		}
    	}
    	return result;
    }
}
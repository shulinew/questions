package array;
/*
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
	The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
	How many possible unique paths are there?
 */
public class UniquePath {
    public int uniquePaths(int m, int n) {
    	
//    	   double cnt = 1;
//    	    int sum = (m - 1) + (n - 1);
//    	    int sml =  m < n  ? (m - 1) : (n - 1);
//    	    while(sml>0) cnt = cnt * sum-- / sml--;
    	/*
    	 * First of all you should understand that we need to do n + m - 2 movements : m - 1 down, n - 1 right, because we start from cell (1, 1).

Secondly, the path it is the sequence of movements( go down / go right), therefore we can say that two paths are different when there is i-th (1 .. m + n - 2) movement in path1 differ i-th movement in path2.

So, how we can build paths. Let's choose (n - 1) movements(number of steps to the right) from (m + n - 2), and rest (m - 1) is (number of steps down).

I think now it is obvious that count of different paths are all combinations (n - 1) movements from (m + n-2).


For the eg., given in question, 3x7 matrix, robot needs to take 2+6 = 8 steps with 2 down and 6 right in any order. That is nothing but a permutation problem. Denote down as 'D' and right as 'R', following is one of the path :-

D R R R D R R R

We have to tell the total number of permutations of the above given word. So, decrease both m & n by 1 and apply following formula:-

Total permutations = (m+n)! / (m! * n!)
    	 */

    	    return (int) Math.round(cnt);
        boolean[][] states = new int[m][n];
        int uniquePaths = 0;
        for (int i = 0; i< m;i++){
        	for (int j = 0; j< n;j++){
        		
        	}
        }
    }
    
    /*
     * Now consider if some obstacles are added to the grids. How many unique paths would there be?
		An obstacle and empty space is marked as 1 and 0 respectively in the grid.
		For example,
		There is one obstacle in the middle of a 3x3 grid as illustrated below.
     */

}

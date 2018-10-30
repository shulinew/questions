package math;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9,
 *  16, ...) which sum to n. 
 */
public class PerfectSquares {
	// 91ms
    public int numSquares(int n) {
    	if (n <= 0) 
    		return 0;

    	int [] countPerfectSquares = new int[n+1];
    	Arrays.fill(countPerfectSquares, Integer.MAX_VALUE);
    	countPerfectSquares[0] = 0;
    	for (int i = 1; i<= n; i++) {
    		for (int j = 1; j*j <=i; j++ ){
    			countPerfectSquares[i] = Math.min(countPerfectSquares[i], countPerfectSquares[i-j*j]+1);
    		}
    	}
        return countPerfectSquares[n+1];
    }
    // Static Dynamic Programming 93ms
    public int numSquares2(int n) {
    	if (n <= 0) return 0;
    	List<Integer>countPerfectSquares = new ArrayList<Integer>();
    	countPerfectSquares.add(0);
    	while(countPerfectSquares.size() <= n) {
    		int m = countPerfectSquares.size();
    		int countSquares = Integer.MAX_VALUE;
    		for (int i = 1; i*i <= m; i++) {
    			countSquares = Math.min(countSquares, countPerfectSquares.get(m-i*i)+1);
    		}
    		countPerfectSquares.add(countSquares);
    	}
    	return countPerfectSquares.get(n);
    }
    public int numSquares1(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i = 0; i <= n; i++){
            for(int j = 1; i + j * j <= n; j++){
                dp[i  + j * j] = Math.min(dp[i + j * j], dp[i] + 1);
             }
        }
        return dp[n];
    }
    private boolean isSquare(int num) {
    	int sqrt = (int) Math.sqrt(num);
    	return (sqrt * sqrt == num?true:false);
    }
    public int numSquaresMath(int n) {
    	if (isSquare(n)) 
    		return 1;
    	while((n & 3) == 0) {//n%4==0
    		n >>= 2;
    	}
    	if ((n & 7) == 7) { //n%8 == 7
    		return 4;
    	}
    	int sqrt = (int)Math.sqrt(n);
    	for (int i =1; i<= sqrt;i++){
    		if (isSquare(n - i*8)) {
    			return 2;
    		}
    	}
    	return 3;
    }
    public int numSquares4(int n) {
        int[] s = new int[n+1];
        for(int i=0;i<n+1;i++) s[i] = Integer.MAX_VALUE;
        //note to me: no need to store a list of perfect squares, knowing the square root of the largest perfect square is sufficient
        //List<Integer> squares = new ArrayList<Integer>();
        for(int i = 1;i<n+1;i++){
            int sqrt = (int) Math.sqrt(i);
            if(i == sqrt*sqrt){s[i] = 1;continue;}
            for(int j = 1;j<=sqrt;j++){
                if(s[i-j*j]+1<s[i]) s[i] = s[i-j*j]+1;
            }
        }
        return s[n];
    }
    
    public int numSquares5(int n) {
        ArrayDeque<Integer> queue = new ArrayDeque<Integer>();
        queue.add(n);
        // m is how many nodes in each level
        // nodesCount is children count of each node	
        int depth = 1, m = 1, nodesCount = 0;
        
        while(true){
            if(m == 0){
                depth++;
                m = nodesCount;
                nodesCount = 0;
            }
            
            int cur = queue.remove();
            m--;
            
            int l = (int) Math.sqrt(cur);
            for(int i=l; i>0; i--){
                int sq = i*i;
                int delta = cur - sq;
                if(delta == 0)
                    return depth;
                queue.add(delta);
                nodesCount++;
            }
        }
    }

}

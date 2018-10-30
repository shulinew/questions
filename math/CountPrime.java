package math;
/*
 * Count the number of prime numbers less than a non-negative number, n.
 */

public class CountPrime {
    
    public int countPrimes(int n){
    	boolean[] notPrimes = new boolean[n];
    	int count = 0;
    	for (int i=2; i <n;i++){
    		if (!notPrimes[i]){
    			count++;
    			for (int j = 2; i*j<n;j++){
    				notPrimes[i*j] = true;
    			}
    		}
    	}
    	return count;
    }
    public int countPrimes1(int n) {
        if (n < 3)
            return 0;
            
        boolean[] f = new boolean[n];
        int count = n / 2;
        for (int i = 3; i * i < n; i += 2) {
            if (f[i])
                continue;
            
            for (int j = i * i; j < n; j += 2 * i) {
                if (!f[j]) {
                    --count;
                    f[j] = true;
                }
            }
        }
        return count;
    }
}

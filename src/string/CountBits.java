package string;

public class CountBits {
    public int[] countBits(int num) {
    	int[] results = new int[num+1];
    	results[0] = 0;
    	if (num > 0){
	    	results[1] = 1;
	    	int power = 0;
	    	for (int i =2;i<=num;i++){
	    		if ((i&(i-1)) == 0){
	    			results[i] = 1;
	    			power = i;
	    		}else{
	    			int temp = i - power;
	    			results[i] = results[power] + results[temp];
	    		}
	    	}
    	}
    	return results;      
    }
    public int[] countBits1(int num) {
        int[] result = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            // Obviously, 
            // if i is even, then it should be i / 2 + "0"
            // if i is odd, it should be i / 2 + "1"
            // e.g.   8 --> 1000, 16 --> 1000 0, 17 --> 1000 1
            result[i] = result[i / 2] + i % 2;
        }
        return result;
    }
    public int[] countBits2(int num) {
        int[] bits = new int[num + 1];
        bits[0] = 0;
        for(int i = 1; i <= num; i++){
            bits[i] = bits[(i - 1) & i] + 1;
        }
        return bits;
    }
}

package math;

public class Power {
	/*
	 * Given an integer, write a function to determine if it is a power of two. 
	 */
    public boolean isPowerOfTwo(int n) {
    	int tmep = n>>1;
        if (n<<1 != 0){
        	return false;
        }
        return true;
    }
    public boolean isPowerOfTwo1(int n){
    	if(n==0) return false;
    	while(n%2==0) n/=2;
    	return (n==1);
    }
    public boolean isPowerOfTwo2(int n){
    	return n>0 && (1073741824 % n == 0);
    }
    public static void main(String[] args){
    	Power power = new Power();
    	power.isPowerOfTwo(8);
    }
    
    /*
     * Given an integer, write a function to determine if it is a power of three. 
     *     
     */
	public boolean isPowerOfThree(int n) {
		while (n != 0 && n%3==0){
			n = n/3;
		}
		return (n == 1);
	}
	public boolean isPowerOfThree1(int n) {
	    // 1162261467 is 3^19,  3^20 is bigger than int  
	    return ( n>0 &&  Math.pow(3, 19)%n==0);
	}
	public boolean isPowerOfThree2(int n) {
		if(n<=0) return false;
		double a = Math.log10(n)/Math.log10(3); //log power and base 3
		return (a-Math.floor(a))==0; //check after the decimal point.
	}
	public boolean isPowerOfFour(int n) {
		while (n != 0 && n%4==0){
			n = n/4;
		}
		return (n == 1);
	}
	
	//we need prove if n%2 =1, 2^n-1 % 3 != 0, because 2^n-2= 2(2^(n-1) -1),so 2^n - 1 % 3 = 1.
//	Second, all numbers above could be further categorized to 2 class. A: all numbers that are 2^(2k+1) and B: all numbers that 2^(2k).
//	Third, we could show that 2^(2k+1)-1 could not pass the test of (n-1)%3==0. (by induction) So all A are ruled out, leaving only B, which is power of 4.
	public boolean isPowerOfFour1(int n) {
		return n > 0 && (n&(n-1))==0 && (n-1)%3 == 0;
//	    return num > 0 && (num&(num-1)) == 0 && (num & 0x55555555) != 0;
	}

}

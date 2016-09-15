package string;

public class ReverseBits {
    public int reverseBits(int n) {
    	if (n==0) return 0;
    	int result = 0;
    	for (int i = 1;i<=32;i++){
    		result<<=1;
    		if ((n&1) == 1)
    			result++;
    		n = n>>1;
    	}
        return result;
    }
    public int reverseBits1(int n) {
    	int[] lookup = { 0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15 };
        int output = 0;
        for (int i=0; i<8; ++i) {
            output <<= 4;
            output |= lookup[n & 0xf];
            n >>= 4;
        }
        return output;
    }
    public static void main (String[] args){
    	String result = "test";
    	result = result.substring(0, result.length()-1);
    	ReverseBits rb = new ReverseBits();
    	rb.reverseBits(3);
    }

}

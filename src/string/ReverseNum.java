package string;

public class ReverseNum {
    public int reverse(int x) {
        if (x == 0) return x;
        boolean isNegative = (x < 0)?true:false;
    	StringBuffer sb = new StringBuffer();
        while (x != 0){
        	sb.append(Math.abs(x%10));
        	x = x/10;
        }
       Long result = new Long(sb.toString());
       if (result > Integer.MAX_VALUE || (result * -1) < Integer.MIN_VALUE)
    	   return 0;
       if (isNegative){
    	   result = 0 - result;
       }
       return Integer.parseInt(result.toString());
    }
    public int reverse1(int x){
    	int result = 0;
    	while (x != 0){
    		int tail = x %10;
    		int newResult = (result *10) + tail;
    		//overflow
    		if ((newResult - tail) / 10 != result){
    			return 0;
    		}
    		result = newResult;
    		x = x /10;
    	}
    	return result;
    }
    public static void main(String[] args){
    	ReverseNum rn = new ReverseNum();
    	rn.reverse(-123);
    }

}

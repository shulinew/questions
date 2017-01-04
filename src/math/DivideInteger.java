package math;


/*
 *  Divide two integers without using multiplication, division and mod operator.
If it is overflow, return MAX_INT. 
 */
public class DivideInteger {
	public int divide(int dividend, int divisor){
		long result = 0;
		if (divisor == 0) return Integer.MAX_VALUE;
		int sign = 1;
		long absDividend = (long)dividend;
		long absDivisor = (long)divisor;
		if ((dividend > 0 && divisor < 0) || dividend < 0 && divisor > 0){
			sign = -1;
		}

		absDivisor = Math.abs((long)divisor);
		absDividend = Math.abs((long)dividend);

		while(absDividend >= absDivisor){
			long temp = absDivisor;
			long count = 1;
			while (temp <= absDividend){
				temp <<= 1;
				count <<= 1;
			}
			result += count >> 1;
			absDividend -= temp >>1;
		}
		return (result * sign) >= Integer.MAX_VALUE ? Integer.MAX_VALUE:(int)result * sign;
	}
    public static void main(String [] strs){
    	DivideInteger rs = new DivideInteger();
    	System.out.println(rs.divide(-2147483648, -1));
    }
}

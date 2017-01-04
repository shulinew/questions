package math;
/*
 * Given an integer, convert it to a roman numeral.

Input is guaranteed to be within the range from 1 to 3999.
 */
public class IntToRoman {
	public String intToRoman(int i){
		String[] M = {"","M","MM","MMM"};
	    String[] C = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
	    String[] X = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
	    String[] I = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
	    StringBuffer sb = new StringBuffer();
	    sb.append(M[i/1000]).append(C[(i%1000)/100]).append(X[(i%100)/10]).append(I[i%10]);
	    return sb.toString();
	}
	
	  private static final int[] vals =
	    new int[] { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };

	  private static final String[] strs =
	    new String[] { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
	    
	  public String intToRoman1(int num) {
	    StringBuilder sb = new StringBuilder();
	    for (int i = 0; num > 0; i++) {
	      for ( ; num >= vals[i]; sb.append(strs[i]), num -= vals[i]);
	    }
	    return sb.toString();
	  }
}

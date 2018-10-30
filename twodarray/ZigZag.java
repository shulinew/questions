package twodarray;

/*
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: 
 * (you may want to display this pattern in a fixed font for better legibility) 
 * n=numRows
	=2n-2    1                           2n-1                         4n-3
	=        2                     2n-2  2n                    4n-4   4n-2
	=        3               2n-3        2n+1              4n-5       .
	=        .           .               .               .            .
	=        .       n+2                 .           3n               .
	=        n-1 n+1                     3n-3    3n-1                 5n-5
	=2n-2    n                           3n-2                         5n-4
 */
public class ZigZag {
    public String convert(String s, int numRows) {
    	StringBuilder [] sbs = new StringBuilder[numRows];
    	for (int i = 0;i < sbs.length;i++){
    		sbs[i] = new StringBuilder();
    	}
    	int i = 0;
    	while (i < s.length()){
    		for (int v = 0;v<sbs.length && i <s.length();v++){
    			sbs[v].append(s.charAt(i++));
    		}
    		for(int v = sbs.length -2;v>=1 && i <s.length();v--){
    			sbs[v].append(s.charAt(i++));
    		}
    	}
    	for (int v = 1;v<sbs.length;v++){
    		sbs[0].append(sbs[v]);
    	}
    	return sbs[0].toString();
        
    }

}

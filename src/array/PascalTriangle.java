package array;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangle {
    public List<List<Integer>> generate(int numRows) {
    	List<List<Integer>> pascal = new ArrayList<List<Integer>>();
    	List<Integer> previousRow = null;
    	for (int i = 0; i<numRows;i++){
    		List<Integer> row = buildOneRow(previousRow);
    		pascal.add(row);
    		previousRow = row;
    	}
    	
    	return pascal;     
    }
    
    private List<Integer> buildOneRow(List<Integer> previousRow){
    	List<Integer> oneRow = new ArrayList<Integer>();
    	oneRow.add(1);
    	if (previousRow != null){
    		for (int i = 0; i < previousRow.size() - 1; i++){
    			oneRow.add(previousRow.get(i) + previousRow.get(i+1));
    		}
    		oneRow.add(1);
    	}
    	return oneRow;
    }
    
    /*
    List<List<Integer>> allrows = new ArrayList<List<Integer>>();
    ArrayList<Integer> row = new ArrayList<Integer>();
    for(int i=0;i<numRows;i++)
    {
        row.add(0, 1);
        for(int j=1;j<row.size()-1;j++)
            row.set(j, row.get(j)+row.get(j+1));
        allrows.add(new ArrayList<Integer>(row));
    }
    return allrows;
    */
    public List<Integer> getRow(int rowIndex) {
    	List<Integer> row = new ArrayList<Integer>();
    	for (int i = 0 ;i < rowIndex+1;i++){
    		row.add(1);
    		for (int j = i-1;j>0;j--){
    			row.set(j, row.get(j) + row.get(j-1));
    		}
    	}
    	
    	return row;      
    }
    public static void main(String[] args){
    	PascalTriangle test = new PascalTriangle();
    	test.getRow(5);
    	System.out.println( 1331 /10);
    }

}

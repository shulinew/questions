package array;

import java.util.List;

/*
 * Given a triangle, find the minimum path sum from top to bottom. 
 * Each step you may move to adjacent numbers on the row below.
 */
public class Triangle {
    public int minimumTotal(List<List<Integer>> triangle) {
    	for (int i = triangle.size() - 2; i>=0;i--){
    		for(int j = 0; j<=i;j++){
    			List<Integer> previousRow = triangle.get(i+1);
    			int sum = Math.min(previousRow.get(j), previousRow.get(j+1)) + triangle.get(i).get(j);
    			triangle.get(i).set(j, sum);
    		}
    	}
    	return triangle.get(0).get(0);
    }

}

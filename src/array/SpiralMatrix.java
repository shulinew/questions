package array;

import java.util.ArrayList;
import java.util.List;

/*
 * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
	For example,
	Given the following matrix:
	[ 1, 2, 3 ],
	 [ 4, 5, 6 ],
	 [ 7, 8, 9 ]
	 
	  [1,2,3,6,9,8,7,4,5]. 
 */

public class SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
    	List<Integer> list = new ArrayList<Integer>();
    	if(matrix == null || matrix.length == 0) return list;
    	int rows = matrix.length, cols = matrix[0].length;
    	int startRow = 0, startCol = 0,i = 0,j = 0;
    	int steps = cols;
    	while(steps > 0){
    		while(steps > 0){
    			list.add(matrix[startRow][i++]);
    			steps--;
    		}
    		rows--;
    		steps = rows;
    		if (steps <= 0){
    			break;
    		}
    		j = ++startRow;
    		startCol = i-1;
    		while(steps > 0){
    			list.add(matrix[j++][startCol]);
    			steps--;
    		}
    		cols--;
    		steps = cols;
    		if (steps <= 0){
    			break;
    		}
    		startRow = j-1;
    		i = --startCol;
    		while (steps > 0){
    			list.add(matrix[startRow][i--]);
    			steps--;
    		}
    		rows--;
    		steps = rows;
    		if (steps <= 0){
    			break;
    		}
    		j = startRow -1;
    		startCol = ++i;
    		while (steps > 0){
    			list.add(matrix[j--][startCol]);
    			steps--;
    		}
    		cols--;
    		steps = cols;
    		startRow = ++j;
    		i = startCol + 1;
    	}
    	return list;
        
    }
    
    /*
     * Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
     * 
     */
    public int[][] generateMatrix(int n) {
    	int [][] matrix = new int[n][n];
    	int colStart = 0, rowStart = 0;
    	int colEnd = n -1, rowEnd = n-1;
    	int num = 1;
    	
    	while (colStart <= colEnd && rowStart <= rowEnd){
    		for (int i = colStart; i<= colEnd;i++){
    			matrix[rowStart][i] = num++;
    		}
    		++rowStart;
    		for (int j = rowStart;j<=rowEnd;j++){
    			matrix[j][colEnd] = num++;
    		}
    		colEnd--;
    		for (int i = colEnd;i >= colStart;i--){
    			matrix[rowEnd][i] = num++;
    		}
    		rowEnd--;
    		for (int j = rowEnd;j>= rowStart;j--){
    			matrix[j][colStart] = num++;
    		}
    		colStart++;
    	}
    	
    	return matrix;
        
    }
    /*
     *  int i = 0, j = 0, k = 1;
            while (k <= n * n) {
                matrix[i][j] = k++;
                if (dir == 0){
                    j++;
                    if (j == n || matrix[i][j] != 0) dir = 1, j--, i++;
                } else
                if (dir == 1) {
                    i++;
                    if (i == n || matrix[i][j] != 0) dir = 2, i--, j--;
                } else
                if (dir == 2) {
                    j--;
                    if (j < 0 || matrix[i][j] != 0) dir = 3, j++, i--;
                } else
                if (dir == 3) {
                    i--;
                    if (i < 0 || matrix[i][j] != 0) dir = 0, i++, j++;
                }
            }
            return matrix;
     */
    public static void main(String[] args){
    	int [][] matrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
    	SpiralMatrix test = new SpiralMatrix();
    	List<Integer> list = test.spiralOrder(matrix);
    	for (int i = 0;i < list.size();i++){
    		System.out.println(list.get(i));
    	}
    }

}

package array;

/*
 * You are given an n x n 2D matrix representing an image.
	Rotate the image by 90 degrees (clockwise).
	Follow up:
	Could you do this in-place?
 */
public class RotateImage {
	public void rotate(int[][] matrix){
		int temp = 0;
		for (int i = 0; i< matrix.length/2;i++){
			for(int j = 0; j < matrix[0].length;j++){
				temp = matrix[i][j];
				matrix[i][j] = matrix[matrix.length - 1-i][j];
				matrix[matrix.length - 1-i][j] = temp;
			}
		}
		for (int i = 0 ; i< matrix.length;i++){
			for (int j = i;j< matrix[0].length;j++){
				temp = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = temp;
				
			}
		}
	}

}

package array;

public class NQueensBT {
	
	public int[][] solution;
	
	public NQueensBT(int n) {
		solution = new int[n][n];
		for (int i = 0;i < n; i++){
			for (int j = 0;j < n; j++) {
				solution[i][j] = 0;
			}
		}
	}
	public void print(int n){
		if (placeQueens(0, n)){
			for (int i = 0; i < n; i++){
				for (int j = 0; j < n; j++){
					System.out.print(" " + solution[i][j]);
				}
				System.out.println();
			}
		}else{
			System.out.println("No solution!!");
		}
	}
	public boolean placeQueens(int queen, int n){
		if (queen == n){
			return true;
		}
		for (int row = 0; row < n; row++){
			if (canPlace(solution, row, queen)){
				solution[row][queen] = 1;
			}
			if (placeQueens(queen+1, n)){
				return true;
			}
			solution[row][queen] = 0;
		}
		return false;
	}

	private boolean canPlace(int[][] solution, int row, int column){
		// since we are filling one column at a time,
		// we will check if no queen is placed in that particular row
		for (int i = 0; i< column; i++){
			if (solution[row][i] == 1){
				return false;
			}
		}
		// we are filling one column at a time,so we need to check the upper and
		// diagonal as well
		// check upper diagonal
		for(int i = row, j = column;i>= 0 && j>= 0;i--, j--){
			if (solution[i][j] == 1){
				return false;
			}
		}
		for (int i = row, j = column; i< solution.length && j >= 0; i++, j--){
			if (solution[i][j] == 1){
				return false;
			}
		}
		return true;
	}

		
	public static void main(String[] args) {
		int n = 8;
		NQueensBT q = new NQueensBT(n);
		q.print(n);
	
	}
}

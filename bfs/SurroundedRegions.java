package bfs;

/*
 *  Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region. 
before:
	X X X X
	X O O X
	X X O X
	X O X X
	after:
	X X X X
	X X X X
	X X X X
	X O X X
 */
public class SurroundedRegions {
	public void surroundedRegions(char[][] board) {
		int row = board.length;
		if (row == 0) return;
		int column = board[0].length;
		int i,j;
		for  (i = 0; i< row; i++) {
			check(board, i, 0, row, column);
			if (column > 1) {
				check(board, i, column - 1, row, column);
			}
		}
		for (j = 1; j+1 < column; j++) {
			check(board, 0, j, row, column);
			if (row > 1) {
				check(board, row -1, j, row, column);
			}
		}
		for (i = 0; i< row; i++) {
			for (j =0; j< column; j++) {
				board[i][j] = (board[i][j] == '1') ? 'O':'X';
					
			}
		}
	}
	private void check(char[][] board, int i, int j, int row, int column){
		if (board[i][j] == 'O') {
			board[i][j] = '1';
			if (i > 1) {
				check(board, i-1, j, row, column);
			}
			if (j > 1) {
				check(board, i, j-1, row, column);
			}
			if (i+1 < row) {
				check(board, i+1, j, row, column);
			}
			if (j+1 < column) {
				check(board, i, j+1, row, column);
			}
		}
	}

}

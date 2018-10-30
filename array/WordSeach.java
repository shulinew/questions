package array;

/*
 *  Given a 2D board and a word, find if the word exists in the grid.
	The word can be constructed from letters of sequentially adjacent 
	cell, where "adjacent" cells are those horizontally or vertically 
	neighboring. The same letter cell may not be used more than once. 
 */
public class WordSeach {
    public boolean exist(char[][] board, String word) {
    	for (int i=0;i<board.length;i++){
    		for (int j=0;j<board[0].length;j++){
    			if (exist(board, word, i, j, 0))
    				return true;
    		}
    	}
    	return false;
        
    }
    private boolean exist(char[][] board, String word, int i, int j, int index){
    	if (index == word.length()){
    		return true;
    	}
    	if (i < 0 || j <0 || i == board.length ||
    			j == board[0].length || word.charAt(index) != board[i][j]){
    		return false;
    	}
    	board[i][j] = '*';
    	boolean exist = exist(board, word, i+1, j, index+1) ||
    			        exist(board, word, i, j+1, index+1) ||
    			        exist(board, word, i-1, j, index+1) ||
    			        exist(board, word, i, j-1, index+1);
    	board[i][j] = word.charAt(index);
    	return exist;
    	
    }

}

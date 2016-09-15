package twodarray;

/*
 * Determine if a Sudoku is valid
 * 



 00:00 01:01 02:02 03:10 04:11 05:12 06:20 07:21 08:22
 10:03 11:04 12:05 13:13 14:14 15:15 16:23 17:24 18:25
 20:06 21:07 22:08 23:16 24:17 25:18 26:26 27:27 28:28
 30:30 31:31 32:32 33:40 34:41 35:42 36:50 37:51 38:52
 40:33 41:34 42:35 43:43 44:44 45:45 46:53 47:54 48:55
 50:36 51:37 52:38 53:46 54:47 55:48 56:56 57:57 58:58
 60:60 61:61 62:62 63:70 64:71 65:72 66:80 67:81 68:82
 70:63 71:64 72:65 73:73 74:74 75:75 76:83 77:84 78:85
 80:66 81:67 82:68 83:76 84:77 85:78 86:86 87:87 88:88
 
 
0,0, 0,1, 0,2; < --- 3 Horizontal Steps followed by 1 Vertical step to next level.

1,0, 1,1, 1,2; < --- 3 Horizontal Steps followed by 1 Vertical step to next level.

2,0, 2,1, 2,2; < --- 3 Horizontal Steps.

And so on...
But, the j iterates from 0 to 9.

But we need to stop after 3 horizontal steps, and go down 1 step vertical.

Use % for horizontal traversal. Because % increments by 1 for each j : 0%3 = 0 , 1%3 = 1, 2%3 = 2, and resets back. So this covers horizontal traversal for each block by 3 steps.

Use / for vertical traversal. Because / increments by 1 after every 3 j: 0/3 = 0; 1/3 = 0; 2/3 =0; 3/3 = 1.

So far, for a given block, you can traverse the whole block using just j.

But because j is just 0 to 9, it will stay only first block. But to increment block, use i. To move horizontally to next block, use % again : ColIndex = 3 * i%3 (Multiply by 3 so that the next block is after 3 columns. Ie 0,0 is start of first block, second block is 0,3 (not 0,1);

Similarly, to move to next block vertically, use / and multiply by 3 as explained above. Hope this helps.
            int RowIndex = 3*(i/3);
            int ColIndex = 3*(i%3);
if(board[RowIndex + j/3][ColIndex + j%3]!='.' && !cube.add(board[RowIndex + j/3][ColIndex + j%3]))
                return false;

 */
public class Sudoku {
//    public boolean isValidSudoku(char[][] board) {
//        HashSet<Character> rows = new HashSet<Character>();
//        HashSet<Character> columns = new HashSet<Character>();
//        HashSet<Character> cube = new HashSet<Character>();
//        for (int j = 0; j < 9;j++){
//            if(board[i][j]!='.' && !rows.add(board[i][j]))
//                return false;
//            if(board[j][i]!='.' && !columns.add(board[j][i]))
//                return false;
//            int RowIndex = 3*(i/3);
//            int ColIndex = 3*(i%3);
//            if(board[RowIndex + j/3][ColIndex + j%3]!='.' && !cube.add(board[RowIndex + j/3][ColIndex + j%3]))
//                return false;
//        }
//        
//    }
    private void print2D(){
    	for (int i =0; i< 9;i++){
	        for (int j = 0; j < 9;j++){
	            int RowIndex = 3*(i/3);
	            int ColIndex = 3*(i%3);
	            StringBuilder sb = new StringBuilder();
	            sb.append(" ").append(i).append(j).append(":").append(RowIndex + j/3).append(ColIndex + j%3);
	            System.out.print(sb.toString());
	        }
	        System.out.print("\n");
    	}
    	
    }
    public static void main(String[] args){
    	Sudoku sudoku = new Sudoku();
    	sudoku.print2D();
    	
    }

}

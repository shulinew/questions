/*
Think about Zuma Game. You have a row of balls on the table, colored red(R), yellow(Y), blue(B), green(G), and white(W). You also have several balls in your hand.

Each time, you may choose a ball in your hand, and insert it into the row (including the leftmost place and rightmost place). Then, if there is a group of 3 or more balls in the same color touching, remove these balls. Keep doing this until no more balls can be removed.

Find the minimal balls you have to insert to remove all the balls on the table. If you cannot remove all the balls, output -1.

Examples:

Input: "WRRBBW", "RB"
Output: -1
Explanation: WRRBBW -> WRR[R]BBW -> WBBW -> WBB[B]W -> WW

Input: "WWRRBBWW", "WRBRW"
Output: 2
Explanation: WWRRBBWW -> WWRR[R]BBWW -> WWBBWW -> WWBB[B]WW -> WWWW -> empty

Input:"G", "GGGGG"
Output: 2
Explanation: G -> G[G] -> GG[G] -> empty 

Input: "RBYYBBRRB", "YRBGB"
Output: 3
Explanation: RBYYBBRRB -> RBYY[Y]BBRRB -> RBBBRRB -> RRRB -> B -> B[B] -> BB[B] -> empty 


    You may assume that the initial row of balls on the table won’t have any 3 or more consecutive balls with the same color.
    The number of balls on the table won't exceed 20, and the string represents these balls is called "board" in the input.
    The number of balls in your hand won't exceed 5, and the string represents these balls is called "hand" in the input.
    Both input strings will be non-empty and only contain characters 'R','Y','B','G','W'.


*/
public class Zumba {
    public int findMinStep(String board, String hand) {
        if(mem.containsKey(board) && mem.get(board).containsKey(hand)) {
            return mem.get(board).get(hand);
        }
        int bL = board.length(), hL = hand.length();
        if(bL == 0) {return 0;}
        if(hL == 0) {return -1;}
        int min = Integer.MAX_VALUE;
        for(int j = 0; j < hL; j++) {
            char c = hand.charAt(j);
            //not use
            String remain = hand.substring(0, j) + hand.substring(j + 1);
            int next = findMinStep(board, remain);//not use ball, count as no extra step
            if(next != -1) {
                min = Math.min(next, min); 
            }
              
            //use            
            for(int i = 0; i <= bL; i++) {
                if(i != 0 && i < bL && board.charAt(i) == board.charAt(i - 1)) {continue;}
                String newBoard = board.substring(0, i) + c + (i == bL ? "" : board.substring(i));
                next = findMinStep(collapse(newBoard), remain);
                if(next != -1) {
                    min = Math.min(next + 1, min);
                }
                 //use one ball, count as one step
            }
        }
        int res = min == Integer.MAX_VALUE ? -1 : min;
        return ret(board, hand, res);
    }
    private int ret(String board, String hand, int res) {
        Map<String, Integer> m = mem.getOrDefault(board, new HashMap<>());
        m.put(hand, res);
        mem.put(board, m);
        return res;
    }
    private String collapse(String s) {
        for(int i = 0, j = 0; j < s.length(); j++) {
            if(s.charAt(j) == s.charAt(i)) {continue;}
            if(j - i >= 3) {
                return collapse(s.substring(0, i) + s.substring(j));
            }
            else {
                i = j;
            }
        }
        return s;
    }

    ///////////////2////////////////////
    private int aux(String s, int[] c){
        if("".equals(s)) return 0;
        //worst case, every character needs 2 characters; plus one to make it impossible, ;-)
        int res = 2 * s.length() + 1; 
        for(int i = 0; i < s.length();){
            int j = i++;
            while(i < s.length() && s.charAt(i) == s.charAt(j)) i++;
            int inc = 3 - i + j;
            if(c[s.charAt(j)] >= inc){
                int used = inc <= 0 ? 0 : inc;
                c[s.charAt(j)] -= used;
                int temp = aux(s.substring(0, j) + s.substring(i), c);
                if(temp >= 0) res = Math.min(res, used + temp);
                c[s.charAt(j)] += used;
            }
        }
        return res == 2 * s.length() + 1 ? -1 : res;
    }
    
    public int findMinStep(String board, String hand) {
        int[] c = new int[128];
        for(char x : hand.toCharArray()){
            c[x]++;
        }
        return  aux(board, c);
    }

    /////////////////////////3////////////////////////
    int MAXCOUNT = 6;   // the max balls you need will not exceed 6 since "The number of balls in your hand won't exceed 5"

    public int findMinStep(String board, String hand) {
        int[] handCount = new int[26];
        for (int i = 0; i < hand.length(); ++i) ++handCount[hand.charAt(i) - 'A'];
        int rs = helper(board + "#", handCount);  // append a "#" to avoid special process while j==board.length, make the code shorter.
        return rs == MAXCOUNT ? -1 : rs;
    }
    private int helper(String s, int[] h) {
        s = removeConsecutive(s);     
        if (s.equals("#")) return 0;
        int  rs = MAXCOUNT, need = 0;
        for (int i = 0, j = 0 ; j < s.length(); ++j) {
            if (s.charAt(j) == s.charAt(i)) continue;
            need = 3 - (j - i);     //balls need to remove current consecutive balls.
            if (h[s.charAt(i) - 'A'] >= need) {
                h[s.charAt(i) - 'A'] -= need;
                rs = Math.min(rs, need + helper(s.substring(0, i) + s.substring(j), h));
                h[s.charAt(i) - 'A'] += need;
            }
            i = j;
        }
        return rs;
    }
    //remove consecutive balls longer than 3
    private String removeConsecutive(String board) {
        for (int i = 0, j = 0; j < board.length(); ++j) {
            if (board.charAt(j) == board.charAt(i)) continue;
            if (j - i >= 3) return removeConsecutive(board.substring(0, i) + board.substring(j));
            else i = j;
        }
        return board;
    }

}
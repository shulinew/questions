import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
n the "100 game," two players take turns adding, to a running total, any integer from 1..10. The player who first causes the running total to reach or exceed 100 wins.

What if we change the game so that players cannot re-use integers?

For example, two players might take turns drawing from a common pool of numbers of 1..15 without replacement until they reach a total >= 100.

Given an integer maxChoosableInteger and another integer desiredTotal, determine if the first player to move can force a win, assuming both players play optimally.

You can always assume that maxChoosableInteger will not be larger than 20 and desiredTotal will not be larger than 300. 

Input:
maxChoosableInteger = 10
desiredTotal = 11

Output:
false

Explanation:
No matter which integer the first player choose, the first player will lose.
The first player can choose an integer from 1 up to 10.
If the first player choose 1, the second player can only choose integers from 2 up to 10.
The second player will win by choosing 10 and get a total = 11, which is >= desiredTotal.
Same with other integers chosen by the first player, the second player will always win.
https://leetcode.com/problems/can-i-win/discuss/95277/Java-solution-using-HashMap-with-detailed-explanation
*/
public class CanIWin {

    //https://leetcode.com/problems/can-i-win/discuss/95293/Java-easy-strightforward-solution-with-explanation
    /*
    The solution is quite strightforward. First off we have to eliminate primitive cases. So,

    if the first player can choose a number, which is already greater than or equal to the desired total obviously it wins.
    If max choosable integer is less than the desired total, but if it exceeds the desired total in sum with any other number then the first player looses anyway.
    If the sum of all number in the pool cannot exceed or reach the desired total, then no one can win.

Now, for the other cases we can use MiniMax logic to reveal the winner. Because both player play optimally, In order to win, the first player has to make a choice, that leaves the second player no chance to win. Thus, at each step we consider all the possible choices by the current player and give turn to the second player recursively. If we find a move, after which the second player looses anyway or we have already exceed the desired total by adding the chosen number, we return true, i.e the current player wins. This way the game looks like the following tree:

     player1 ->  0
              /| ...\
  player2 -> 1 2 ....max 
            /|\ ..../ | \
player1 -> 2 3...  1  2 ..max-1
           ...                \
player1 ->   /      |     \   loose
player2 -> loose   win   loose

The figure above helps to imagine how the algorithm considers all possible scenarios of the game. The leafs of the game tree are loose or win states for one of the players.
 Finally the logic concludes to the idea, that if some branch does not contain any leaf that ends 
 with win state for player2, the move associated with that branch is the optimal one for the first player.

P.S: Time complexity of naive implementation will work for O(n!). Therefore we have to memorize branch states after traversing once.
    */
    // Map<Integer, Boolean> set[];
    // public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
    //     if(maxChoosableInteger >= desiredTotal) return true;
    //     if(maxChoosableInteger+1 >=desiredTotal) return false;
    //     set = new Map[301];
    //     for(int i  =0 ;i<301;i++) set[i] = new HashMap<>();
    //     if(maxChoosableInteger*(maxChoosableInteger+1)/2 < desiredTotal) return false;
    //     return canWin((1<<maxChoosableInteger+1)-1, desiredTotal);
    // }
    
    // public boolean canWin(int set1, int total){
    //     if(set[total].containsKey(set1)) return set[total].get(set1);
    //     for(int i = 20;i>=1;i--){
    //         int p = (1<<i);
    //         if((p&set1) == p){
    //             int set1next = (set1^p);
    //             int totalNext = total - i;
    //             if(totalNext<=0) return true;
    //             boolean x;
    //             if(set[totalNext].containsKey(set1next)) x = set[totalNext].get(set1next);
    //             else x = canWin(set1next, totalNext);
    //             if(!x){
    //                 set[total].put(set1, true);
    //                 return true;
    //             }
    //         }
    //     }
    //     set[total].put(set1, false);
    //     return false;
    // }
    public static boolean canIWinBrutalForce(int maxChoosableInteger, int desiredTotal) {
        if (desiredTotal <= 0) return true;
        int sum = (maxChoosableInteger + 1) * maxChoosableInteger/2; 
        Map<String, Boolean> map = new HashMap<String,Boolean>();
        boolean [] used = new boolean[maxChoosableInteger+1];
        return desiredTotal < 2 ? true : sum < desiredTotal ? false : sum == desiredTotal ? (maxChoosableInteger % 2 == 0 ? false : true) : helper(desiredTotal, map, used);
    }
    private static boolean helper(int desiredTotal, Map<String, Boolean> map, boolean[] used){
        if (desiredTotal <= 0) {
            return false;
        }
        String key = Arrays.toString(used);
        if (!map.containsKey(key)) {
            for(int i = 1; i < used.length; i++) {
                if (!used[i]) {
                    used[i] = true;
                    // the other player lose
                    if(!helper(desiredTotal-i, map, used)) {
                        map.put(key, true);
                        // Before return, need reset for this path for other recursive call
                        used[i] = false;
                        return true;
                    }
                    //take i doesn't win, try another one
                    used[i] = false;
                }
            }
            // exhaust all possible remaining numbers, this key doesn't work
            map.put(key, false);
        }
        return map.get(key);
    }

    int [] memo = new int[1<<20];
    public boolean canIwin(int maxChoosableInteger, int target) {
        int sum = (1 + maxChoosableInteger) * maxChoosableInteger/2; // sum of all numbers
        return target < 2 ? true : sum < target ? false : sum == target ? (maxChoosableInteger % 2 == 0 ? false : true) : dfs(maxChoosableInteger, target, 0);
    }
    private boolean dfs(int maxChoosableInteger, int target, int state) {
        if (target <= 0 || memo[state] != 0) {
            return target > 0 && memo[state] > 0;
        }
        for (int i = 0; i < maxChoosableInteger; i++) {
            if ((state & 1 << i) == 0 && !dfs(maxChoosableInteger,target-i-1, state | 1 << i)) {
                memo[state] = 1;
                return true;
            }
        }
        memo[state] = -1;
        return false;
    }


    /////////////////////////////////////////////////////
    public int minScoreTriangulation (int[] A) {
        int[][] memo = new int[51][51]; // why 51
        return dp(0, A.length-1, A, memo);
    }
    private int dp(int pos1, int pos2, int[] A, int[][] memo) {
        if (pos2 - pos1 <= 1) {
            return 0;
        }
        if (memo[pos1][pos2] != 0) {
            return memo[pos1][pos2];
        }
        int result = Integer.MAX_VALUE;
        for (int i = pos1+1; i < pos2; i++) {
            result = Math.min(result, dp(pos1, i, A, memo) + dp(i, pos2, A, memo) + A[pos1]*A[pos2]*A[i]);
        }
        memo[pos1][pos2] = result;
        return result;
    }
    public static void main (String[] args) {
        CanIWin test = new CanIWin();
        // // System.out.println(CanIWin.canIWinBrutalForce(10, 11));
        // System.out.println(test.canIwin(4, 6));
        // MiniscoreTriangulation test1 = new MiniscoreTriangulation();
        // System.out.println(CanIWin.canIWinBrutalForce(10, 11));
        System.out.println(test.minScoreTriangulation(new int[]{1,3,1,4,1,5}));
        // System.out.println(System.getenv("Path"));
    }

/*
State of Game: initially, we have all M numbers [1, M] available in the pool. Each number may or may not be picked at a state of the game later on, so we have maximum 2^M different states. Note that M <= 20, so int range is enough to cover it. For memorization, we define int k as the key for a game state, where

    the i-th bit of k, i.e., k&(1<<i) represents the availability of number i+1 (1: picked; 0: not picked).

At state k, the current player could pick any unpicked number from the pool, so state k can only go to one of the valid next states k':

    if i-th bit of k is 0, set it to be 1, i.e., next state k' = k|(1<<i).

Recursion: apparently

    the current player can win at state k iff opponent can't win at some valid next state k'.

Memorization: to speed up the recursion, we can use a vector<int> m of size 2^M to memorize calculated results m[k] for state key k:

    0 : not calculated yet;
    1 : current player can win;
    -1: current player can't win.

Initial State Check:
There are several checks to be done at initial state k = 0 for early termination so we won't waste our time for DFS process:

    if T < 2, obviously, the first player wins by simply picking 1.
    if the sum of entire pool S = M*(M+1)/2 is less than T, of course, nobody can reach T.
    if the sum S == T, the order to pick numbers from the pool is irrelevant. Whoever picks the last will reach T. So the first player can win iff M is odd.

*/
    // bool canIWin(int M, int T) {
    //   int S = M*(M+1)/2; // sum of entire pool
    //   return T<2? true : S<T? false : S==T? M%2 : dfs(M,T,0);
    // }
    
    // bool dfs(int M, int T, int k) {
    //   if (T<=0 || m[k]) return T>0 && m[k]>0; // memorization or total reached by opponent
    //   for (int i = 0; i < M; ++i)
    //     if (!(k&1<<i) && !dfs(M, T-i-1, k|1<<i)) return m[k] = 1; // current player wins
    //   return !(m[k] = -1); // current player can't win
    // }
    // memo save state
    // 0: not calculate
    // 1: win
    // -1: lose

}
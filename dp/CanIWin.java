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
        public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        int sum = (1+maxChoosableInteger)*maxChoosableInteger/2;
        if(sum < desiredTotal) return false;
        if(desiredTotal <= 0) return true;
        
        map = new HashMap();
        used = new boolean[maxChoosableInteger+1];
        return helper(desiredTotal);
    }
    
    public boolean helper(int desiredTotal){
        if(desiredTotal <= 0) return false;
        int key = format(used);
        if(!map.containsKey(key)){
    // try every unchosen number as next step
            for(int i=1; i<used.length; i++){
                if(!used[i]){
                    used[i] = true;
     // check whether this lead to a win (i.e. the other player lose)
                    if(!helper(desiredTotal-i)){
                        map.put(key, true);
                        used[i] = false;
                        return true;
                    }
                    used[i] = false;
                }
            }
            map.put(key, false);
        }
        return map.get(key);
    }
   
    // transfer boolean[] to an Integer 
    public int format(boolean[] used){
        int num = 0;
        for(boolean b: used){
            num <<= 1;
            if(b) num |= 1;
        }
        return num;
    }

    //
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
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if(maxChoosableInteger >= desiredTotal) return true;
        if(maxChoosableInteger+1 >=desiredTotal) return false;
        set = new Map[301];
        for(int i  =0 ;i<301;i++) set[i] = new HashMap<>();
        if(maxChoosableInteger*(maxChoosableInteger+1)/2 < desiredTotal) return false;
        return canWin((1<<maxChoosableInteger+1)-1, desiredTotal);
    }
    
    public boolean canWin(int set1, int total){
        if(set[total].containsKey(set1)) return set[total].get(set1);
        for(int i = 20;i>=1;i--){
            int p = (1<<i);
            if((p&set1) == p){
                int set1next = (set1^p);
                int totalNext = total - i;
                if(totalNext<=0) return true;
                boolean x;
                if(set[totalNext].containsKey(set1next)) x = set[totalNext].get(set1next);
                else x = canWin(set1next, totalNext);
                if(!x){
                    set[total].put(set1, true);
                    return true;
                }
            }
        }
        set[total].put(set1, false);
        return false;
    }
}
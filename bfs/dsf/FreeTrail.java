
/*Hi There! The key point in the problem is to make decision whether to move clockwise or anticlockwise. Actually to get 
wer, we have to move clockwise for some characters of key and anti-clockwise for others. If apply brute force, then for each 
position in key we have two options,

    Search for the character clockwise
    Search for the character anti-clockwise

To find optimal answer we need to try both options and get minimum of them. Thus, we obtain dfs solution for the problem.
But, there are duplicate calculation for some positions. Therefore, we need to memorize states. The state is defined by 
position of thering and the index of character in the key. This way, we can avoid calculating number of steps for the same state. 
Code will clarify the idea more.
*/
public class FreeTrail {
    Map<String, Map<Integer, Integer>> memo;
    public int findRotateSteps(String ring, String key) {
        memo = new HashMap<>();
        return helper(ring, key, 0);
    }
    
    private int findPos(String ring, char ch){ // find first occurrence clockwise
        return ring.indexOf(ch);
    }
    
    private int findBackPos(String ring, char ch){ //find first occurrence  anti-clockwise
        if(ring.charAt(0) == ch) return 0;
        for(int i = ring.length()-1;i>0;i--){
            if(ring.charAt(i) == ch) return i;
        }
        return 0;
    }
    
    private int helper(String ring, String key, int i){
        if(i == key.length()) return 0;
        int res = 0;
        char ch = key.charAt(i);
        if(memo.containsKey(ring) && memo.get(ring).containsKey(i)) return memo.get(ring).get(i);
        int f = findPos(ring, ch);
        int b = findBackPos(ring, ch);
        int forward = 1+f+helper(ring.substring(f)+ring.substring(0, f), key, i+1);
        int back = 1+ring.length()-b + helper(ring.substring(b)+ring.substring(0, b),key, i+1);
        res = Math.min(forward, back);
        Map<Integer, Integer> ans = memo.getOrDefault(ring, new HashMap<>());
        ans.put(i, res);
        memo.put(ring, ans);
        return res;
    }
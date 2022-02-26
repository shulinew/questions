/*
*/

public class Wordbreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dictionary = new HashSet<String>(wordDict);
        Map<String, Boolean> memo = new HashMap<String, Boolean>();
        return helper(s, dictionary, memo);    
    }
    private boolean helper(String s, Set<String> dictionary, Map<String, Boolean> memo) {
        int length = s.length();
        if (memo.containsKey(s)) {
            return memo.get(s);
        }
        if (length == 0) {
            return true;
        }
        for (int i = 1; i <= length; i++){
            if(dictionary.contains(s.substring(0, i)) && helper(s.substring(i), dictionary)) {
                memo.put(s, true);
                return true;
            }
        }
        memo.put(s, false);
        return false;
    }
    public boolean wordBreak(String s, List<String> wordDict) {
        int length = s.length();
        boolean[] dp = new boolean[length+1];
        dp[0] = true;
        for (int i = 1; i <= length; i++){
            for (int j = 0; j < i; j++){
                if (dp[j] && wordDict.contains(s.substring(j,i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[length];
    }
    public boolean wordBreakDP(String s, List<String> wordDict) {
        int length = s.length();
        boolean[] dp = new boolean[length+1];
        dp[0] = true;
        for (int i = 1; i <= length; i++){
            for (String word: wordDict){
                int subLen = word.length();
                if(subLen <=i){
                    if(dp[i-subLen] && word.contains(s.substring(i-subLen, i))) {
                        dp[i] = true;
                        break;
                    }
                }
            }
        }
        return dp[length];
    }
}
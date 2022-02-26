package dp;

import java.util.*;

public class LongestStrChain {
    public int longestStrChain(String[] words) {
        Map<String, Integer> dictionary = new HashMap<String, Integer>();
        Arrays.sort(words, (a, b) -> a.length() - b.length());
        int result = 0;
        for (String word : words) {
            int best = 0;
            for (int i = 0; i < word.length(); i++) {
                // Remove one char at i
                String subWord = word.substring(0, i) + word.substring(i+1);
                best = Math.max(best, dictionary.getOrDefault(subWord, 0) + 1);
            }
            dictionary.put(word, best);
            result = Math.max(result, best);
        }
        return result;
    }
    public int longestStrChainDP(String[] words) {
        Arrays.sort(words, new StringByLengthSort());
        int[] dp = new int[words.length];
        int maxLen = 0;
        for (int i = 0; i < words.length; i++) {
            dp[i] = 1;
            for (int j = i-1; j >= 0 && words[i].length() - words[j].length() <= 1; j--) {
                if (isPredecessor(words[j], words[i])) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }
        return maxLen;
    }
    // s1's length <= s2's length
    private boolean isPredecessor(String s1, String s2) {
        if (s1.length() == s2.length()) return false;
        int difference = 0;
        for (int i = 0, j= 0; i < s1.length();) {
            if (s1.charAt(i) == s2.charAt(j)) {
                i++;
                j++;
            } else {
                difference++;
                if (difference > 1) return false;
                j++;
            }
        }
        return true;
    }
    private static class StringByLengthSort implements Comparator<String> {
        public int compare(String a, String b) {
            return Integer.compare(a.length(), b.length());
        }
    }

    // test case: "ksqvsyq","ks","kss","czvh","zczpzvdhx","zczpzvh","zczpzvhx","zcpzvh","zczvh","gr","grukmj","ksqvsq","gruj","kssq","ksqsq","grukkmj","grukj","zczpzfvdhx","gru"}
    public int logestStrChainDfs(String[] words) {
        int result = 0;
        Set<String> set = new HashSet<String>();
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (String word: words) {
            set.add(word);
        }
        for(String word: words) {
            result = Math.max(result, dfs(map, set, word));
        }
        return result;
    }
    private int dfs(Map<String, Integer>memo, Set<String> set, String word) {
        if (memo.containsKey(word)) return memo.get(word);
        int count = 0;
        for (int i = 0; i < word.length(); i++) {
            String pre = word.substring(0, i) + word.substring(i+1);
            if (set.contains(pre)) {
                count = Math.max(count, dfs(memo, set, pre));
            }
        }
        memo.put(word, 1 + count);
        return count + 1;
    }
}

package string;

import java.util.HashSet;
import java.util.Set;

/*
 * Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words 
 * do not share common letters. You may assume that each word will contain only lower case letters. If no such 
 * two words exist, return 0. 
 * https://discuss.leetcode.com/topic/57973/java-29ms-mask-two-pointer-4-versions-for-all-explained-in-detail
 */
public class MaxProductOfLength {
    public int maxProduct(String[] words) {
        int n = words.length;
        Arrays.sort(words, (a, b)->b.length() - a.length());
        Queue<int[]> queue = new PriorityQueue<>((a, b)->words[b[0]].length()*words[b[1]].length() -  words[a[0]].length()*words[a[1]].length());
        for(int i=0; i<n; i++)
            queue.offer(new int[]{i, 0});
        while(!queue.isEmpty()){
            int[] curr = queue.poll();
            int i = curr[0];
            int j = curr[1];
            if(i != j && (getCode(words[i]) & getCode(words[j])) == 0)
                return words[i].length() * words[j].length();
            if(j < n-1)
                queue.offer(new int[]{i, j+1});
        }
        return 0;
    }
    
    private int getCode(String str){
        int r = 0;
        for(int i=0; i<str.length(); i++)
            r |= 1<<(str.charAt(i) - 'a');
        return r;
    }
    
    public int maxProduct1(String[] words) {
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.length() - s2.length();
            }
        });
        
        int max = 0, mask[] = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            for (char c : words[i].toCharArray())
                mask[i] |= 1 << (c - 'a');
        }

        for (int i = words.length-1; i > 0; i--) {
            int len = words[i].length(), maski = mask[i];
            if (len * len <= max)
                return max;

            for (int j = i-1; j >= 0; j--) {
                if ((mask[j] & maski) == 0) {
                    max = Math.max(max, len * words[j].length());
                    break;
                }
            }
        }
        return max;
    }
    
    public int maxProduct_1(String[] words) {
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.length() - s2.length();
            }
        });
        int max = 0;
        boolean[] set = new boolean[26];
        for (int i = words.length-1; i > 0; i--) {
            int len = words[i].length();
            if (len * len <= max)
                return max;
            // set
            for (char ch: words[i].toCharArray())
                set[ch-'a'] = true;

            for (int j = i-1; j >= 0; j--) {
                String tmp = words[j];
                int k = 0;
                for (; k < tmp.length(); k++) {
                    if (set[tmp.charAt(k) - 'a'])
                        break;
                }

                if (k == tmp.length()) {
                    max = Math.max(max, len * tmp.length());
                    break;
                }
            }
            // clear the set
            Arrays.fill(set, false);
        }
    }
    public int maxProduct2(String[] words) {
    	if (words == null || words.length == 0) return 0;
    	int length = words.length;
    	int [] masks = new int[length];
    	for (int i = 0;i<length;i++){
    		masks[i] = 0;
    		for (int j = 0;j<words[i].length();j++){
    			masks[i] |= 1 << (words[i].charAt(j) - 'a');
    		}
    	}
    	int maxProduct = 0;
    	for (int i = 0; i < length; i++){
    		for (int j = i +1; j < length; j++){
    			if ((masks[i] & masks[j]) == 0){
    				maxProduct = Math.max(maxProduct, words[i].length() * words[j].length());
    			}
    		}
    	}
    	return maxProduct;
    	
    }

}

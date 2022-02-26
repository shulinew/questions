/*
The idea is, if we know the max number of unique substrings in p ends with 'a', 'b', ..., 'z', then the summary of them is the answer. Why is that?

    The max number of unique substring ends with a letter equals to the length of max contiguous substring ends with that letter. Example "abcd", the max number of unique substring ends with 'd' is 4, apparently they are "abcd", "bcd", "cd" and "d".
    If there are overlapping, we only need to consider the longest one because it covers all the possible substrings. Example: "abcdbcd", the max number of unique substring ends with 'd' is 4 and all substrings formed by the 2nd "bcd" part are covered in the 4 substrings already.
    No matter how long is a contiguous substring in p, it is in s since s has infinite length.
    Now we know the max number of unique substrings in p ends with 'a', 'b', ..., 'z' and those substrings are all in s. Summary is the answer, according to the question.
https://leetcode.com/problems/unique-substrings-in-wraparound-string/discuss/95454/Evolve-from-brute-force-to-optimal
https://leetcode.com/problems/unique-substrings-in-wraparound-string/discuss/95473/Java-two-different-solutions-with-explanation
*/

public class FindSubStringInWrapRound {
    public int findSubstringInWraproundString(String p) {
        Set<String> set = new HashSet<String>();
        int count = 0;
        for (int len = 1; i <= p.length(); len++){
            for (int i = 0; i+len <=p.length(); i++){
                String str = p.substring(i, len+i);
                if (!set.contains(str) && isIncrease(str)){
                    set.add(str);
                    count++;
                }
            }
        }
        return count;
    }

    private boolean isIncrease(String str){
        char[] chars = str.toCharArray();
        if (chars.length == 1) return true;
        for (int i = 1; i < chars.length; i++){
             if ((chars[i] - chars[i-1] != 1) && (chars[i] != 'a' || chars[i-1] != 'z')){
                return false;
             } 
        }
        return true;
    }
    public int findSubstringInWraproundStringBrutal(string p) {
        int n = p.size();
        unordered_set<string> ht;
        for(int i=0;i<n;i++)
            for(int j=i;j<n;j++) {
                if(j>i && p[j-1]+1!=p[j] && p[j-1]-p[j]!=25) break;
                ht.insert(p.substr(i,j-i+1));
            }
        return ht.size();
    }

// O(n^2). For substrs starting at the same char, we only need to record the longest one. Because it covers all the shorter substrs starting from the char. The length is the number of substrings starting at the char.
    int findSubstringInWraproundString(string p) {
        int n = p.size(), len[26]={0};
        for(int i=0;i<n;i++)
            for(int j=i;j<n;j++) {
                if(j>i && p[j-1]+1!=p[j] && p[j-1]-p[j]!=25) break;
                len[p[i]-'a'] = max(len[p[i]-'a'],j-i+1);
            }
        return accumulate(len,len+26,0);
    }

    public int findSubstringInWraproundString(String p) {
        int length = p.length();
        // Save longest substr start from each char, the length would be total count of substring
        int[] len = new int[26];
        for (int i = 0; i < length; i++){
            for (int j = 0; j < length; j++){
                if (j > i && p.charAt(j) - p.charAt(j - 1) != 1 && p.charAt(j - 1) - p.charAt(j) != 25) {
                    break;
                }
                len[p.charAt(i)-'a'] = Math.max(len[p.charAt(i)-'a'], j-i+1);
            }
        }
        int count = 0;
        for (int i = 0; i < len.length; i++){
            count += len[i];
        }
        return count;
    }

    public int findSubstringInWraproundString(String p){
        int length = p.length();
        // Save longest substr ends at each char, the length would be total count of substring
        int[] len = new int[26];
        int maxLeng = 0;
        for (int i = 0; i < length; i++){
            if (i > 0 && (p.charAt(i) - p.charAt(i - 1) == 1 || p.charAt(i - 1) - p.charAt(i) == 25)){
                maxLeng++;
            } else{
                maxLeng = 1;
            }
            len[p.charAt(i)-'a'] = Math.max(len[p.charAt(i)-'a'], maxLeng);
        }
        int count = 0;
        for (int i = 0; i < len.length; i++){
            count += len[i];
        }
        return count;
    }

    public int findSubstringInWraproundString(String p){
        int length = p.length();
        // Save longest substr start at each char. Use two points
        int[] len = new int[26];
        int startPos = 0;
        for (int i = 0; i < length; i++){
            if (i > startPos && p.charAt(i) - p.charAt(i - 1) != 1 && p.charAt(i - 1) - p.charAt(i) != 25){
                // Only need to cound to max 26 chars
                int maxLen = Math.min(i, startPos+26);
                for (int k = startPos; k < maxLen; k++) {
                    len[p.charAt(k)-'a'] = Math.max(len[p.charAt(k)-'a'], i-k);
                }
                startPos = i--;
            } 
        }
        // count the chars which were not counted as part of above logic
        for(int k=startPos; k<Math.min(length,startPos+26);k++){
            len[p.charAt(k)-'a'] = Math.max(len[p.charAt(k)-'a'],length-k);
        }
        int count = 0;
        for (int i = 0; i < len.length; i++){
            count += len[i];
        }
        return count;
    }
}
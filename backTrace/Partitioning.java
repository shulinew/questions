/*
Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.
*/

public class Partitioning {
    public List<List<String>> partition(String s) {
        List<List<String>> results = new ArrayList<List<String>>();
        backTrace(results, new ArrayList<String>(), s, 0);
        return results;
    }
    private void backTrace(List<List<String>> results, List<String> tempList, String s, int start) {
        if (s.length == start) {
            results.add(new ArrayList<String>(tempList));
            return;
        } else {
            for (int i = start; i < s.length(); i++) {
                if (isPalindrome(s, i, start)) {
                    tempList.add(s.substring(start, i+1));
                    backTrace(results, tempList, s, i+1);
                    tempList.remove(tempList.length()-1);
                }
            }
        }
    }
    private boolean isPalindrome(String s, int high, int low) {
        while(high > low) {
            if (s.charAt(low++) != s.charAt(high--)) {
                return false;
            }
        }
        return true;
    }
}
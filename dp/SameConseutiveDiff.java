/*
Return all non-negative integers of length N such that the absolute difference between every two consecutive digits is K.

Note that every number in the answer must not have leading zeros except for the number 0 itself. For example, 01 has one leading zero and is invalid, but 0 is valid.

You may return the answer in any order.
Input: N = 2, K = 1
Output: [10,12,21,23,32,34,43,45,54,56,65,67,76,78,87,89,98]

Input: N = 3, K = 7
Output: [181,292,707,818,929]

Input: 3, 1
[101,121,123,210,212,232,234,321,323,343,345,432,434,454,456,543,545,565,567,654,656,676,678,765,767,787,789,876,878,898,987,989]

https://leetcode.com/problems/find-all-anagrams-in-a-string/discuss/92007/Sliding-Window-algorithm-template-to-solve-all-the-Leetcode-substring-search-problem.

https://leetcode.com/explore/interview/card/top-interview-questions-medium/109/backtracking/795/discuss/18239/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partioning)
*/

public class SameConseutiveDiff {
    public int[] numsSameConsecDiff(int N, int K) {
         List<Integer> current = Arrays.asList(0,1,2,3,4,5,6,7,8,9);
         for (int i = 2; i <= N; i++) {
             List<Integer> current2 = new ArrayList<Integer>();
             for (Integer val: current) {
                 int x = val % 10;
                 if (x + K <= 9 && val > 0) {
                     curent2.add(val *10 + x+K);
                 }
                 if (x - k >=0 && val > 0 && K > 0) { //x > 0 Avoid add number starts with 0
                     current2.add(val * 10 + x - k);
                 }
                 current = current2;
             }
         }
        // return cur.stream().mapToInt(j->j).toArray();
         int [] res = new int[current.size()];
         int i = 0;
         for (Integer val: current) {
             res[i++] = val;
         }
         return res;    
    }

    public int[] numsSameConsecDiff(int N, int K) {
        List<Integer> list = new ArrayList<>();
        if(N==0)
            return new int[0];
        if(N==1)
			list.add(0);      // edge case
        dfs(N, K, list, 0);
        return list.stream().mapToInt(i->i).toArray();   //list.toArray(new int[list.size()]); doesn't work for primitives
    }
    public void dfs(int N, int K, List<Integer> list, int number){
        if(N == 0){   // base case, if you have added enough number of integers then append it to list; Here N is used as the total digits in temporary number 
            list.add(number);
            return ;
        }
        for(int i=0; i<10; ++i){
            if(i==0 && number ==0)    // Do not add 0 at begining of a number
                continue;
            else if(number == 0 && i!=0){     // base case, we add all the digits when we do not have any previous digit to check if difference = K
                dfs(N-1, K, list, i);
            }
            else{
                if(Math.abs((number%10) - i )==K){
                    dfs(N-1, K, list, number*10+i);    // General dfs to add the digit at the units place and reducing the number of digits by 1.
                }
            }
        }
    }
    private void helper(int N, int K, List<Integer> list, int number) {
        if (N == 0) {
            list.add(number);
            return;
        }
        for (int i = 0; i <= 9; i ++) {
            if (i == 0 && number == 0) continue;
            else if (number == 0 && i != 0) {
                helper(N-1, K, list, i);
            } else if (Math.abs(number %10-i) == K) {
                helper(N-1, K, list, number*10 + i);
            }    
        }
    }
}
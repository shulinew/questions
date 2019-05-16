/*
Find largest subset so that Si%Sj = 0 or Sj%Si = 0
*/
public class LargestDivisible {
    public List<Integer> largestDivisibleSubset(int[] nums) {
       List<Integer> result = new ArrayList<Integer>();
       int length = nums.length;
       int maxLength = 0;
       int[][] dp = new int[length][length];

       for (int i = 1; i < length; i++){
           for (int j = 0; j < length-i; j++) {
               if (nums[i+j] % nums[j]== 0 || nums[j] % nums[i+j] == 0){
                   dp[j][j+i] = dp[j][j+i-1]+1;
                   if (i > maxLength) {
                       result = new ArrayList<Integer>();
                       for (int k = 0; k <= i; k++){
                           int pos = j+k;
                           result.add(nums[pos]);
                       }
                   }
                   maxLength = Math.max(maxLength, i);
               }
           }
       }
       return result;
    }
    public List<Integer> largestDivisibleSubset(int[] nums){
        List<Integer> result = new ArrayList<Integer>();
        int length = nums.length;
        int maxLength = 0;
        Arrays.sort(nums);
        int[] dp = new int[length]; // Save the count for i
        for (int i = 1; i < length; i++){
            for (int j = 0; j < i; j++) {
               if (nums[i] % nums[j] == 0){
                   dp[i] = Math.max(dp[i], dp[j]+1);
                   maxLength = Math.max(maxLength, dp[i]);
               }
            }
        }
        for (int i = length-1; i >=0;i--){
           if(dp[i] == maxLength){
               if (result.size() < 1){
                   result.add(nums[i]);
                   maxLength--;
               } else if(result.get(result.size()-1)%nums[i] == 0) {
                   result.add(nums[i]);
                   maxLength--;
               } 
           }
        }
        return result;
    }
    public List<Integer> largestDivisibleSubset(int[] nums){
        List<Integer> result = new ArrayList<Integer>();
        Map<Integer, List<Integer>> memo = new HashMap<Integer, List<Integer>>();
        Arrays.sort(nums);
        result = helper(0,nums, memo);
        return result;
    }
    private List<Integer> helper(int start, int[] nums, Map<Integer, List<Integer>> memo ){
        if (memo.containsKey(start)) {
            return memo.get(start);
        }
        List<Integer> maxLengthList = new ArrayList<Integer>();
        int previous = start == 0? 1:nums[start-1];
        for (int i = start; i < nums.length; i++){
            if (nums[i] % previous == 0){
               List<Integer> list = new ArrayList<Integer>(helper(i+1, nums, memo));
               list.add(nums[i]);
               if(list.size() > maxLengthList.size()){
                   maxLengthList = list;
               }
            }
        }
        memo.put(start, maxLengthList);
        return maxLengthList;
    }
}
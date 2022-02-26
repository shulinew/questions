/*
Given a collection of distinct integers, return all possible permutations.
*/

public class Permutation {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        backTrace(result, new ArrayList<Integer>(), nums);
        return result;
    }
    private void backTrace(List<List<Integer>> result, List<Integer> tempList, int[] nums) {
        if (tempList.size() == nums.length()) {
            result.add(new ArrayList<Integer>(tempList));
        } else {
            for (int i = 0; i < nums.length(); i++) {
                if (!tempList.contains(nums[i])) {
                    tempList.add(nums[i]);
                    backTrace(result, tempList, nums);
                    tempList.remove(tempList.size()-1);
                }
            }
        }
    }
}
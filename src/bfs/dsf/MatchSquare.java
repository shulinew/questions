/*
Remember the story of Little Match Girl? By now, you know exactly what matchsticks the little match girl has, 
please find out a way you can make one square by using up all those matchsticks. You should not break any stick, 
but you can link them up, and each matchstick must be used exactly one time.

Your input will be several matchsticks the girl has, represented with their stick length. Your output will either 
be true or false, to represent whether you could make one square using all the matchsticks the little match girl has.

Input: [1,1,2,2,2]
Output: true
Explanation: You can form a square with length 2, one side of the square came two sticks with length 1.

Input: [3,3,3,3,4]
Output: false
Explanation: You cannot find a way to form a square with all the matchsticks.
*/

public class MatchSquare {
    public boolean makesquare(int[] nums) {
    	if (nums == null || nums.length < 4) return false;
        int sum = 0;
        for (int num : nums) sum += num;
        if (sum % 4 != 0) return false;
        
    	return dfs(nums, new int[4], 0, sum / 4);
    }
    
    private boolean dfs(int[] nums, int[] sums, int index, int target) {
    	if (index == nums.length) {
    	    if (sums[0] == target && sums[1] == target && sums[2] == target) {
                return true;
            }
            return false;
    	}
    	
    	for (int i = 0; i < 4; i++) {
    	    if (sums[i] + nums[index] > target) continue;
    	    sums[i] += nums[index];
            if (dfs(nums, sums, index + 1, target)) return true;
    	    sums[i] -= nums[index];
    	}	
    	return false;
    }

    public boolean makesquareOptimize(int[] nums) {
    	if (nums == null || nums.length < 4) return false;
        int sum = 0;
        for (int num : nums) sum += num;
        if (sum % 4 != 0) return false;
        
        Arrays.sort(nums);
        reverse(nums);
        
    	return dfs(nums, new int[4], 0, sum / 4);
    }
    
    private boolean dfs(int[] nums, int[] sums, int index, int target) {
    	if (index == nums.length) {
    	    if (sums[0] == target && sums[1] == target && sums[2] == target) {
    		return true;
    	    }
    	    return false;
    	}
    	
    	for (int i = 0; i < 4; i++) {
    	    if (sums[i] + nums[index] > target) continue;
    	    sums[i] += nums[index];
            if (dfs(nums, sums, index + 1, target)) return true;
    	    sums[i] -= nums[index];
    	}
    	
    	return false;
    }
    
    private void reverse(int[] nums) {
        int i = 0, j = nums.length - 1;
        while (i < j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++; j--;
        }
    }
}



////////////////////
For better description of the problem, let's reformulate it in the following symbolic way:

Given an array nums with n elements, let T(i, s1, s2, s3, s4) denote whether we can partition the subarray 
nums[0, i](both inclusive) into four disjoint groups such that the sum of elements in the j-th group is sj, 
with j = 1, 2, 3, 4.

With this definition, our original problem will be T(n - 1, side, side, side, side) where side is the side 
length of the square.

To solve for T(i, s1, s2, s3, s4), note that the last element of the subarray nums[0, i] (which is nums[i]) 
must belong to one of the disjoint groups, therefore we have the following recurrence relation:

T(i, s1, s2, s3, s4) = T(i - 1, s1 - nums[i], s2, s3, s4) ||
                       T(i - 1, s1, s2 - nums[i], s3, s4) ||
                       T(i - 1, s1, s2, s3 - nums[i], s4) ||
                       T(i - 1, s1, s2, s3, s4 - nums[i])

The interpretation is as follows: if nums[i] belongs to the j-th group, we subtract it from sj, then recursively 
solve for the subproblem with reduced array size and modified group sum. However, we do not know which group 
it belongs to beforehand, therefore each of the groups will be examined until we either hit the right group or 
determine that no partition is possible. Also note that if all elements in the input array are positive, an 
element cannot fall into a group with a group sum smaller than the element itself, i.e., nums[i] cannot belong 
to the j-th group if nums[i] > sj.

The termination condition for the recursion is when the subarray is empty, i.e., i = -1, at which time we will 
check whether the sum of each group is zero. If so, a partition is found and return true; otherwise no partition 
is possible and return false.

Here is the java program based on the above ideas:

public boolean makesquare(int[] nums) {
    if (nums.length < 4) return false;
        
    int perimeter = 0;
    for (int ele : nums) perimeter += ele;
    if (perimeter % 4 != 0) return false;
    
    int side = perimeter / 4;

    return makesquareSub(nums, nums.length - 1, new int[] {side, side, side, side});
}

private boolean makesquareSub(int[] nums, int i, int[] s) {
    if (i < 0) return s[0] == 0 && s[1] == 0 && s[2] == 0 && s[3] == 0;
        
    for (int j = 0; j < s.length; j++) {
        if (nums[i] > s[j]) continue;
        s[j] -= nums[i];
        if (makesquareSub(nums, i - 1, s)) return true;
        s[j] += nums[i];
    }
        
    return false;
}

While accepted, this solution runs rather slowly (~450ms). So let's see what optimizations can be done to enhance the time complexity.

II -- DFS with sorting

In the recurrence relation above, we concluded that if nums[i] > sj, it cannot belong to the j-th group, which implies we needn't even bother to try that case. This condition is most likely to be met if we always choose the maximum element that is currently available in the subarray. Also note that the order of elements does not matter in the partition of the array. Therefore we can sort the input array in ascending order before rushing into DFS. This reduced the running time sharply to ~40ms.

public boolean makesquare(int[] nums) {
    if (nums.length < 4) return false;
        
    int perimeter = 0;
    for (int ele : nums) perimeter += ele;
    if (perimeter % 4 != 0) return false;
        
    Arrays.sort(nums);
    int side = perimeter / 4;

    return makesquareSub(nums, nums.length - 1, new int[] {side, side, side, side});
}
    
private boolean makesquareSub(int[] nums, int i, int[] s) {
    if (i < 0) return s[0] == 0 && s[1] == 0 && s[2] == 0 && s[3] == 0;
        
    for (int j = 0; j < s.length; j++) {
        if (nums[i] > s[j]) continue;
        s[j] -= nums[i];
        if (makesquareSub(nums, i - 1, s)) return true;
        s[j] += nums[i];
    }
        
    return false;
}

Note: It looks like the following solutions based on sequential ideas are not correct. See oliver_feng's comment below.

III -- DFS with sequential-partition

So far, the partitioning of the array is done simultaneously for the four disjoint groups. With the array sorted, it is also possible to break the partitioning process into four sequential parts such that each part will find each of the disjoint groups. (Note: sorting in ascending order is necessary now. We have to choose greedily (maximum element available) for each group, otherwise the total number of valid groups may decrease. Check out the case [3,3,3,3,1,1,1,1].)

To this end, let's define T(i, sum) which denotes whether sum can be written as the summation of some elements in the subarray nums[0, i], with each element used at most once. If so, a group is found such that the sum of its elements will be sum. Without much efforts, we can obtain the following recurrence relation:

T(i, sum) = T(i - 1, sum - nums[i]) || T(i - 1, sum) 

The two cases on the right hand side correspond to whether we choose the last element nums[i] to form the sum or not. And again, it cannot be chosen if nums[i] > sum.

The termination condition is either sum = 0 in which case a group is found or i = -1 in which case no such group exists.

So far everything looks pretty similar to what we have discussed in part I. However, a key difference here is now we need to explicitly mark the elements that are chosen to form previous groups since they are no longer available. Some straightforward ways would be using a boolean array or an integer(and do bit manipulations). Since the elements in the input array are initially positive, it is also possible to mark visited elements by negating their values.

Here is the java program for sequential partition, with running time further reduced to ~15ms.

public boolean makesquare(int[] nums) {
    if (nums.length < 4) return false;
        
    int perimeter = 0;
    for (int ele : nums) perimeter += ele;
    if (perimeter % 4 != 0) return false;
        
    Arrays.sort(nums);
    int side = perimeter / 4;

    for (int i = 0; i < 3; i++) {
        if (!makesquareSub(nums, nums.length - 1, side)) return false;
    }
        
    return true;
}
    
private boolean makesquareSub(int[] nums, int i, int sum) {
    if (sum == 0) return true;
    if (i < 0) return false;
        
    if (nums[i] > 0 && nums[i] <= sum) {
        nums[i] = -nums[i];
        if (makesquareSub(nums, i - 1, sum + nums[i])) return true;
        nums[i] = -nums[i];
    }
        
    return makesquareSub(nums, i - 1, sum);
}

IV -- DFS with DP

For all solutions above (T(i, s1, s2, s3, s4) or T(i, sum)), we did not take into account the possibility that there is overlapping among the subproblems.

For T(i, s1, s2, s3, s4), each subproblem is characterized by five integers while for T(i, sum), it is two. I would say the probability of overlapping subproblems for the former is relatively lower than the latter. So I only implemented DP for the sequential-partition case. However the running time (~25ms) did not get further improved, possibly due to the fact that the probability for overlapping subproblems is already rather low (if any) for this case with small number of elements. Not sure if DP will prevail for larger input size though.

Last word about the following DP solution: naively we would use an array of HashMap (let's denote it as map) to memorize intermediate results, with i indexed into the array and sum as the key of the corresponding HashMap. However, due to our greedy strategy for finding each group, if two subproblems T1(i1, sum1) and T2(i2, sum2) have the same sum, i.e., sum1 = sum2, then the one with larger index i will always be solved before the other. Since T(i, sum) = true implies T(j, sum) = true with j >= i, and T(i, sum) = false implies T(j, sum) = false with j <= i, it is sufficient to use only the sum factor to characterize subproblems that yield false solution (if any of the subproblem yields true, the recursion will rewind and eventually terminate). Therefore you will see only a HashSet is used in the following implementation.

public boolean makesquare(int[] nums) {
    if (nums.length < 4) return false;
        
    int perimeter = 0;
    for (int ele : nums) perimeter += ele;
    if (perimeter % 4 != 0) return false;
        
    Arrays.sort(nums);
    int side = perimeter / 4;
        
    for (int i = 0; i < 3; i++) {
        if (!makesquareSub(nums, nums.length - 1, side, new HashSet<>())) return false;
    }
    
    return true;
}
    
private boolean makesquareSub(int[] nums, int i, int sum, Set<Integer> set) {
    if (sum == 0) return true;
    if (set.contains(sum) || i < 0) return false;
    
    if (nums[i] > 0 && nums[i] <= sum) {
        nums[i] = -nums[i];
        if (makesquareSub(nums, i - 1, sum + nums[i], set)) return true;
        nums[i] = -nums[i];
    }
        
    if (makesquareSub(nums, i - 1, sum, set)) return true;
        
    set.add(sum);
    return false;
}

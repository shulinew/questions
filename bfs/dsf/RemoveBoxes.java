/*
Given several boxes with different colors represented by different positive numbers.
You may experience several rounds to remove boxes until there is no box left. Each time you can choose some continuous 
boxes with the same color (composed of k boxes, k >= 1), remove them and get k*k points.
Find the maximum points you can get. 
*/

/*
Since the input is an array, let’s begin with the usual approach by breaking it down with the original problem applied to each of the subarrays.

Let the input array be boxes with length n. Define T(i, j) as the maximum points one can get by removing boxes of the subarray boxes[i, j] 
(both inclusive). The original problem is identified as T(0, n - 1) and the termination condition is as follows:

    T(i, i - 1) = 0: no boxes so no points.
    T(i, i) = 1: only one box left so the maximum point is 1.

Next let’s try to work out the recurrence relation for T(i, j). Take the first box boxes[i](i.e., the box at index i) as an example. What are 
the possible ways of removing it? (Note: we can also look at the last box and the analyses turn out to be the same.)

If it happens to have a color that you dislike, you’ll probably say “I don’t like this box so let’s get rid of it now”. In this case, you will 
first get 1 point for removing this poor box. But still you want maximum points for the remaining boxes, which by definition is T(i + 1, j).
In total your points will be 1 + T(i + 1, j).

But later after reading the rules more carefully, you realize that you might get more points if this box (boxes[i]) can be removed together 
with other boxes of the same color. For example, if there are two such boxes, you get 4 points by removing them simultaneously, instead of 2 
by removing them one by one. So you decide to let it stick around a little bit longer until there is another box of the same color (whose index 
is m) becomes its neighbor. Note up to this moment all boxes from index i + 1 to index m - 1 would have been removed. So if we again aim for 
maximum points, the points gathered so far will be T(i + 1, m - 1). What about the remaining boxes?

At this moment, the boxes we left behind consist of two parts: the one at index i (boxes[i]) and those of the subarray boxes[m, j],
 with the former bordering the latter from the left. Apparently there is no way applying the definition of the subproblem to the subarray 
 boxes[m, j], since we have some extra piece of information that is not included in the definition. In this case, I shall call that the 
 definition of the subproblem is not self-contained and its solution relies on information external to the subproblem itself.

Another example of problem that does not have self-contained subproblems is leetcode 312. Burst Balloons, where the maximum coins of subarray 
nums[i, j] depend on the two numbers adjacent to nums[i] on the left and to nums[j] on the right. So you may find some similarities between 
these two problems.

Problems without self-contained subproblems usually don’t have well-defined recurrence relations, which renders it impossible to be solved 
recursively. The cure to this issue can sound simple and straightforward: modify the definition of the problem to absorb the external i
nformation so that the new one is self-contained.

So let’s see how we can redefine T(i, j) to make it self-contained. First let’s identify the external information. On the one hand, from the 
point of view of the subarray boxes[m, j], it knows nothing about the number (denoted by k) of boxes of the same color as boxes[m]to its left. 
On the other hand, given this number k, the maximum points can be obtained from removing all these boxes is fixed. Therefore the external 
information to T(i, j) is this k. Next let’s absorb this extra piece of information into the definition of T(i, j) and redefine it as T(i, j, k) 
which denotes the maximum points possible by removing the boxes of subarray boxes[i, j] with k boxes attached to its left of the same color as
 boxes[i]. Lastly let’s reexamine some of the statements above:

    Our original problem now becomes T(0, n - 1, 0), since there is no boxes attached to the left of the input array at the beginning.

    The termination conditions now will be:
    a. T(i, i - 1, k) = 0: no boxes so no points, and this is true for any k (you can interpret it as nowhere to attach the boxes).
    b. T(i, i, k) = (k + 1) * (k + 1): only one box left in the subarray but we’ve already got k boxes of the same color attached to its left, 
    so the total number of boxes of the same color is (k + 1) and the maximum point is (k + 1) * (k + 1).

    The recurrence relation is as follows and the maximum points will be the larger of the two cases:
    a. If we remove boxes[i] first, we get (k + 1) * (k + 1) + T(i + 1, j, 0) points, where for the first term, instead of 1 we again get 
    (k + 1) * (k + 1) points for removing boxes[i] due to the attached boxes to its left; and for the second term there will be no attached 
    boxes so we have the 0 in this term.
    b. If we decide to attach boxes[i] to some other box of the same color, say boxes[m], then from our analyses above, the total points will 
    be T(i + 1, m - 1, 0) + T(m, j, k + 1), where for the first term, since there is no attached boxes for subarray boxes[i + 1, m - 1], 
    we have k = 0 for this part; while for the second term, the total number of attached boxes for subarray boxes[m, j] will increase by 1 
    because apart from the original k boxes, we have to account for boxes[i]now, so we have k + 1 for this term. But we are not done yet. 
    What if there are multiple boxes of the same color as boxes[i] within subarray boxes[i + 1, j]? We have to try each of them and choose the 
    one that yields the maximum points. Therefore the final answer for this case will be: max(T(i + 1, m - 1, 0) + T(m, j, k + 1)) where 
    i < m <= j && boxes[i] == boxes[m].

Before we get to the actual code, it’s not hard to discover that there is overlapping among the subproblems T(i, j, k), therefore it’s qualified 
as a DP problem and its intermediate results should be cached for future lookup. Here each subproblem is characterized by three integers 
(i, j, k), all of which are bounded, i.e, 0 <= i, j, k < n, so a three-dimensional array (n by n by n) will be good enough for the cache.

Finally here are the two solutions, one for top-down DP and the other for bottom-up DP. From the bottom-up solution, the time complexity 
will be O(n^4) and the space complexity will be O(n^3).
*/

public RemoveBoxes {
    public int removeBoxes(int [] boxes) {
        int maxPoints = 0;
        int currentPoints = 0;
        for (int i = 0; i < boxes.length; i++) {
            currentPoints += helper(boxes, i);
        }

    }
    private int helper(int [] boxes, int i) {
        int count = 1;
        if (i == boxes.length - 1) {
            return count;
        }
        for (int j = i; j < boxes.length; j++) {
            if (boxes[j] == boxes[i]) {
                count++;
            } else {
                return count * count + helper(boxes, j+1);
            }
        }
    }
// Top down
    public int removeBoxes(int[] boxes) {
        int n = boxes.length;
        int[][][] dp = new int[n][n][n];
        return removeBoxesSub(boxes, 0, n - 1, 0, dp);
    }
        
    private int removeBoxesSub(int[] boxes, int i, int j, int k, int[][][] dp) {
        if (i > j) return 0;
        if (dp[i][j][k] > 0) return dp[i][j][k];
        
        for (; i + 1 <= j && boxes[i + 1] == boxes[i]; i++, k++); // optimization: all boxes of the same color counted continuously from the first box should be grouped together
        int res = (k + 1) * (k + 1) + removeBoxesSub(boxes, i + 1, j, 0, dp);
        
        for (int m = i + 1; m <= j; m++) {
            if (boxes[i] == boxes[m]) {
                res = Math.max(res, removeBoxesSub(boxes, i + 1, m - 1, 0, dp) + removeBoxesSub(boxes, m, j, k + 1, dp));
            }
        }           
        dp[i][j][k] = res;
        return res;
    }

    //Bottom up
    public int removeBoxes(int[] boxes) {
        int n = boxes.length;
        int[][][] dp = new int[n][n][n];
            
        for (int j = 0; j < n; j++) {
            for (int k = 0; k <= j; k++) {
                dp[j][j][k] = (k + 1) * (k + 1);
            }
        }
    	
        for (int l = 1; l < n; l++) {
            for (int j = l; j < n; j++) {
                int i = j - l;     
                for (int k = 0; k <= i; k++) {
                    int res = (k + 1) * (k + 1) + dp[i + 1][j][0];
                        
                    for (int m = i + 1; m <= j; m++) {
                        if (boxes[m] == boxes[i]) {
                            res = Math.max(res, dp[i + 1][m - 1][0] + dp[m][j][k + 1]);
                        }
                    }
                        
                    dp[i][j][k] = res;
                }
            }
        }        
        return (n == 0 ? 0 : dp[0][n - 1][0]);
    }
    /*
    When facing this problem, I am keeping thinking how to simulate the case when boxes[i] == boxes[j] when i and j are not consecutive. 
    It turns out that the dp matrix needs one more dimension to store such state. So we are going to define the state as

dp[i][j][k] represents the max points from box[i] to box[j] with k boxes whose values equal to box[i]

The transformation function is as below

dp[i][j][k] = max(dp[i+1][m-1][1] + dp[m][j][k+1]) when box[i] = box[m]

    */
  public int removeBoxes(int[] boxes) {
        if (boxes == null || boxes.length == 0) {
            return 0;
        }

        int size = boxes.length;
        int[][][] dp = new int[size][size][size];

        return get(dp, boxes, 0, size-1, 1);
    }

    private int get(int[][][] dp, int[] boxes, int i, int j, int k) {
        if (i > j) {
            return 0;
        } else if (i == j) {
            return k * k;
        } else if (dp[i][j][k] != 0) {
            return dp[i][j][k];
        } else {
            int temp = get(dp, boxes, i + 1, j, 1) + k * k;

            for (int m = i + 1; m <= j; m++) {
                if (boxes[i] == boxes[m]) {
                    temp = Math.max(temp, get(dp, boxes, i + 1, m - 1, 1) + get(dp, boxes, m, j, k + 1));
                }
            }

            dp[i][j][k] = temp;
            return temp;
        }

    public int removeBoxes(int[] boxes) {
        List<Integer> colors = new ArrayList<>();
        List<Integer> lens = new ArrayList<>();
        // preprocessing
        // [1,1,1,3,3,2,3,3,3,1,1] would become
        // colors : [1,3,2,3,1]
        // lens :   [3,2,1,3,2]
        for (int box : boxes) {
            if (!colors.isEmpty() && colors.get(colors.size() - 1) == box) {
                // continuous, increase length count by 1
                lens.set(lens.size() - 1, lens.get(lens.size() - 1) + 1);
            } else {
                // new color
                colors.add(box);
                lens.add(1);
            }
        }
        int N = boxes.length;
        int M = colors.size();
        // dp[i][j][k] means the maximal score for colors[i:j] with k boxes of same color merged after j
        // i and j are inclusive, so dp[0][M - 1][0] will be the final answer
        int[][][] dp = new int[M][M][N];
        return dfs(colors, lens, 0, M - 1, 0, dp);
    }
    
    // top-down dfs search with memoization
    private int dfs(List<Integer> colors, List<Integer> lens, int l, int r, int k, int[][][] dp) {
        if (l > r) return 0;
        if (dp[l][r][k] > 0) return dp[l][r][k];
        // merging boxes with colors[r]
        int score = dfs(colors, lens, l, r - 1, 0, dp) + (lens.get(r) + k) * (lens.get(r) + k);
        // merge boxes with colors[l:i] and colors[l + 1:r - 1] where i from l to r - 1
        for (int i = l; i < r; i++) {
            if (colors.get(i) == colors.get(r)) {
                // notice here : since we use top-down approach, colors[i + 1:r - 1] has already been merged, so k = 0;
                // so color[i] is adjacent to color[r] now
                score = Math.max(score, 
                    dfs(colors, lens, l, i, lens.get(r) + k, dp) + dfs(colors, lens, i + 1, r - 1, 0, dp));
            }
        }
        dp[l][r][k] = score;
        return score;
    }
}
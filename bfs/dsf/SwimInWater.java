/*
On an N x N grid, each square grid[i][j] represents the elevation at that point (i,j).

Now rain starts to fall. At time t, the depth of the water everywhere is t. You can swim from a square to another 
4-directionally adjacent square if and only if the elevation of both squares individually are at most t. You can swim infinite 
distance in zero time. Of course, you must stay within the boundaries of the grid during your swim.

You start at the top left square (0, 0). What is the least time until you can reach the bottom right square (N-1, N-1)?
*/

public class SwimInWater {

    final static int[][] steps = {{0,1},{0,-1}, {1,0},{-1,0}};
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        int[][] max = new int[n][n];
        for(int[] line : max)
            Arrays.fill(line, Integer.MAX_VALUE);
        dfs(grid, max, 0, 0, grid[0][0]);
        return max[n-1][n-1];
    }

    private void dfs(int[][] grid, int[][] max, int x, int y, int cur) {
        int n = grid.length;
        if (x < 0 || x >= n || y < 0 || y >= n || Math.max(cur, grid[x][y]) >= max[x][y])
            return;
        max[x][y] = Math.max(cur, grid[x][y]);
        for(int[] s : steps) {
            dfs(grid, max, x + s[0], y + s[1], max[x][y]);
        }
    }

/* keep a priority queue of which square we can walk in next. We always walk in the smallest one that is 4-directionally adjacent to 
ones we've visited.
When we reach the target, the largest number we've visited so far is the answer.
*/
    public int swimInWater1(int[][] grid) {
        int N = grid.length;
        Set<Integer> seen = new HashSet();
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((k1, k2) ->
                grid[k1 / N][k1 % N] - grid[k2 / N][k2 % N]);
        pq.offer(0);
        int ans = 0;

        int[] dr = new int[]{1, -1, 0, 0};
        int[] dc = new int[]{0, 0, 1, -1};

        while (!pq.isEmpty()) {
            int k = pq.poll();
            int r = k / N, c = k % N;
            ans = Math.max(ans, grid[r][c]);
            if (r == N-1 && c == N-1) return ans;

            for (int i = 0; i < 4; ++i) {
                int cr = r + dr[i], cc = c + dc[i];
                int ck = cr * N + cc;
                if (0 <= cr && cr < N && 0 <= cc && cc < N && !seen.contains(ck)) {
                    pq.offer(ck);
                    seen.add(ck);
                }
            }
        }
    }

    ////
    /* Whether the swim is possible is a monotone function with respect to time, so we can binary search this function for the 
    correct time: the smallest T for which the swim is possible.
    Say we guess that the correct time is T. To check whether it is possible, we perform a simple depth-first search where we can 
    only walk in squares that are at most T. */
    public int swimInWater2(int[][] grid) {
        int N = grid.length;
        int lo = grid[0][0], hi = N * N;
        while (lo < hi) {
            int mi = lo + (hi - lo) / 2;
            if (!possible(mi, grid)) {
                lo = mi + 1;
            } else {
                hi = mi;
            }
        }
        return lo;
    }

    public boolean possible(int T, int[][] grid) {
        int N = grid.length;
        Set<Integer> seen = new HashSet();
        seen.add(0);
        int[] dr = new int[]{1, -1, 0, 0};
        int[] dc = new int[]{0, 0, 1, -1};

        Stack<Integer> stack = new Stack();
        stack.add(0);

        while (!stack.empty()) {
            int k = stack.pop();
            int r = k / N, c = k % N;
            if (r == N-1 && c == N-1) return true;

            for (int i = 0; i < 4; ++i) {
                int cr = r + dr[i], cc = c + dc[i];
                int ck = cr * N + cc;
                if (0 <= cr && cr < N && 0 <= cc && cc < N
                        && !seen.contains(ck) && grid[cr][cc] <= T) {
                    stack.add(ck);
                    seen.add(ck);
                }
            }
        }

        return false;
    }
}
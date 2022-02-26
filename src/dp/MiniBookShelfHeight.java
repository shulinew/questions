package dp;

public class MiniBookShelfHeight {
    //[[1,1],[2,3],[2,3],[1,1],[1,1],[1,1],[1,2]]
    //4
    public int minHeightShelves(int[][] books, int shelf_width) {
        int booksCount = books.length;
        // dp[i] is the minimum height for books[i]
        int[] dp = new int[booksCount];
        dp[0] = 0;

        for (int i = 1; i <= books.length; ++i) {
            int width = books[i-1][0];
            int height = books[i-1][1];
            dp[i] = dp[i-1] + height;
            for (int j = i - 1; j > 0 && width + books[j-1][0] <= shelf_width; --j) {
                height = Math.max(height, books[j-1][1]);
                width += books[j-1][0];
                dp[i] = Math.min(dp[i], dp[j-1] + height);
            }
        }
        return dp[books.length];
    }
}

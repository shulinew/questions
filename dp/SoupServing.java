public class SoupServing {
   public double soupServings(int N) {
       if (N > 5000) {
           return 1.0;
       }
       return helper((N + 24)/25, (N + 24)/25, new double[200][200]);
    //    return helper(N, N, new Double[N+1][N+1]);
   }
   private double helper(int A, int B, Double[][] memo) {
      if (A <= 0 && B <= 0) {
          return 0.5;
      }
      if (A <= 0) {
          return 1.0;
      }
      if (B <= 0) {
          return 0.0;
      }
      if (memo[A][B] != null) {
          return memo[A][B];
      }
      memo[A][B] = 0.0;
      int[] serveA = {100, 75, 50, 25};
      int[] serveB = {0, 25, 50, 75};
      for (int i = 0; i < 4; i++) {
          memo[A][B] += helper(A - serveA[i], B - serveB[i], memo);
      }
      return memo[A][B] *= 0.25;
   }

    private double helper(int a, int b, double[][] memo) {
        if (a <= 0 && b <= 0){
            return 0.5;
        }
        if (a <= 0) {
            return 1.0;
        }
        if (b <= 0) {
            return 0.0;
        }
        if (memo[a][b] > 0) {
            return memo[a][b];
        }
        memo[a][b] = 0.25 * (helper(a-4, b, memo) + helper(a-3, b-1, memo) + helper(a-2, b-2, memo) + helper(a-1, b-3, memo));
        return memo[a][b];
    }
}
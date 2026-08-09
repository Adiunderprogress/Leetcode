class Solution {
    public int stoneGameII(int[] piles) {
        int n = piles.length;
        
        // suffixSum[i] stores the sum of piles from index i to n - 1
        int[] suffixSum = new int[n];
        suffixSum[n - 1] = piles[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + piles[i];
        }

        // dp[i][M] stores max stones obtainable starting at index i with current M
        int[][] dp = new int[n][n + 1];

        return helper(0, 1, piles, suffixSum, dp);
    }

    private int helper(int i, int M, int[] piles, int[] suffixSum, int[][] dp) {
        int n = piles.length;

        // Base Case: If remaining piles can all be taken in one turn
        if (i + 2 * M >= n) {
            return suffixSum[i];
        }

        // Return cached result if already computed
        if (dp[i][M] != 0) {
            return dp[i][M];
        }

        int maxStones = 0;

        // Try taking X piles where 1 <= X <= 2 * M
        for (int X = 1; X <= 2 * M; X++) {
            int nextM = Math.max(M, X);
            // Current player gets total remaining minus what the opponent gets best
            int currentStones = suffixSum[i] - helper(i + X, nextM, piles, suffixSum, dp);
            maxStones = Math.max(maxStones, currentStones);
        }

        dp[i][M] = maxStones;
        return maxStones;
    }
}
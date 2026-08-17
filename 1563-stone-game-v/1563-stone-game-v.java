class Solution {
    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        int[] prefixSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + stoneValue[i];
        }

        int[][] dp = new int[n][n];

        // Length of the subarray
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                // Try all possible split points k
                for (int k = i; k < j; k++) {
                    int leftSum = prefixSum[k + 1] - prefixSum[i];
                    int rightSum = prefixSum[j + 1] - prefixSum[k + 1];

                    if (leftSum < rightSum) {
                        dp[i][j] = Math.max(dp[i][j], leftSum + dp[i][k]);
                    } else if (leftSum > rightSum) {
                        dp[i][j] = Math.max(dp[i][j], rightSum + dp[k + 1][j]);
                    } else {
                        dp[i][j] = Math.max(dp[i][j], leftSum + Math.max(dp[i][k], dp[k + 1][j]));
                    }
                }
            }
        }

        return dp[0][n - 1];
    }
}
class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        // dp[i] represents the max score difference from index i to the end
        int[] dp = new int[n + 1];
        
        // Iterate backwards from the end of the array
        for (int i = n - 1; i >= 0; i--) {
            int takeSum = 0;
            dp[i] = Integer.MIN_VALUE;
            
            // Try taking 1, 2, or 3 stones
            for (int k = 1; k <= 3 && i + k - 1 < n; k++) {
                takeSum += stoneValue[i + k - 1];
                dp[i] = Math.max(dp[i], takeSum - dp[i + k]);
            }
        }
        
        if (dp[0] > 0) {
            return "Alice";
        } else if (dp[0] < 0) {
            return "Bob";
        } else {
            return "Tie";
        }
    }
}
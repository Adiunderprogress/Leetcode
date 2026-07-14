class Solution {
    public int subsequencePairCount(int[] nums) {
        int MOD = 1_000_000_007;
        
        // Find the maximum value in nums to bound our DP array
        int maxVal = 0;
        for (int x : nums) {
            maxVal = Math.max(maxVal, x);
        }
        
        // Precompute GCDs for O(1) lookups
        // Notice that gcd(0, x) = x, which elegantly handles adding elements to an empty sequence
        int[][] gcdMap = new int[maxVal + 1][maxVal + 1];
        for (int i = 0; i <= maxVal; i++) {
            for (int j = 0; j <= maxVal; j++) {
                int a = i, b = j;
                while (b != 0) {
                    int temp = b;
                    b = a % b;
                    a = temp;
                }
                gcdMap[i][j] = a;
            }
        }
        
        // dp[g1][g2] stores the number of valid pairs of subsequences
        int[][] dp = new int[maxVal + 1][maxVal + 1];
        dp[0][0] = 1; // 1 way to have two empty subsequences
        
        for (int x : nums) {
            int[][] nextDp = new int[maxVal + 1][maxVal + 1];
            
            for (int g1 = 0; g1 <= maxVal; g1++) {
                for (int g2 = 0; g2 <= maxVal; g2++) {
                    if (dp[g1][g2] == 0) continue;
                    
                    int ways = dp[g1][g2];
                    
                    // 1. Skip the current element
                    nextDp[g1][g2] = (nextDp[g1][g2] + ways) % MOD;
                    
                    // 2. Add current element to seq1
                    int newG1 = gcdMap[g1][x];
                    nextDp[newG1][g2] = (nextDp[newG1][g2] + ways) % MOD;
                    
                    // 3. Add current element to seq2
                    int newG2 = gcdMap[g2][x];
                    nextDp[g1][newG2] = (nextDp[g1][newG2] + ways) % MOD;
                }
            }
            dp = nextDp;
        }
        
        // Sum up cases where both subsequences are non-empty and have the exact same GCD
        int ans = 0;
        for (int g = 1; g <= maxVal; g++) {
            ans = (ans + dp[g][g]) % MOD;
        }
        
        return ans;
    }
}
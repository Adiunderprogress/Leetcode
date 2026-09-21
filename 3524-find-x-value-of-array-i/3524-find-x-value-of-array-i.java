class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] result = new long[k];
        // dp[r] stores the number of contiguous subarrays ending at the current index
        // whose product modulo k equals r
        long[] dp = new long[k];

        for (int num : nums) {
            int currentRem = num % k;
            long[] nextDp = new long[k];

            // Subarray starting at the current element itself
            nextDp[currentRem]++;

            // Extend previously existing subarrays to the current element
            for (int r = 0; r < k; r++) {
                if (dp[r] > 0) {
                    int newRem = (r * currentRem) % k;
                    nextDp[newRem] += dp[r];
                }
            }

            // Accumulate counts for each remainder into the global result
            for (int r = 0; r < k; r++) {
                result[r] += nextDp[r];
            }

            dp = nextDp;
        }

        return result;
    }
}
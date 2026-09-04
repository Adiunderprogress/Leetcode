class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        
        // Step 1: Precompute suffix minimums
        // minSuffix[i] stores the minimum value in nums[i..n-1]
        int[] minSuffix = new int[n];
        minSuffix[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            minSuffix[i] = Math.min(nums[i], minSuffix[i + 1]);
        }
        
        // Step 2: Iterate through nums while tracking the prefix maximum
        int maxPrefix = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            maxPrefix = Math.max(maxPrefix, nums[i]);
            
            // Instability score: max(nums[0..i]) - min(nums[i..n-1])
            if (maxPrefix - minSuffix[i] <= k) {
                return i;
            }
        }
        
        return -1;
    }
}
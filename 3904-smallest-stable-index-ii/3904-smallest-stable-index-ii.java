class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        int[] minSuff = new int[n];
        
        // Build suffix minimums array
        minSuff[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            minSuff[i] = Math.min(nums[i], minSuff[i + 1]);
        }
        
        // Track prefix maximum and find the smallest stable index
        int maxPref = 0;
        for (int i = 0; i < n; i++) {
            maxPref = Math.max(maxPref, nums[i]);
            if (maxPref - minSuff[i] <= k) {
                return i;
            }
        }
        
        return -1;
    }
}
class Solution {
    public int[] shuffle(int[] nums, int n) {
        // Create a result array of the same size
        int[] ans = new int[2 * n];
        int index = 0;
        
        // Alternately pick elements from both halves
        for (int i = 0; i < n; i++) {
            ans[index++] = nums[i];       // Place x_i
            ans[index++] = nums[i + n];   // Place y_i
        }
        
        return ans;
    }
}
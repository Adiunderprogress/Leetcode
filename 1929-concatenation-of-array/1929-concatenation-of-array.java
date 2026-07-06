class Solution {
    public int[] getConcatenation(int[] nums) {
        int n = nums.length;
        // Create a new array of double the size
        int[] ans = new int[2 * n];
        
        // Loop through the original array and fill the answer array
        for (int i = 0; i < n; i++) {
            ans[i] = nums[i];       // Fill the first half
            ans[i + n] = nums[i];   // Fill the second half
        }
        
        return ans;
    }
}
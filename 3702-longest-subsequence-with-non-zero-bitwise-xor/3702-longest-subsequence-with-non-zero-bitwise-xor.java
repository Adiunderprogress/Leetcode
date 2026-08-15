class Solution {
    public int longestSubsequence(int[] nums) {
        int n = nums.length;
        int totalXor = 0;
        boolean allZeros = true;
        
        for (int x : nums) {
            totalXor ^= x;
            if (x != 0) {
                allZeros = false;
            }
        }
        
        // If all elements are 0, no non-zero XOR subsequence is possible
        if (allZeros) {
            return 0;
        }
        
        // If the total XOR of all elements is non-zero, take the whole array
        if (totalXor != 0) {
            return n;
        }
        
        // If total XOR is 0, we can always drop one element to make it non-zero
        return n - 1;
    }
}
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int maxCount = 0;
        int count = 0;
        
        for (int num : nums) {
            if (num == 1) {
                count++;
            } else {
                // Update maxCount and reset current streak
                maxCount = Math.max(maxCount, count);
                count = 0;
            }
        }
        
        // Final check in case the array ends with 1s
        return Math.max(maxCount, count);
    }
}
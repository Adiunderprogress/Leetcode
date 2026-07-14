class Solution {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        // Since 0 <= nums[i] <= 100, we create a bucket array of size 101
        int[] count = new int[101];
        int[] result = new int[nums.length];
        
        // Step 1: Count the frequency of each number
        for (int num : nums) {
            count[num]++;
        }
        
        // Step 2: Accumulate the counts to get the number of elements smaller than or equal to the index
        for (int i = 1; i <= 100; i++) {
            count[i] += count[i - 1];
        }
        
        // Step 3: Populate the result array
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                result[i] = 0;
            } else {
                result[i] = count[nums[i] - 1];
            }
        }
        
        return result;
    }
}
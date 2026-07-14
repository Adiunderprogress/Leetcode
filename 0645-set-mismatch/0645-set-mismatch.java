class Solution {
    public int[] findErrorNums(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            int correctIndex = nums[i] - 1;
            // Swap if the current number is not at its correct position
            // AND the target position doesn't already have the correct number
            if (nums[i] != nums[correctIndex]) {
                swap(nums, i, correctIndex);
            } else {
                i++;
            }
        }

        // Search for the mismatch
        for (int index = 0; index < nums.length; index++) {
            if (nums[index] != index + 1) {
                // Duplicate is the value present, Missing is the expected index + 1
                return new int[] {nums[index], index + 1};
            }
        }

        return new int[] {-1, -1};
    }

    private void swap(int[] nums, int first, int second) {
        int temp = nums[first];
        nums[first] = nums[second];
        nums[second] = temp;
    }
}
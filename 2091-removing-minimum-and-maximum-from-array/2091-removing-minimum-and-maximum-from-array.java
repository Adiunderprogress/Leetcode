class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        if (n <= 2) return n;

        int minIdx = 0;
        int maxIdx = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] < nums[minIdx]) {
                minIdx = i;
            }
            if (nums[i] > nums[maxIdx]) {
                maxIdx = i;
            }
        }

        int L = Math.min(minIdx, maxIdx);
        int R = Math.max(minIdx, maxIdx);

        int deleteBothFromLeft = R + 1;
        int deleteBothFromRight = n - L;
        int deleteSplit = (L + 1) + (n - R);

        return Math.min(deleteBothFromLeft, Math.min(deleteBothFromRight, deleteSplit));
    }
}
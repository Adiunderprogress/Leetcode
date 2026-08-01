class Solution {
    private Integer[][] memo;

    public boolean predictTheWinner(int[] nums) {
        int n = nums.length;
        memo = new Integer[n][n];
        // If the max score difference for Player 1 from [0, n-1] is >= 0, Player 1 wins.
        return maxScoreDiff(nums, 0, n - 1) >= 0;
    }

    private int maxScoreDiff(int[] nums, int left, int right) {
        if (left == right) {
            return nums[left];
        }

        if (memo[left][right] != null) {
            return memo[left][right];
        }

        // Choose left or choose right and subtract the opponent's optimal response
        int chooseLeft = nums[left] - maxScoreDiff(nums, left + 1, right);
        int chooseRight = nums[right] - maxScoreDiff(nums, left, right - 1);

        return memo[left][right] = Math.max(chooseLeft, chooseRight);
    }
}
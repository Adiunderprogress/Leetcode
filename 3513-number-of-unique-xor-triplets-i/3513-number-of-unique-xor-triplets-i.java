class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;
        if (n <= 2) return n;

        int mask = n;
        mask |= mask >> 1;
        mask |= mask >> 2;
        mask |= mask >> 4;
        mask |= mask >> 8;
        mask |= mask >> 16;

        return mask + 1;
    }
}
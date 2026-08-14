class Solution {
    public int mySqrt(int x) {
        if (x == 0 || x == 1) {
            return x;
        }

        int left = 1;
        int right = x / 2;
        int ans = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Using long to prevent integer overflow with mid * mid
            long square = (long) mid * mid;

            if (square == x) {
                return mid;
            } else if (square < x) {
                ans = mid;        // mid is a valid floor candidate
                left = mid + 1;   // try finding a larger candidate
            } else {
                right = mid - 1;  // square is too large
            }
        }

        return ans;
    }
}
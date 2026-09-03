class Solution {
public:
    bool uniformArray(vector<int>& nums1) {
        int minOdd = INT_MAX;
        int minEven = INT_MAX;

        for (int x : nums1) {
            if (x % 2 != 0) {
                minOdd = min(minOdd, x);
            } else {
                minEven = min(minEven, x);
            }
        }

        // If there are no odd numbers, all elements are already even.
        if (minOdd == INT_MAX) return true;

        // If odd numbers exist, we can only make all elements odd if minOdd < minEven.
        return minOdd < minEven;
    }
};
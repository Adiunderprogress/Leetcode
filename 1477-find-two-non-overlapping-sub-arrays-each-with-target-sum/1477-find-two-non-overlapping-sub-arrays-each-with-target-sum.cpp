#include <vector>
#include <algorithm>

using namespace std;

class Solution {
public:
    int minSumOfLengths(vector<int>& arr, int target) {
        int n = arr.size();
        const int INF = 1e9;
        
        // min_len[i] stores the min length of a valid subarray ending at or before index i
        vector<int> min_len(n, INF);
        
        int left = 0, current_sum = 0;
        int min_total_sum = INF;
        int best_so_far = INF;

        for (int right = 0; right < n; ++right) {
            current_sum += arr[right];

            // Shrink window if current_sum exceeds target
            while (current_sum > target && left <= right) {
                current_sum -= arr[left];
                left++;
            }

            // Valid subarray found ending at `right`
            if (current_sum == target) {
                int current_len = right - left + 1;

                // Check if there is a valid non-overlapping subarray before `left`
                if (left > 0 && min_len[left - 1] != INF) {
                    min_total_sum = min(min_total_sum, current_len + min_len[left - 1]);
                }

                best_so_far = min(best_so_far, current_len);
            }

            // Record the minimum length subarray found up to `right`
            min_len[right] = best_so_far;
        }

        return min_total_sum == INF ? -1 : min_total_sum;
    }
};
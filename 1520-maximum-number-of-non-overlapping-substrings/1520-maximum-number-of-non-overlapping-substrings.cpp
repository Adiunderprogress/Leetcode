#include <vector>
#include <string>
#include <algorithm>

using namespace std;

class Solution {
public:
    vector<string> maxNumOfSubstrings(string s) {
        int n = s.length();
        vector<int> first(26, -1), last(26, -1);

        // Step 1: Record first and last occurrence of each character
        for (int i = 0; i < n; ++i) {
            int ch = s[i] - 'a';
            if (first[ch] == -1) {
                first[ch] = i;
            }
            last[ch] = i;
        }

        vector<pair<int, int>> validIntervals;

        // Step 2: Expand range for each character starting at its first occurrence
        for (int i = 0; i < 26; ++i) {
            if (first[i] == -1) continue;

            int l = first[i], r = last[i];
            bool isValid = true;

            for (int j = l; j <= r; ++j) {
                int ch = s[j] - 'a';
                if (first[ch] < l) {
                    // Left bound expands past starting index, skip
                    isValid = false;
                    break;
                }
                r = max(r, last[ch]);
            }

            if (isValid) {
                validIntervals.push_back({l, r});
            }
        }

        // Step 3: Sort by right endpoint to greedily pick non-overlapping intervals
        sort(validIntervals.begin(), validIntervals.end(), [](const pair<int, int>& a, const pair<int, int>& b) {
            return a.second < b.second;
        });

        vector<string> result;
        int lastEnd = -1;

        for (const auto& [l, r] : validIntervals) {
            if (l > lastEnd) {
                result.push_back(s.substr(l, r - l + 1));
                lastEnd = r;
            }
        }

        return result;
    }
};
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

class Solution {
public:
    string lexGreaterPermutation(string s, string target) {
        int n = s.length();
        vector<int> count(26, 0);
        for (char c : s) {
            count[c - 'a']++;
        }

        // Try to match target as long as possible
        vector<int> temp_count = count;
        int matched_length = 0;
        for (int i = 0; i < n; i++) {
            int char_idx = target[i] - 'a';
            if (temp_count[char_idx] > 0) {
                temp_count[char_idx]--;
                matched_length++;
            } else {
                break;
            }
        }

        // Backtrack from the matched prefix length down to 0 to find a pivot
        for (int i = matched_length; i >= 0; i--) {
            // Reconstruct frequency counts after prefix matching 0..i-1
            vector<int> cur_count = count;
            for (int k = 0; k < i; k++) {
                cur_count[target[k] - 'a']--;
            }

            // If we are at the end of the string, we cannot place a larger char at index n
            if (i == n) continue;

            // Find the smallest character strictly greater than target[i]
            for (int c = (target[i] - 'a') + 1; c < 26; c++) {
                if (cur_count[c] > 0) {
                    // Found valid pivot at index i with character 'a' + c
                    string res = target.substr(0, i);
                    res.push_back((char)('a' + c));
                    cur_count[c]--;

                    // Append remaining characters in sorted order
                    for (int j = 0; j < 26; j++) {
                        while (cur_count[j] > 0) {
                            res.push_back((char)('a' + j));
                            cur_count[j]--;
                        }
                    }
                    return res;
                }
            }
        }

        return "";
    }
};
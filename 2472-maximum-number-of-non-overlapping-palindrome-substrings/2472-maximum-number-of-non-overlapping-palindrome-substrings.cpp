#include <string>
#include <algorithm>

class Solution {
public:
    int maxPalindromes(std::string s, int k) {
        int n = s.length();
        int count = 0;
        int last_end = -1; // End index of the last chosen non-overlapping palindrome

        for (int center = 0; center < 2 * n - 1; ++center) {
            int left = center / 2;
            int right = left + (center % 2);

            while (left >= 0 && right < n && s[left] == s[right]) {
                int len = right - left + 1;
                
                // If it meets length requirement and starts after the previous chosen palindrome
                if (len >= k && left > last_end) {
                    count++;
                    last_end = right;
                    break; // Greedily take the shortest valid palindrome at this position
                }
                
                // Expand outward
                left--;
                right++;
            }
        }

        return count;
    }
};
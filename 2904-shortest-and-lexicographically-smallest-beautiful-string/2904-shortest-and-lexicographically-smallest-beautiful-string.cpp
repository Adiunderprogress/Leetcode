#include <string>

class Solution {
public:
    std::string shortestBeautifulSubstring(std::string s, int k) {
        int n = s.length();
        std::string res = "";
        
        for (int i = 0; i < n; ++i) {
            int ones = 0;
            for (int j = i; j < n; ++j) {
                if (s[j] == '1') {
                    ones++;
                }
                if (ones == k) {
                    std::string sub = s.substr(i, j - i + 1);
                    if (res.empty() || sub.length() < res.length() || 
                       (sub.length() == res.length() && sub < res)) {
                        res = sub;
                    }
                    break; // Moving j further only increases the length
                }
            }
        }
        
        return res;
    }
};
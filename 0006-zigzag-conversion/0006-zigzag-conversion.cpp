#include <string>

class Solution {
public:
    string convert(string s, int numRows) {
        // Edge cases: no zigzagging needed
        if (numRows == 1 || s.length() <= numRows) {
            return s;
        }

        string result = "";
        result.reserve(s.length()); // Pre-allocate memory for performance
        int cycleLen = 2 * numRows - 2;
        int n = s.length();

        // Process row by row
        for (int i = 0; i < numRows; i++) {
            for (int j = i; j < n; j += cycleLen) {
                // Append the vertical column character
                result += s[j];
                
                // If it's a middle row, calculate and append the diagonal character
                if (i != 0 && i != numRows - 1) {
                    int diagonalIndex = j + cycleLen - 2 * i;
                    if (diagonalIndex < n) {
                        result += s[diagonalIndex];
                    }
                }
            }
        }

        return result;
    }
};
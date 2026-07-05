class Solution {
    public boolean isPalindrome(int x) {
        // Edge cases: 
        // 1. Negative numbers are not palindromes (e.g., -121 reads as 121-)
        // 2. Numbers ending in 0 are not palindromes, except 0 itself (e.g., 10, 100)
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int reversedHalf = 0;
        // Keep reversing the digits of the second half until it meets or exceeds the first half
        while (x > reversedHalf) {
            reversedHalf = (reversedHalf * 10) + (x % 10);
            x /= 10;
        }

        // For even length: x == reversedHalf (e.g., 1221 becomes x = 12, reversedHalf = 12)
        // For odd length: x == reversedHalf / 10 to get rid of the middle digit (e.g., 12321 becomes x = 12, reversedHalf = 123)
        return x == reversedHalf || x == reversedHalf / 10;
    }
}

class Solution {
    public boolean isPalindrome(int x) {
        // Special cases:
        // As discussed above, when x < 0, x is not a palindrome.
        // Also if the last digit of the number is 0, for it to be a palindrome,
        // the first digit of the number also needs to be 0. Only 0 satisfies this.
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int reversedHalf = 0;
        while (x > reversedHalf) {
            reversedHalf = (reversedHalf * 10) + (x % 10);
            x /= 10;
        }

        // When the length is an odd number, we can get rid of the middle digit by reversedHalf/10
        // For example when the input is 12321, at the end of the while loop we get x = 12, reversedHalf = 123,
        // since the middle digit doesn't matter in palindrome, we can simply get rid of it.
        return x == reversedHalf || x == reversedHalf / 10;
    }
}
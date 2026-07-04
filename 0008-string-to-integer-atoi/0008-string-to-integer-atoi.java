class Solution {
    public int myAtoi(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int i = 0;
        int n = s.length();

        // Step 1: Skip leading whitespaces
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }

        // If the string is empty or only contained whitespaces
        if (i == n) {
            return 0;
        }

        // Step 2: Check for sign
        int sign = 1;
        if (s.charAt(i) == '+' || s.charAt(i) == '-') {
            sign = (s.charAt(i) == '-') ? -1 : 1;
            i++;
        }

        // Step 3 & 4: Convert digits and handle overflow/underflow
        int result = 0;
        while (i < n) {
            char ch = s.charAt(i);
            
            // Stop if the character is not a valid digit
            if (ch < '0' || ch > '9') {
                break;
            }

            int digit = ch - '0';

            // Check for 32-bit signed integer overflow bounds
            // Integer.MAX_VALUE is 2147483647, Integer.MIN_VALUE is -2147483648
            if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && digit > 7)) {
                return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            result = result * 10 + digit;
            i++;
        }

        return result * sign;
    }
}
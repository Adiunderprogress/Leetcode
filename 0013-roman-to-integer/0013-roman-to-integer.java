class Solution {
    public int romanToInt(String s) {
        int ans = 0;
        int num = 0;
        int prev = 0;

        // Loop through the string from right to left
        for (int i = s.length() - 1; i >= 0; i--) {
            switch (s.charAt(i)) {
                case 'I': num = 1; break;
                case 'V': num = 5; break;
                case 'X': num = 10; break;
                case 'L': num = 50; break;
                case 'C': num = 100; break;
                case 'D': num = 500; break;
                case 'M': num = 1000; break;
            }
            
            // If the current value is less than the previous value, subtract it
            if (num < prev) {
                ans -= num;
            } else {
                // Otherwise, add it
                ans += num;
            }
            // Update the previous value for the next iteration
            prev = num;
        }
        return ans;
    }
}
class Solution {
    public int maximumLengthSubstring(String s) {
        int[] count = new int[26];
        int left = 0;
        int maxLength = 0;

        for (int right = 0; right < s.length(); right++) {
            // Expand the window by including the current character
            char currentChar = s.charAt(right);
            count[currentChar - 'a']++;

            // If any character count exceeds 2, shrink the window from the left
            while (count[currentChar - 'a'] > 2) {
                count[s.charAt(left) - 'a']--;
                left++;
            }

            // Update the maximum substring length found so far
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}
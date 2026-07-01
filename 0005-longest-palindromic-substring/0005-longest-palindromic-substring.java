class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        
        int start = 0, end = 0;
        
        for (int i = 0; i < s.length(); i++) {
            // Case 1: Odd-length palindromes (centered at a single character like "aba")
            int len1 = expandAroundCenter(s, i, i);
            
            // Case 2: Even-length palindromes (centered between two characters like "bba")
            int len2 = expandAroundCenter(s, i, i + 1);
            
            // Get the maximum length found
            int len = Math.max(len1, len2);
            
            // If we found a longer palindrome, update our tracking indices
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        
        return s.substring(start, end + 1);
    }
    
    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // Returns the length of the valid palindrome found
        return right - left - 1;
    }
}
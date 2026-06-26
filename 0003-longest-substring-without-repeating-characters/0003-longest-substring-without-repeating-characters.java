class Solution {
    public int lengthOfLongestSubstring(String s) {
        int[] lastSeen = new int[128];
        // Initialize all positions to -1
        for (int i = 0; i < 128; i++) {
            lastSeen[i] = -1;
        }
        
        int maxLen = 0;
        int start = 0; // Left pointer of the sliding window
        
        for (int end = 0; end < s.length(); end++) {
            char currentChar = s.charAt(end);
            
            // If the character was seen inside the current window, move 'start'
            if (lastSeen[currentChar] >= start) {
                start = lastSeen[currentChar] + 1;
            }
            
            // Update the last seen index of the character
            lastSeen[currentChar] = end;
            
            // Calculate the window size and update maxLen
            maxLen = Math.max(maxLen, end - start + 1);
        }
        
        return maxLen;
    }
}
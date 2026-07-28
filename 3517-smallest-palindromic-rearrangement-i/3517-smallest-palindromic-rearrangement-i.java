class Solution {
    public String smallestPalindrome(String s) {
        int n = s.length();
        int halfLen = n / 2;
        
        // Extract the characters of the first half
        char[] half = s.substring(0, halfLen).toCharArray();
        
        // Sort the first half to make it lexicographically smallest
        java.util.Arrays.sort(half);
        
        StringBuilder sb = new StringBuilder();
        sb.append(half);
        
        // If string length is odd, keep the middle character in place
        if (n % 2 != 0) {
            sb.append(s.charAt(halfLen));
        }
        
        // Mirror the sorted first half
        for (int i = halfLen - 1; i >= 0; i--) {
            sb.append(half[i]);
        }
        
        return sb.toString();
    }
}
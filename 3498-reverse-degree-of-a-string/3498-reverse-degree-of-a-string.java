class Solution {
    public int reverseDegree(String s) {
        int totalSum = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            
            // Reversed alphabet value: 'a' -> 26, 'b' -> 25, ..., 'z' -> 1
            int reversedValue = 26 - (ch - 'a');
            
            // String position is 1-indexed
            int stringIndex = i + 1;
            
            totalSum += reversedValue * stringIndex;
        }
        
        return totalSum;
    }
}
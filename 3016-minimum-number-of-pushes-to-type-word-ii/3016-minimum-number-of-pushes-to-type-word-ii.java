import java.util.Arrays;

class Solution {
    public int minimumPushes(String word) {
        // Count frequencies of each character
        int[] count = new int[26];
        for (char c : word.toCharArray()) {
            count[c - 'a']++;
        }
        
        // Sort frequencies in ascending order
        Arrays.sort(count);
        
        int totalPushes = 0;
        int multiplier = 1;
        int countKeys = 0;
        
        // Iterate from the most frequent character (end of the array) to the least frequent
        for (int i = 25; i >= 0; i--) {
            if (count[i] == 0) break;
            
            totalPushes += count[i] * multiplier;
            countKeys++;
            
            // Every 8 keys assigned, the push cost increases by 1
            if (countKeys % 8 == 0) {
                multiplier++;
            }
        }
        
        return totalPushes;
    }
}
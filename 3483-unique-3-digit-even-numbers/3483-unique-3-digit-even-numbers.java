import java.util.Arrays;

class Solution {
    public int totalNumbers(int[] digits) {
        int[] freq = new int[10];
        for (int d : digits) {
            freq[d]++;
        }
        
        int count = 0;
        
        // Iterate over all possible 3-digit even numbers
        for (int num = 100; num < 1000; num += 2) {
            int d1 = num / 100;       // Hundreds digit
            int d2 = (num / 10) % 10; // Tens digit
            int d3 = num % 10;        // Units digit
            
            int[] currentFreq = new int[10];
            currentFreq[d1]++;
            currentFreq[d2]++;
            currentFreq[d3]++;
            
            // Verify if the available digit counts are sufficient
            if (currentFreq[d1] <= freq[d1] && 
                currentFreq[d2] <= freq[d2] && 
                currentFreq[d3] <= freq[d3]) {
                count++;
            }
        }
        
        return count;
    }
}
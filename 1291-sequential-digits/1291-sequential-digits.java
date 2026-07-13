import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> result = new ArrayList<>();
        String digits = "123456789";
        
        // The length of numbers can range from the length of 'low' to the length of 'high'
        int minLen = String.valueOf(low).length();
        int maxLen = String.valueOf(high).length();
        
        // Sliding window over the "123456789" string
        for (int length = minLen; length <= maxLen; length++) {
            for (int start = 0; start <= 9 - length; start++) {
                String sub = digits.substring(start, start + length);
                int num = Integer.parseInt(sub);
                
                if (num >= low && num <= high) {
                    result.add(num);
                }
            }
        }
        
        return result;
    }
}
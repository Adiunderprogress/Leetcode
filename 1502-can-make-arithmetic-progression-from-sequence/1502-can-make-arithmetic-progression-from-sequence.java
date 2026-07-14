import java.util.Arrays;

class Solution {
    public boolean canMakeArithmeticProgression(int[] arr) {
        // 1. Sort the array
        Arrays.sort(arr);
        
        // 2. Find the common difference from the first two elements
        int diff = arr[1] - arr[0];
        
        // 3. Check if all consecutive elements have the same difference
        for (int i = 2; i < arr.length; i++) {
            if (arr[i] - arr[i - 1] != diff) {
                return false;
            }
        }
        
        return true;
    }
}
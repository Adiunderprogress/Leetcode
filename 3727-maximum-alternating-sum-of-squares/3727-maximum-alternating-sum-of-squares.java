import java.util.Arrays;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        // Convert to long and take absolute values, then sort in ascending order
        long[] vals = new long[nums.length];
        for (int i = 0; i < nums.length; i++) {
            vals[i] = Math.abs((long) nums[i]);
        }
        Arrays.sort(vals); // Ascending order: smallest to largest
        
        // We want to arrange them into the alternating sum: + - + - ...
        // To maximize, we place the largest elements on the '+' positions and smallest on '-' positions.
        long maxScore = 0;
        int left = 0, right = vals.length - 1;
        boolean add = true;
        
        // Using a greedy two-pointer approach from the sorted array:
        // The largest elements should be added at the '+' positions.
        // Let's fill from right (largest) to left.
        // Actually, a standard way for alternating max/min with + - + - is:
        // Put largest at the positive ends.
        
        // Let's use a Deque or simple array reordering:
        long[] arranged = new long[vals.length];
        int l = 0, r = vals.length - 1;
        for (int i = vals.length - 1; i >= 0; i--) {
            if (i % 2 == 0) {
                arranged[i] = vals[r--]; // Take from the largest end for positive positions
            } else {
                arranged[i] = vals[l++]; // Take from the smallest end for negative positions
            }
        }
        
        for (int i = 0; i < arranged.length; i++) {
            if (i % 2 == 0) {
                maxScore += arranged[i] * arranged[i];
            } else {
                maxScore -= arranged[i] * arranged[i];
            }
        }
        
        return maxScore;
    }
}
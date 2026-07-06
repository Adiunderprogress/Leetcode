import java.util.Arrays;

class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        // Sort: ascending by start point; if starts are equal, descending by end point
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });
        
        int remainingCount = 0;
        int maxEnd = 0;
        
        for (int[] interval : intervals) {
            // If the current interval's end fits within the maxEnd seen so far, 
            // it is completely covered because its start is >= previous start.
            if (interval[1] <= maxEnd) {
                continue; // Covered, skip it
            }
            
            // Otherwise, it's a unique remaining interval
            remainingCount++;
            maxEnd = interval[1];
        }
        
        return remainingCount;
    }
}
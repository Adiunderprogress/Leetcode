import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        // Map to store the bitmask of reserved segments for each row
        Map<Integer, Integer> rowMasks = new HashMap<>();
        
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];
            
            int mask = 0;
            // Determine which blocks the current reserved seat invalidates
            if (col == 2 || col == 3) {
                mask = 1; // Blocks Left
            } else if (col == 4 || col == 5) {
                mask = 3; // Blocks Left and Middle (1 | 2)
            } else if (col == 6 || col == 7) {
                mask = 6; // Blocks Middle and Right (2 | 4)
            } else if (col == 8 || col == 9) {
                mask = 4; // Blocks Right
            }
            
            // If the seat affects any of the middle 8 seats, add to map
            if (mask != 0) {
                rowMasks.put(row, rowMasks.getOrDefault(row, 0) | mask);
            }
        }
        
        // Rows that aren't in the map have 0 interfering reservations, fitting 2 families each
        int maxGroups = (n - rowMasks.size()) * 2;
        
        // Evaluate the rows that have at least one valid reservation
        for (int mask : rowMasks.values()) {
            // If both Left (Bit 0) and Right (Bit 2) are free
            if ((mask & 1) == 0 && (mask & 4) == 0) {
                maxGroups += 2;
            } 
            // Else if Left is free, OR Right is free, OR Middle (Bit 1) is free
            else if ((mask & 1) == 0 || (mask & 4) == 0 || (mask & 2) == 0) {
                maxGroups += 1;
            }
        }
        
        return maxGroups;
    }
}
import java.util.Arrays;

class Solution {
    public String[] findRelativeRanks(int[] score) {
        int n = score.length;
        String[] result = new String[n];
        
        // Store [score, originalIndex] pairs
        int[][] pairs = new int[n][2];
        for (int i = 0; i < n; i++) {
            pairs[i][0] = score[i];
            pairs[i][1] = i;
        }
        
        // Sort in descending order based on score
        Arrays.sort(pairs, (a, b) -> Integer.compare(b[0], a[0]));
        
        // Assign ranks based on sorted position
        for (int i = 0; i < n; i++) {
            int originalIdx = pairs[i][1];
            if (i == 0) {
                result[originalIdx] = "Gold Medal";
            } else if (i == 1) {
                result[originalIdx] = "Silver Medal";
            } else if (i == 2) {
                result[originalIdx] = "Bronze Medal";
            } else {
                result[originalIdx] = String.valueOf(i + 1);
            }
        }
        
        return result;
    }
}
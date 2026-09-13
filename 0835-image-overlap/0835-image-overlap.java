import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        List<int[]> la = new ArrayList<>();
        List<int[]> lb = new ArrayList<>();

        // Store positions of 1s for both matrices
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (img1[i][j] == 1) la.add(new int[]{i, j});
                if (img2[i][j] == 1) lb.add(new int[]{i, j});
            }
        }

        Map<String, Integer> map = new HashMap<>();
        int maxOverlap = 0;

        // Calculate translation vectors and track frequency
        for (int[] a : la) {
            for (int[] b : lb) {
                int dr = b[0] - a[0];
                int dc = b[1] - a[1];
                String key = dr + "," + dc;
                
                int count = map.getOrDefault(key, 0) + 1;
                map.put(key, count);
                maxOverlap = Math.max(maxOverlap, count);
            }
        }

        return maxOverlap;
    }
}
import java.util.*;

class Solution {
    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();
        
        // If the start or end cell has a thief, the safeness factor is 0
        if (grid.get(0).get(0) == 1 || grid.get(n - 1).get(n - 1) == 1) {
            return 0;
        }
        
        int[][] safeness = new int[n][n];
        for (int[] row : safeness) {
            Arrays.fill(row, -1);
        }
        
        Queue<int[]> queue = new LinkedList<>();
        
        // Step 1: Multi-source BFS from all thieves
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (grid.get(r).get(c) == 1) {
                    queue.offer(new int[]{r, c});
                    safeness[r][c] = 0;
                }
            }
        }
        
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int r = cell[0];
            int c = cell[1];
            
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                
                if (nr >= 0 && nr < n && nc >= 0 && nc < n && safeness[nr][nc] == -1) {
                    safeness[nr][nc] = safeness[r][c] + 1;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }
        
        // Step 2: Dijkstra's algorithm to find the maximum safeness path
        // Max-heap stores elements as {safeness_factor, r, c} sorted descending by safeness_factor
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        boolean[][] visited = new boolean[n][n];
        
        maxHeap.offer(new int[]{safeness[0][0], 0, 0});
        visited[0][0] = true;
        
        while (!maxHeap.isEmpty()) {
            int[] curr = maxHeap.poll();
            int maxSafe = curr[0];
            int r = curr[1];
            int c = curr[2];
            
            // Reached the destination
            if (r == n - 1 && c == n - 1) {
                return maxSafe;
            }
            
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                
                if (nr >= 0 && nr < n && nc >= 0 && nc < n && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    // The safeness of the path is limited by the minimum safeness encountered so far
                    int nextSafe = Math.min(maxSafe, safeness[nr][nc]);
                    maxHeap.offer(new int[]{nextSafe, nr, nc});
                }
            }
        }
        
        return 0;
    }
}
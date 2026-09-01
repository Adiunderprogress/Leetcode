import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        
        int startR = -1, startC = -1;
        List<int[]> litterList = new ArrayList<>();
        
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                char ch = classroom[r].charAt(c);
                if (ch == 'S') {
                    startR = r;
                    startC = c;
                } else if (ch == 'L') {
                    litterList.add(new int[]{r, c});
                }
            }
        }
        
        int totalLitter = litterList.size();
        if (totalLitter == 0) return 0;
        
        int fullMask = (1 << totalLitter) - 1;
        
        // Map grid coordinates of 'L' to bit indices
        int[][] litterId = new int[m][n];
        for (int i = 0; i < m; i++) Arrays.fill(litterId[i], -1);
        for (int i = 0; i < totalLitter; i++) {
            litterId[litterList.get(i)[0]][litterList.get(i)[1]] = i;
        }
        
        // bestEnergy[r][c][mask] stores max remaining energy seen for state (r, c, mask)
        int[][][] bestEnergy = new int[m][n][1 << totalLitter];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(bestEnergy[i][j], -1);
            }
        }
        
        // BFS Queue stores: {r, c, mask, current_energy, moves}
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startR, startC, 0, energy, 0});
        bestEnergy[startR][startC][0] = energy;
        
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0], c = curr[1], mask = curr[2], e = curr[3], moves = curr[4];
            
            if (mask == fullMask) {
                return moves;
            }
            
            for (int[] d : dirs) {
                int nr = r + d[0];
                int nc = c + d[1];
                
                if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
                char cell = classroom[nr].charAt(nc);
                if (cell == 'X') continue;
                
                int nextEnergy = e - 1;
                if (nextEnergy < 0) continue;
                
                int nextMask = mask;
                if (cell == 'L' && litterId[nr][nc] != -1) {
                    nextMask |= (1 << litterId[nr][nc]);
                } else if (cell == 'R') {
                    nextEnergy = energy;
                }
                
                if (nextEnergy > bestEnergy[nr][nc][nextMask]) {
                    bestEnergy[nr][nc][nextMask] = nextEnergy;
                    queue.offer(new int[]{nr, nc, nextMask, nextEnergy, moves + 1});
                }
            }
        }
        
        return -1;
    }
}
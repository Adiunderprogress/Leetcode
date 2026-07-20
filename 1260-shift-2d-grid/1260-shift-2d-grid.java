import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int total = m * n;
        
        k = k % total;
        int[][] resultArr = new int[m][n];
        
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                int oldIdx = r * n + c;
                int newIdx = (oldIdx + k) % total;
                
                int newR = newIdx / n;
                int newC = newIdx % n;
                
                resultArr[newR][newC] = grid[r][c];
            }
        }
        
        // Convert array to List<List<Integer>> output format
        List<List<Integer>> result = new ArrayList<>();
        for (int r = 0; r < m; r++) {
            List<Integer> row = new ArrayList<>();
            for (int c = 0; c < n; c++) {
                row.add(resultArr[r][c]);
            }
            result.add(row);
        }
        
        return result;
    }
}
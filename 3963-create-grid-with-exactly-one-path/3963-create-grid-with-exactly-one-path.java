class Solution {
    public String[] createGrid(int m, int n) {
        String[] grid = new String[m];
        
        // First row: all '.'
        grid[0] = ".".repeat(n);
        
        // Remaining rows: (n - 1) '#' followed by a single '.'
        String otherRow = "#".repeat(n - 1) + ".";
        for (int i = 1; i < m; i++) {
            grid[i] = otherRow;
        }
        
        return grid;
    }
}
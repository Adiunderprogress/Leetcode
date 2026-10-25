class Solution {
    public int closedIsland(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int closedIslands = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                // Start DFS whenever we find unvisited land (0)
                if (grid[r][c] == 0) {
                    if (dfs(grid, r, c)) {
                        closedIslands++;
                    }
                }
            }
        }
        return closedIslands;
    }

    private boolean dfs(int[][] grid, int r, int c) {
        // Base case 1: Out of bounds means this island touches the edge -> NOT closed
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length) {
            return false;
        }
        
        // Base case 2: Hit water (1) or already visited land -> closed in this direction
        if (grid[r][c] == 1) {
            return true;
        }

        // Mark current land cell as visited by sinking it (turning 0 into 1)
        grid[r][c] = 1;

        // Explore all 4 directions. 
        // Note: Run all 4 calls independently so short-circuiting (&&) doesn't leave part of the island unvisited!
        boolean up = dfs(grid, r - 1, c);
        boolean down = dfs(grid, r + 1, c);
        boolean left = dfs(grid, r, c - 1);
        boolean right = dfs(grid, r, c + 1);

        return up && down && left && right;
    }
}
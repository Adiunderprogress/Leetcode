import java.util.List;

class Solution {
    public int[] pathsWithMaxScore(List<String> board) {
        int n = board.size();
        int MOD = 1_000_000_007;
        
        // dp matrices initialized with -1 to represent unreachable states
        int[][] maxScore = new int[n][n];
        int[][] pathCount = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                maxScore[i][j] = -1;
            }
        }
        
        // Base case: Starting point at top-left 'E'
        maxScore[0][0] = 0;
        pathCount[0][0] = 1;
        
        // Directions to look back to: Up, Left, Up-Left diagonal
        int[][] dirs = {{-1, 0}, {0, -1}, {-1, -1}};
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // Skip the starting cell and obstacles
                if ((i == 0 && j == 0) || board.get(i).charAt(j) == 'X') {
                    continue;
                }
                
                int currentVal = 0;
                char c = board.get(i).charAt(j);
                if (c >= '1' && c <= '9') {
                    currentVal = c - '0';
                }
                
                int currentMax = -1;
                int currentPaths = 0;
                
                // Check all 3 incoming directions
                for (int[] d : dirs) {
                    int prevI = i + d[0];
                    int prevJ = j + d[1];
                    
                    if (prevI >= 0 && prevJ >= 0 && maxScore[prevI][prevJ] != -1) {
                        int candidateScore = maxScore[prevI][prevJ] + currentVal;
                        
                        if (candidateScore > currentMax) {
                            currentMax = candidateScore;
                            currentPaths = pathCount[prevI][prevJ];
                        } else if (candidateScore == currentMax) {
                            currentPaths = (currentPaths + pathCount[prevI][prevJ]) % MOD;
                        }
                    }
                }
                
                maxScore[i][j] = currentMax;
                pathCount[i][j] = currentPaths;
            }
        }
        
        // Bottom-right cell 'S' contains our final answer
        if (maxScore[n - 1][n - 1] == -1) {
            return new int[]{0, 0};
        }
        
        return new int[]{maxScore[n - 1][n - 1], pathCount[n - 1][n - 1]};
    }
}
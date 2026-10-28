class Solution:
    def minCost(self, grid: list[list[int]]) -> int:
        m, n = len(grid), len(grid[0])
        
        # dp[j] holds the set of reachable XOR sums at column j of the current row
        dp = [set() for _ in range(n)]
        dp[0].add(grid[0][0])
        
        for i in range(m):
            for j in range(n):
                if i == 0 and j == 0:
                    continue
                
                val = grid[i][j]
                
                # Combine reachable XOR values from top (dp[j]) and left (dp[j - 1])
                prev_xors = set()
                if i > 0:
                    prev_xors.update(dp[j])
                if j > 0:
                    prev_xors.update(dp[j - 1])
                
                # Compute new reachable XOR values for cell (i, j)
                dp[j] = {x ^ val for x in prev_xors}
                
        return min(dp[-1])
class Solution:
    def uniquePaths(self, grid: List[List[int]]) -> int:
        MOD = 10**9 + 7
        m, n = len(grid), len(grid[0])
        
        # Helper to find the final landing position given a starting move from (r, c)
        # dir: 0 for moving right (into r, c+1), 1 for moving down (into r+1, c)
        def get_next(r, c, d):
            curr_r, curr_c = r, c
            curr_d = d
            while True:
                if curr_d == 0:
                    nxt_r, nxt_c = curr_r, curr_c + 1
                else:
                    nxt_r, nxt_c = curr_r + 1, curr_c
                
                # Check bounds
                if not (0 <= nxt_r < m and 0 <= nxt_c < n):
                    return None # Invalid / Out of bounds
                
                # If landing cell is an empty cell, stop reflection
                if grid[nxt_r][nxt_c] == 0:
                    return (nxt_r, nxt_c)
                
                # Otherwise, it's a mirror. Reflect according to the rules:
                # If entered while moving right (0), turn down (1)
                # If entered while moving down (1), turn right (0)
                curr_r, curr_c = nxt_r, nxt_c
                curr_d = 1 - curr_d

        # dp[i][j] stores the number of ways to reach cell (i, j)
        dp = [[0] * n for _ in range(m)]
        dp[0][0] = 1
        
        for i in range(m):
            for j in range(n):
                if dp[i][j] == 0:
                    if i == 0 and j == 0:
                        pass
                    else:
                        continue
                
                # Try moving right (d = 0)
                res_right = get_next(i, j, 0)
                if res_right:
                    nr, nc = res_right
                    dp[nr][nc] = (dp[nr][nc] + dp[i][j]) % MOD
                
                # Try moving down (d = 1)
                res_down = get_next(i, j, 1)
                if res_down:
                    nr, nc = res_down
                    dp[nr][nc] = (dp[nr][nc] + dp[i][j]) % MOD
                    
        return dp[m - 1][n - 1]
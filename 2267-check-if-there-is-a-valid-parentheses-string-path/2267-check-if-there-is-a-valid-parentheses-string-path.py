from functools import cache

class Solution:
    def hasValidPath(self, grid: list[list[str]]) -> bool:
        m, n = len(grid), len(grid[0])
        
        # 1. Total steps in any path is m + n - 1. Valid parentheses string must have even length.
        if (m + n - 1) % 2 != 0:
            return False
        
        # 2. Must start with '(' and end with ')'
        if grid[0][0] == ')' or grid[m - 1][n - 1] == '(':
            return False

        max_open = (m + n) // 2

        @cache
        def dfs(r: int, c: int, open_count: int) -> bool:
            # Update balance based on current cell
            if grid[r][c] == '(':
                open_count += 1
            else:
                open_count -= 1
            
            # Invalid path: closed more than opened or opened more than possible max
            if open_count < 0 or open_count > max_open:
                return False
            
            # Reached destination: check if all brackets are balanced
            if r == m - 1 and c == n - 1:
                return open_count == 0
            
            # Move Down or Right
            res = False
            if r + 1 < m:
                res = res or dfs(r + 1, c, open_count)
            if c + 1 < n and not res:
                res = res or dfs(r, c + 1, open_count)
                
            return res

        return dfs(0, 0, 0)
class Solution:
    def checkValidString(self, s: str) -> bool:
        # 'low' is the minimum number of open parentheses needed
        # 'high' is the maximum possible number of open parentheses
        low = high = 0
        
        for char in s:
            if char == '(':
                low += 1
                high += 1
            elif char == ')':
                low -= 1
                high -= 1
            else: # char == '*'
                low -= 1   # Treat '*' as ')'
                high += 1  # Treat '*' as '('
            
            # If high becomes negative, we have too many ')' that cannot be matched
            if high < 0:
                return False
            
            # 'low' cannot be negative because we can treat excess '*' as empty strings
            low = max(low, 0)
            
        # The string is valid if we can successfully match all open parentheses
        return low == 0
class Solution:
    def generateParenthesis(self, n: int) -> list[str]:
        result = []
        
        def backtrack(current_str, open_count, close_count):
            # Base case: if the string length is 2*n, it's a valid combination
            if len(current_str) == 2 * n:
                result.append(current_str)
                return
            
            # If we haven't used all open parentheses, we can add one
            if open_count < n:
                backtrack(current_str + "(", open_count + 1, close_count)
                
            # If we have more open than closed parentheses, we can add a closing one
            if close_count < open_count:
                backtrack(current_str + ")", open_count, close_count + 1)
                
        backtrack("", 0, 0)
        return result
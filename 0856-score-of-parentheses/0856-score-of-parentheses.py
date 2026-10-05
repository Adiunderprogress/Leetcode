class Solution:
    def scoreOfParentheses(self, s: str) -> int:
        stack = [0] # Initialize with 0 to keep track of the total score
        
        for char in s:
            if char == '(':
                # Enter a new nested depth level
                stack.append(0)
            else:
                # Exit the current depth level
                inner_score = stack.pop()
                # If inner_score is 0, it was '()' which has a score of 1.
                # Otherwise, it's (A) which has a score of 2 * A.
                score = max(2 * inner_score, 1)
                # Add the calculated score to the outer level
                stack[-1] += score
                
        return stack[0]
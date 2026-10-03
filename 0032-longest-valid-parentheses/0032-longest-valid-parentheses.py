class Solution:
    def longestValidParentheses(self, s: str) -> int:
        # Initialize stack with -1 to serve as the base index for valid substrings
        stack = [-1]
        max_length = 0
        
        for i, char in enumerate(s):
            if char == '(':
                # Push the index of the open parenthesis
                stack.append(i)
            else:
                # Pop the top element for the matching open parenthesis
                stack.pop()
                
                if not stack:
                    # If stack is empty, this closing parenthesis is unmatched.
                    # Push its index to serve as the new base.
                    stack.append(i)
                else:
                    # Calculate the length of the current valid substring
                    max_length = max(max_length, i - stack[-1])
                    
        return max_length
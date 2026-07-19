class Solution(object):
    def smallestSubsequence(self, s):
        """
        :type s: str
        :rtype: str
        """
        # Find the last occurrence index of each character
        last_occurrence = {char: i for i, char in enumerate(s)}
        
        stack = []
        seen = set()  # To track characters already included in our stack
        
        for i, char in enumerate(s):
            # If the character is already in the stack, skip it to maintain uniqueness
            if char in seen:
                continue
                
            # Pop characters from the stack if:
            # 1. The stack is not empty
            # 2. The top of the stack is lexicographically greater than the current character
            # 3. The top character appears again later in the string
            while stack and stack[-1] > char and last_occurrence[stack[-1]] > i:
                removed_char = stack.pop()
                seen.remove(removed_char)
                
            # Add the current character to the stack and mark it as seen
            stack.append(char)
            seen.add(char)
            
        return "".join(stack)
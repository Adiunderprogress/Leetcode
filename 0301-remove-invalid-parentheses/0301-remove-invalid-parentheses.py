class Solution:
    def removeInvalidParentheses(self, s: str) -> list[str]:
        def isValid(string):
            count = 0
            for char in string:
                if char == '(':
                    count += 1
                elif char == ')':
                    count -= 1
                    if count < 0:
                        return False
            return count == 0

        if not s:
            return [""]

        current_level = {s}
        valid_results = []
        found = False

        while current_level:
            # Check if any string in the current level is valid
            for string in current_level:
                if isValid(string):
                    valid_results.append(string)
                    found = True
            
            # If we found valid strings at this depth, stop searching deeper
            if found:
                return valid_results

            next_level = set()
            for string in current_level:
                for i in range(len(string)):
                    if string[i] in ('(', ')'):
                        # Generate new string by removing the character at index i
                        next_level.add(string[:i] + string[i+1:])
            
            current_level = next_level
            
        return [""]
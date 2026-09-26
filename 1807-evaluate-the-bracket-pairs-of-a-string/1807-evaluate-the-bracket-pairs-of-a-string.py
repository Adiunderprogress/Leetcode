class Solution:
    def evaluate(self, s: str, knowledge: list[list[str]]) -> str:
        # Convert knowledge array to a hash map for O(1) key lookup
        lookup = {key: value for key, value in knowledge}
        
        result = []
        i = 0
        n = len(s)
        
        while i < n:
            if s[i] == '(':
                # Find the closing bracket
                j = i + 1
                while j < n and s[j] != ')':
                    j += 1
                
                # Extract the key inside brackets
                key = s[i + 1:j]
                
                # Replace with corresponding value or '?' if key is missing
                result.append(lookup.get(key, '?'))
                
                # Move index past the closing bracket
                i = j + 1
            else:
                # Regular character, keep as is
                result.append(s[i])
                i += 1
                
        return "".join(result)
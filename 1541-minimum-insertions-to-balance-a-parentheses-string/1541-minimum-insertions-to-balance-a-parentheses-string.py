class Solution:
    def minInsertions(self, s: str) -> int:
        res = 0
        open_brackets = 0
        i = 0
        n = len(s)
        while i < n:
            if s[i] == '(':
                open_brackets += 1
                i += 1
            else:
                # Check if the next character is also ')'
                if i + 1 < n and s[i + 1] == ')':
                    i += 2  # consume both ')'
                else:
                    res += 1  # need to insert one ')'
                    i += 1  # consume this ')'
                
                if open_brackets > 0:
                    open_brackets -= 1
                else:
                    res += 1  # need to insert a matching '('
        return res + open_brackets * 2
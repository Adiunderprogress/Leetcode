class Solution:
    def reverseParentheses(self, s: str) -> str:
        n = len(s)
        pair = [0] * n
        stack = []

        # Step 1: Precompute matching parenthesis indices
        for i, char in enumerate(s):
            if char == '(':
                stack.append(i)
            elif char == ')':
                open_idx = stack.pop()
                pair[open_idx] = i
                pair[i] = open_idx

        # Step 2: Traverse string with direction toggling (Wormhole Approach)
        res = []
        curr = 0
        direction = 1  # 1 for right, -1 for left

        while curr < n:
            if s[curr] in '()':
                curr = pair[curr]  # Teleport to matching parenthesis
                direction = -direction  # Reverse direction
            else:
                res.append(s[curr])
            curr += direction

        return "".join(res)
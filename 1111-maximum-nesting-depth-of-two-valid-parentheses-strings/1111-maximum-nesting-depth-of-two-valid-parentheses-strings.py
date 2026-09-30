class Solution:
    def maxDepthAfterSplit(self, seq: str) -> list[int]:
        ans = []
        depth = 0
        for i, char in enumerate(seq):
            if char == '(':
                depth += 1
                ans.append(depth & 1)
            else:
                ans.append(depth & 1)
                depth -= 1
        return ans
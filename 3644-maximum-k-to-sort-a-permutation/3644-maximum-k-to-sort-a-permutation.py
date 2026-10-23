class Solution:
    def sortPermutation(self, nums: List[int]) -> int:
        k = -1
        for i, val in enumerate(nums):
            if val != i:
                if k == -1:
                    k = val
                else:
                    k &= val
                
        return k if k != -1 else 0
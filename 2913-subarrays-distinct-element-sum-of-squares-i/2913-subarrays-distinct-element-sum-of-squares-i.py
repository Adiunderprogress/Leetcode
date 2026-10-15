class Solution:
    def sumCounts(self, nums: List[int]) -> int:
        total_sum = 0
        n = len(nums)
        
        for i in range(n):
            distinct_elements = set()
            for j in range(i, n):
                distinct_elements.add(nums[j])
                # Add the square of the count of distinct elements in nums[i...j]
                total_sum += len(distinct_elements) ** 2
                
        return total_sum
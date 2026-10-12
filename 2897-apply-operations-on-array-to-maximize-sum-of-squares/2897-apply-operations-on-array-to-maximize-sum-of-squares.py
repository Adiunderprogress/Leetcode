class Solution:
    def maxSum(self, nums: List[int], k: int) -> int:
        MOD = 10**9 + 7
        bit_counts = [0] * 32
        
        # Count the frequency of each bit across all numbers
        for num in nums:
            for i in range(32):
                if (num >> i) & 1:
                    bit_counts[i] += 1
        
        # Reconstruct the top k numbers to be as large as possible
        new_nums = [0] * k
        for i in range(k):
            current_val = 0
            for bit in range(32):
                if bit_counts[bit] > 0:
                    current_val |= (1 << bit)
                    bit_counts[bit] -= 1
            new_nums[i] = current_val
            
        # Calculate the sum of squares modulo 10^9 + 7
        ans = 0
        for num in new_nums:
            ans = (ans + num * num) % MOD
            
        return ans
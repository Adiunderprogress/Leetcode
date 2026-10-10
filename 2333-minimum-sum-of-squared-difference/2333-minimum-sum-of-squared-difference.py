class Solution:
    def minSumSquareDiff(self, nums1: list[int], nums2: list[int], k1: int, k2: int) -> int:
        n = len(nums1)
        total_k = k1 + k2
        
        # Step 1: Find initial absolute differences and their frequencies
        max_diff = 0
        counts = [0] * 100001
        
        for i in range(n):
            d = abs(nums1[i] - nums2[i])
            if d > 0:
                counts[d] += 1
                if d > max_diff:
                    max_diff = d
                    
        # Step 2: Greedily reduce the largest differences
        for d in range(max_diff, 0, -1):
            if counts[d] == 0:
                continue
            
            # Operations needed to reduce all elements of difference `d` to `d - 1`
            ops_needed = counts[d]
            
            if total_k >= ops_needed:
                total_k -= ops_needed
                counts[d] = 0
                counts[d - 1] += ops_needed
            else:
                # We don't have enough budget to reduce all elements of difference `d`
                counts[d] -= total_k
                counts[d - 1] += total_k
                total_k = 0
                break
                
        # Step 3: Compute the final minimum sum of squared differences
        min_sum_sq_diff = 0
        for d in range(1, 100001):
            if counts[d] > 0:
                min_sum_sq_diff += counts[d] * (d * d)
                
        return min_sum_sq_diff
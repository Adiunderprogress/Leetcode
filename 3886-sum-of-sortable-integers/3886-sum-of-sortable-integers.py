class Solution:
    def sortableIntegers(self, nums: list[int]) -> int:
        n = len(nums)
        pref_drops = [0] * n
        last_drop = [-1] * n
        current_drop = -1
        cnt = 0
        for i in range(n - 1):
            if nums[i] > nums[i + 1]:
                cnt += 1
                current_drop = i
            pref_drops[i + 1] = cnt
            last_drop[i] = current_drop
        if n > 0:
            last_drop[n - 1] = current_drop

        ans = 0
        for k in range(1, n + 1):
            if n % k != 0:
                continue
            possible = True
            prev_max = -1
            for b in range(0, n, k):
                e = b + k
                int_drops = pref_drops[e - 1] - pref_drops[b]
                wrap_drop = 1 if nums[e - 1] > nums[b] else 0
                if int_drops + wrap_drop > 1:
                    possible = False
                    break
                if int_drops == 0:
                    b_min = nums[b]
                    b_max = nums[e - 1]
                else:
                    j = last_drop[e - 2]
                    b_min = nums[j + 1]
                    b_max = nums[j]
                if prev_max != -1 and prev_max > b_min:
                    possible = False
                    break
                prev_max = b_max
            if possible:
                ans += k
        return ans
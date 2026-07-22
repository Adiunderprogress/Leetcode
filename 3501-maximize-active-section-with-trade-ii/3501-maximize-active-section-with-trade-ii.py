import bisect

class ST:
    def __init__(self, pairs):
        self.n = len(pairs)
        self.st = [0] * (4 * self.n)
        if self.n > 0:
            self.build(pairs, 1, 0, self.n - 1)

    def build(self, pairs, node, left, right):
        if left == right:
            self.st[node] = pairs[left]
            return self.st[node]
        
        mid = left + (right - left) // 2
        lc = self.build(pairs, node * 2, left, mid)
        rc = self.build(pairs, node * 2 + 1, mid + 1, right)
        self.st[node] = max(lc, rc)
        return self.st[node]

    def query(self, node, left, right, ql, qr):
        if left > qr or right < ql:
            return 0
        if ql <= left and right <= qr:
            return self.st[node]
        
        mid = left + (right - left) // 2
        lc = self.query(node * 2, left, mid, ql, qr)
        rc = self.query(node * 2 + 1, mid + 1, right, ql, qr)
        return max(lc, rc)


class Solution:
    def maxActiveSectionsAfterTrade(self, s: str, queries: list[list[int]]) -> list[int]:
        n = len(s)
        ones = 0
        zeros = []
        i = 0
        
        while i < n:
            if s[i] == '0':
                j = i
                while j < n and s[j] == s[i]:
                    j += 1
                zeros.append([i, j - 1])
                i = j
            else:
                ones += 1
                i += 1

        if len(zeros) < 2:
            return [ones] * len(queries)

        pairs = []
        for i in range(1, len(zeros)):
            L1, R1 = zeros[i - 1]
            L2, R2 = zeros[i]
            pairs.append((R1 - L1 + 1) + (R2 - L2 + 1))

        st = ST(pairs)
        starts = [block[0] for block in zeros]
        ends = [block[1] for block in zeros]
        
        result = []
        for L, R in queries:
            first = bisect.bisect_left(ends, L)
            last = bisect.bisect_right(starts, R) - 1

            if first >= last:
                result.append(ones)
                continue

            best = st.query(1, 0, st.n - 1, first + 1, last - 2)

            # Left boundary check
            prev_len = min(zeros[first][1], R) - max(zeros[first][0], L) + 1
            next_len = min(zeros[first + 1][1], R) - max(zeros[first + 1][0], L) + 1
            best = max(best, prev_len + next_len)

            # Right boundary check
            prev_len = min(zeros[last - 1][1], R) - max(zeros[last - 1][0], L) + 1
            next_len = min(zeros[last][1], R) - max(zeros[last][0], L) + 1
            best = max(best, prev_len + next_len)

            result.append(ones + best)

        return result
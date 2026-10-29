class SegmentTree:
    def __init__(self, n, init_vals):
        self.n = n
        self.tree = [0] * (4 * n)
        if init_vals:
            self._build(1, 0, n - 1, init_vals)

    def _build(self, node, l, r, vals):
        if l == r:
            self.tree[node] = vals[l]
            return
        mid = (l + r) // 2
        self._build(2 * node, l, mid, vals)
        self._build(2 * node + 1, mid + 1, r, vals)
        self.tree[node] = self.tree[2 * node] ^ self.tree[2 * node + 1]

    def update(self, node, l, r, idx, val):
        if l == r:
            self.tree[node] = val
            return
        mid = (l + r) // 2
        if idx <= mid:
            self.update(2 * node, l, mid, idx, val)
        else:
            self.update(2 * node + 1, mid + 1, r, idx, val)
        self.tree[node] = self.tree[2 * node] ^ self.tree[2 * node + 1]

    def query(self, node, l, r, ql, qr):
        if ql > r or qr < l:
            return 0
        if ql <= l and r <= qr:
            return self.tree[node]
        mid = (l + r) // 2
        return self.query(2 * node, l, mid, ql, qr) ^ self.query(2 * node + 1, mid + 1, r, ql, qr)


class Solution:
    def palindromePath(self, n: int, edges: list[list[int]], s: str, queries: list[str]) -> list[bool]:
        from collections import defaultdict

        adj = defaultdict(list)
        for u, v in edges:
            adj[u].append(v)
            adj[v].append(u)

        parent = [0] * n
        depth = [0] * n
        heavy = [-1] * n
        head = [0] * n
        pos = [0] * n
        size = [0] * n
        cur_pos = 0

        # 1st DFS: compute sizes, depths, heavy children, and parents
        def dfs1(u, p, d):
            parent[u] = p
            depth[u] = d
            size[u] = 1
            max_sz = 0
            for v in adj[u]:
                if v != p:
                    dfs1(v, u, d + 1)
                    size[u] += size[v]
                    if size[v] > max_sz:
                        max_sz = size[v]
                        heavy[u] = v

        dfs1(0, -1, 0)

        # 2nd DFS: build heavy-light chains and flatten indices
        def dfs2(u, h):
            nonlocal cur_pos
            head[u] = h
            pos[u] = cur_pos
            cur_pos += 1
            
            if heavy[u] != -1:
                dfs2(heavy[u], h)
                
            for v in adj[u]:
                if v != parent[u] and v != heavy[u]:
                    dfs2(v, v)

        dfs2(0, 0)

        # Initialize segment tree with bitmasks corresponding to characters
        init_vals = [0] * n
        for i in range(n):
            node_idx = pos[i]
            init_vals[node_idx] = 1 << (ord(s[i]) - ord('a'))

        seg_tree = SegmentTree(n, init_vals)
        ans = []

        def get_path_xor(u, v):
            res = 0
            while head[u] != head[v]:
                if depth[head[u]] < depth[head[v]]:
                    u, v = v, u
                res ^= seg_tree.query(1, 0, n - 1, pos[head[u]], pos[u])
                u = parent[head[u]]
            if depth[u] > depth[v]:
                u, v = v, u
            res ^= seg_tree.query(1, 0, n - 1, pos[u], pos[v])
            return res

        for q in queries:
            parts = q.split()
            if parts[0] == "update":
                u = int(parts[1])
                c = parts[2]
                new_mask = 1 << (ord(c) - ord('a'))
                seg_tree.update(1, 0, n - 1, pos[u], new_mask)
            else:
                u, v = int(parts[1]), int(parts[2])
                mask = get_path_xor(u, v)
                # Check if at most one bit is set
                if (mask & (mask - 1)) == 0:
                    ans.append(True)
                else:
                    ans.append(False)

        return ans
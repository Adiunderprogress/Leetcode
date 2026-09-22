class Solution {
    static class Node {
        int prod;
        int[] cnt;

        Node(int k) {
            this.prod = 1;
            this.cnt = new int[k];
        }
    }

    private int kVal;
    private Node[] tree;
    private int n;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.kVal = k;
        this.n = nums.length;
        tree = new Node[4 * n];

        build(nums, 1, 0, n - 1);

        int[] ans = new int[queries.length];

        for (int q = 0; q < queries.length; q++) {
            int idx = queries[q][0];
            int val = queries[q][1];
            int start = queries[q][2];
            int targetX = queries[q][3];

            // Step 1: Update nums[idx] = val
            update(1, 0, n - 1, idx, val);

            // Step 2: Query the range [start, n - 1]
            Node res = query(1, 0, n - 1, start, n - 1);

            // Step 3: Get frequency of remainder targetX
            ans[q] = res.cnt[targetX];
        }

        return ans;
    }

    private Node merge(Node left, Node right) {
        if (left == null) return right;
        if (right == null) return left;

        Node parent = new Node(kVal);
        parent.prod = (left.prod * right.prod) % kVal;

        // Count prefixes strictly in left
        for (int r = 0; r < kVal; r++) {
            parent.cnt[r] += left.cnt[r];
        }

        // Count prefixes extending into right
        for (int r = 0; r < kVal; r++) {
            if (right.cnt[r] > 0) {
                int combinedMod = (left.prod * r) % kVal;
                parent.cnt[combinedMod] += right.cnt[r];
            }
        }

        return parent;
    }

    private void build(int[] nums, int node, int start, int end) {
        if (start == end) {
            tree[node] = new Node(kVal);
            int modVal = nums[start] % kVal;
            tree[node].prod = modVal;
            tree[node].cnt[modVal] = 1;
            return;
        }

        int mid = start + (end - start) / 2;
        build(nums, 2 * node, start, mid);
        build(nums, 2 * node + 1, mid + 1, end);

        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    private void update(int node, int start, int end, int idx, int val) {
        if (start == end) {
            tree[node] = new Node(kVal);
            int modVal = val % kVal;
            tree[node].prod = modVal;
            tree[node].cnt[modVal] = 1;
            return;
        }

        int mid = start + (end - start) / 2;
        if (idx <= mid) {
            update(2 * node, start, mid, idx, val);
        } else {
            update(2 * node + 1, mid + 1, end, idx, val);
        }

        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    private Node query(int node, int start, int end, int l, int r) {
        if (r < start || end < l) {
            return null;
        }

        if (l <= start && end <= r) {
            return tree[node];
        }

        int mid = start + (end - start) / 2;
        Node leftResult = query(2 * node, start, mid, l, r);
        Node rightResult = query(2 * node + 1, mid + 1, end, l, r);

        return merge(leftResult, rightResult);
    }
}
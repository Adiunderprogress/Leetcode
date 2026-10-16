import java.util.HashMap;
import java.util.Map;

class Solution {
    static final int MOD = 1_000_000_007;

    long[] sumSqTree;
    long[] sumTree;
    long[] lazy;

    public int sumCounts(int[] nums) {
        int n = nums.length;
        sumSqTree = new long[4 * n];
        sumTree = new long[4 * n];
        lazy = new long[4 * n];

        Map<Integer, Integer> lastSeen = new HashMap<>();
        long currentSumSquares = 0;
        long totalSum = 0;

        for (int i = 0; i < n; i++) {
            int x = nums[i];
            int prev = lastSeen.getOrDefault(x, -1);
            lastSeen.put(x, i);

            // Range update: add 1 to all starting indices from prev + 1 to i
            int l = prev + 1;
            int r = i;

            // Query previous linear sum from range [l, r]
            long rangeLinearSum = querySum(1, 0, n - 1, l, r);
            long len = (r - l + 1);

            // Algebraic expansion update: sum((c+1)^2) = sum(c^2) + 2*sum(c) + len
            long addedValue = (2 * rangeLinearSum + len) % MOD;
            currentSumSquares = (currentSumSquares + addedValue) % MOD;

            // Apply range update (+1) in segment tree for range [l, r]
            update(1, 0, n - 1, l, r, 1);

            totalSum = (totalSum + currentSumSquares) % MOD;
        }

        return (int) totalSum;
    }

    private void push(int node, int l, int r) {
        if (lazy[node] != 0) {
            int mid = (l + r) / 2;
            apply(2 * node, l, mid, lazy[node]);
            apply(2 * node + 1, mid + 1, r, lazy[node]);
            lazy[node] = 0;
        }
    }

    private void apply(int node, int l, int r, long val) {
        long len = (r - l + 1);
        // sumSqTree update: newSumSq = oldSumSq + 2 * val * oldSum + val^2 * len
        long oldSum = sumTree[node];
        sumSqTree[node] = (sumSqTree[node] + 2 * val * oldSum + val * val % MOD * len) % MOD;
        sumSqTree[node] = (sumSqTree[node] + MOD) % MOD;

        // sumTree update: newSum = oldSum + val * len
        sumTree[node] = (sumTree[node] + val * len) % MOD;
        sumTree[node] = (sumTree[node] + MOD) % MOD;

        lazy[node] = (lazy[node] + val) % MOD;
    }

    private void update(int node, int start, int end, int l, int r, long val) {
        if (r < start || end < l) return;
        if (l <= start && end <= r) {
            apply(node, start, end, val);
            return;
        }
        push(node, start, end);
        int mid = (start + end) / 2;
        update(2 * node, start, mid, l, r, val);
        update(2 * node + 1, mid + 1, end, l, r, val);
        
        sumSqTree[node] = (sumSqTree[2 * node] + sumSqTree[2 * node + 1]) % MOD;
        sumTree[node] = (sumTree[2 * node] + sumTree[2 * node + 1]) % MOD;
    }

    private long querySum(int node, int start, int end, int l, int r) {
        if (r < start || end < l) return 0;
        if (l <= start && end <= r) return sumTree[node];
        push(node, start, end);
        int mid = (start + end) / 2;
        return (querySum(2 * node, start, mid, l, r) + querySum(2 * node + 1, mid + 1, end, l, r)) % MOD;
    }
}
class Solution {
    static class Node {
        int max;
        int prefix;
        int suffix;
        char leftChar;
        char rightChar;

        Node(char c) {
            this.max = 1;
            this.prefix = 1;
            this.suffix = 1;
            this.leftChar = c;
            this.rightChar = c;
        }

        Node() {}
    }

    private Node[] tree;
    private char[] chars;
    private int n;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        this.chars = s.toCharArray();
        this.n = chars.length;
        this.tree = new Node[4 * n];

        build(1, 0, n - 1);

        int k = queryIndices.length;
        int[] ans = new int[k];

        for (int i = 0; i < k; i++) {
            int idx = queryIndices[i];
            char c = queryCharacters.charAt(i);
            chars[idx] = c;
            update(1, 0, n - 1, idx, c);
            ans[i] = tree[1].max;
        }

        return ans;
    }

    private Node merge(Node left, Node right, int leftLen, int rightLen) {
        Node res = new Node();
        res.leftChar = left.leftChar;
        res.rightChar = right.rightChar;

        // Base max length from children
        res.max = Math.max(left.max, right.max);

        // Check if prefix extends fully through left node into right node
        res.prefix = left.prefix;
        if (left.prefix == leftLen && left.rightChar == right.leftChar) {
            res.prefix = leftLen + right.prefix;
        }

        // Check if suffix extends fully through right node into left node
        res.suffix = right.suffix;
        if (right.suffix == rightLen && left.rightChar == right.leftChar) {
            res.suffix = rightLen + left.suffix;
        }

        // Check if middle merge produces a larger contiguous block
        if (left.rightChar == right.leftChar) {
            res.max = Math.max(res.max, left.suffix + right.prefix);
        }

        res.max = Math.max(res.max, Math.max(res.prefix, res.suffix));
        return res;
    }

    private void build(int node, int start, int end) {
        if (start == end) {
            tree[node] = new Node(chars[start]);
            return;
        }
        int mid = start + (end - start) / 2;
        build(2 * node, start, mid);
        build(2 * node + 1, mid + 1, end);
        tree[node] = merge(tree[2 * node], tree[2 * node + 1], mid - start + 1, end - mid);
    }

    private void update(int node, int start, int end, int idx, char c) {
        if (start == end) {
            tree[node] = new Node(c);
            return;
        }
        int mid = start + (end - start) / 2;
        if (idx <= mid) {
            update(2 * node, start, mid, idx, c);
        } else {
            update(2 * node + 1, mid + 1, end, idx, c);
        }
        tree[node] = merge(tree[2 * node], tree[2 * node + 1], mid - start + 1, end - mid);
    }
}
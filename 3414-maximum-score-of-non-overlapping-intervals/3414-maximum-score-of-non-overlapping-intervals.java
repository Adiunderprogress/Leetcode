import java.util.*;

class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        
        // Store interval along with its original index: [l, r, weight, original_index]
        int[][] sorted = new int[n][4];
        for (int i = 0; i < n; i++) {
            sorted[i][0] = intervals.get(i).get(0);
            sorted[i][1] = intervals.get(i).get(1);
            sorted[i][2] = intervals.get(i).get(2);
            sorted[i][3] = i;
        }

        // Sort primarily by right boundary r
        Arrays.sort(sorted, (a, b) -> Integer.compare(a[1], b[1]));

        // Precompute previous non-overlapping index using binary search
        int[] prev = new int[n];
        for (int i = 0; i < n; i++) {
            int l = 0, r = i - 1, ans = -1;
            while (l <= r) {
                int mid = (l + r) / 2;
                if (sorted[mid][1] < sorted[i][0]) {
                    ans = mid;
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
            prev[i] = ans;
        }

        // DP State: dp[k][i] stores best state taking up to k intervals from prefix 0..i
        // State representation: best total weight, array of chosen original indices
        class State {
            long weight = 0;
            List<Integer> indices = new ArrayList<>();
        }

        State[][] dp = new State[5][n + 1];
        for (int k = 0; k <= 4; k++) {
            for (int i = 0; i <= n; i++) {
                dp[k][i] = new State();
            }
        }

        for (int k = 1; k <= 4; k++) {
            for (int i = 1; i <= n; i++) {
                // Option 1: Don't pick interval i-1
                State best = dp[k][i - 1];

                // Option 2: Pick interval i-1
                int p = prev[i - 1];
                State prevChoice = (p != -1) ? dp[k - 1][p + 1] : dp[k - 1][0];
                
                long candWeight = prevChoice.weight + sorted[i - 1][2];
                List<Integer> candIndices = new ArrayList<>(prevChoice.indices);
                candIndices.add(sorted[i - 1][3]);
                Collections.sort(candIndices);

                // Compare Option 1 and Option 2
                if (candWeight > best.weight) {
                    State newState = new State();
                    newState.weight = candWeight;
                    newState.indices = candIndices;
                    dp[k][i] = newState;
                } else if (candWeight == best.weight) {
                    if (best.weight == 0 || isLexicographicallySmaller(candIndices, best.indices)) {
                        State newState = new State();
                        newState.weight = candWeight;
                        newState.indices = candIndices;
                        dp[k][i] = newState;
                    } else {
                        dp[k][i] = best;
                    }
                } else {
                    dp[k][i] = best;
                }
            }
        }

        State resultState = dp[4][n];
        int[] result = new int[resultState.indices.size()];
        for (int i = 0; i < resultState.indices.size(); i++) {
            result[i] = resultState.indices.get(i);
        }
        return result;
    }

    private boolean isLexicographicallySmaller(List<Integer> a, List<Integer> b) {
        int len = Math.min(a.size(), b.size());
        for (int i = 0; i < len; i++) {
            if (!a.get(i).equals(b.get(i))) {
                return a.get(i) < b.get(i);
            }
        }
        return a.size() < b.size();
    }
}
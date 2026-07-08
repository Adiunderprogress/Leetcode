import java.util.*;

class Solution {
    public int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();
        long MOD = 1000000007;

        // Collect positions and values of non-zero digits
        List<Integer> nonZeroIndices = new ArrayList<>();
        List<Integer> nonZeroVals = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c != '0') {
                nonZeroIndices.add(i);
                nonZeroVals.add(c - '0');
            }
        }

        int m = nonZeroVals.size();
        if (m == 0) {
            return new int[queries.length]; // All queries will result in 0
        }

        // Precompute prefix sums for digit sums
        long[] prefSum = new long[m + 1];
        // Precompute prefix values for concatenation
        long[] prefConcat = new long[m + 1];
        // Precompute powers of 10 modulo 10^9 + 7
        long[] pow10 = new long[m + 1];

        pow10[0] = 1;
        for (int i = 0; i < m; i++) {
            prefSum[i + 1] = prefSum[i] + nonZeroVals.get(i);
            prefConcat[i + 1] = (prefConcat[i] * 10 + nonZeroVals.get(i)) % MOD;
            pow10[i + 1] = (pow10[i] * 10) % MOD;
        }

        int[] answer = new int[queries.length];

        for (int q = 0; q < queries.length; q++) {
            int L = queries[q][0];
            int R = queries[q][1];

            // Find the first non-zero digit index >= L
            int startIdx = findFirstGTE(nonZeroIndices, L);
            // Find the last non-zero digit index <= R
            int endIdx = findLastLTE(nonZeroIndices, R);

            if (startIdx > endIdx || startIdx == -1 || endIdx == -1) {
                answer[q] = 0;
                continue;
            }

            // Number of non-zero digits in this range
            int count = endIdx - startIdx + 1;

            // Extract sum of digits
            long digitSum = prefSum[endIdx + 1] - prefSum[startIdx];

            // Extract concatenation value x modulo MOD
            long x = (prefConcat[endIdx + 1] - (prefConcat[startIdx] * pow10[count]) % MOD + MOD) % MOD;

            // Calculate final answer for this query
            answer[q] = (int) ((x * digitSum) % MOD);
        }

        return answer;
    }

    // Binary search for first element >= target
    private int findFirstGTE(List<Integer> list, int target) {
        int low = 0, high = list.size() - 1;
        int ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (list.get(mid) >= target) {
                ans = mid;
                high = mid - 1; // look for smaller index
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    // Binary search for last element <= target
    private int findLastLTE(List<Integer> list, int target) {
        int low = 0, high = list.size() - 1;
        int ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (list.get(mid) <= target) {
                ans = mid;
                low = mid + 1; // look for larger index
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }
}
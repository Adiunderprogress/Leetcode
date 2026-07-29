class Solution {
    private static final int MAX_K = 1_000_001; // Capped threshold since k <= 10^6

    public String smallestPalindrome(String s, int k) {
        int n = s.length();
        int halfLen = n / 2;

        // 1. Count character frequencies
        int[] freq = new int[26];
        for (int i = 0; i < n; i++) {
            freq[s.charAt(i) - 'a']++;
        }

        // 2. Identify mid character (if n is odd) and half counts
        char midChar = 0;
        int[] halfCounts = new int[26];
        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 != 0) {
                midChar = (char) ('a' + i);
            }
            halfCounts[i] = freq[i] / 2;
        }

        // 3. Precompute combinations C(n, r) capped at MAX_K
        int[][] C = new int[halfLen + 1][halfLen + 1];
        for (int i = 0; i <= halfLen; i++) {
            C[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                C[i][j] = C[i - 1][j - 1] + C[i - 1][j];
                if (C[i][j] > MAX_K) {
                    C[i][j] = MAX_K;
                }
            }
        }

        // 4. Check if total distinct permutations >= k
        long totalPermutations = getPermutations(halfCounts, halfLen, C);
        if (totalPermutations < k) {
            return "";
        }

        // 5. Construct the first half string character by character
        char[] half = new char[halfLen];
        int remainingLen = halfLen;

        for (int i = 0; i < halfLen; i++) {
            for (int c = 0; c < 26; c++) {
                if (halfCounts[c] == 0) continue;

                // Temporarily place character 'c'
                halfCounts[c]--;
                long count = getPermutations(halfCounts, remainingLen - 1, C);

                if (count >= k) {
                    half[i] = (char) ('a' + c);
                    remainingLen--;
                    break; // Fixed character at index i
                } else {
                    k -= count;
                    halfCounts[c]++; // Backtrack and try next character
                }
            }
        }

        // 6. Reconstruct the full palindrome
        StringBuilder sb = new StringBuilder();
        sb.append(new String(half));
        if (n % 2 != 0) {
            sb.append(midChar);
        }
        for (int i = halfLen - 1; i >= 0; i--) {
            sb.append(half[i]);
        }

        return sb.toString();
    }

    // Helper method to compute multinomial combination capped at MAX_K
    private long getPermutations(int[] counts, int totalLen, int[][] C) {
        long perms = 1;
        int rem = totalLen;

        for (int count : counts) {
            if (count == 0) continue;
            perms = perms * C[rem][count];
            if (perms > MAX_K) {
                return MAX_K;
            }
            rem -= count;
        }

        return perms;
    }
}
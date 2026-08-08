class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        // suf[i] = number of characters from the end of word2
        // that can be matched exactly in word1[i...]
        int[] suf = new int[n + 1];

        int j = m - 1;

        for (int i = n - 1; i >= 0; i--) {
            suf[i] = suf[i + 1];

            if (j >= 0 && word1.charAt(i) == word2.charAt(j)) {
                suf[i]++;
                j--;
            }
        }

        int[] ans = new int[m];

        int p = 0;
        boolean usedMismatch = false;

        for (int i = 0; i < n && p < m; i++) {

            int remaining = m - p - 1;

            // Exact match
            if (word1.charAt(i) == word2.charAt(p)) {

                // We can always take an exact match.
                ans[p++] = i;
            }

            // Different character -> use our one mismatch
            else if (!usedMismatch && suf[i + 1] >= remaining) {

                ans[p++] = i;
                usedMismatch = true;
            }
        }

        if (p != m) {
            return new int[0];
        }

        return ans;
    }
}
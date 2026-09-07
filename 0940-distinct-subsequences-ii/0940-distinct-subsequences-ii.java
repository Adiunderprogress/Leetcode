class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1_000_000_007;
        long total = 0;
        long[] last = new long[26];

        for (char c : s.toCharArray()) {
            int idx = c - 'a';
            long countNew = (total + 1 - last[idx] + MOD) % MOD;
            total = (total + countNew) % MOD;
            last[idx] = (last[idx] + countNew) % MOD;
        }

        return (int) total;
    }
}
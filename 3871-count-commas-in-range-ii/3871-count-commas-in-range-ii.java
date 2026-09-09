class Solution {
    public long countCommas(long n) {
        long ans = 0;
        for (long b = 1000; b <= n; b *= 1000) ans += n - b + 1;
        return ans;
    }
}
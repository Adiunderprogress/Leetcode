class Solution {
public:
    int numberOfSets(int n, int k) {
        long long MOD = 1e9 + 7;
        int total = n + k - 1;
        int r = 2 * k;

        // DP table for computing Pascal's triangle modulo 10^9 + 7
        vector<vector<long long>> dp(total + 1, vector<long long>(r + 1, 0));

        for (int i = 0; i <= total; ++i) {
            dp[i][0] = 1;
            for (int j = 1; j <= min(i, r); ++j) {
                dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j]) % MOD;
            }
        }

        return dp[total][r];
    }
};
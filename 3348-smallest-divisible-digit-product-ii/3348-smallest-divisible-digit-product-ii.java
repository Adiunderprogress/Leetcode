import java.util.*;

class Solution {

    int A, B, C, D;
    int[] dp;

    int[][] f = {
        {0, 0, 0, 0}, // 0
        {0, 0, 0, 0}, // 1
        {1, 0, 0, 0}, // 2
        {0, 1, 0, 0}, // 3
        {2, 0, 0, 0}, // 4
        {0, 0, 1, 0}, // 5
        {1, 1, 0, 0}, // 6
        {0, 0, 0, 1}, // 7
        {3, 0, 0, 0}, // 8
        {0, 2, 0, 0}  // 9
    };

    int id(int a, int b, int c, int d) {
        return (((a * (B + 1) + b) * (C + 1) + c) * (D + 1) + d);
    }

    void buildDP() {
        int size = (A + 1) * (B + 1) * (C + 1) * (D + 1);

        dp = new int[size];
        Arrays.fill(dp, 1000000);

        dp[id(0, 0, 0, 0)] = 0;

        for (int a = 0; a <= A; a++) {
            for (int b = 0; b <= B; b++) {
                for (int c = 0; c <= C; c++) {
                    for (int d = 0; d <= D; d++) {

                        if (a == 0 && b == 0 && c == 0 && d == 0)
                            continue;

                        int best = 1000000;

                        for (int digit = 2; digit <= 9; digit++) {

                            int na = Math.max(0, a - f[digit][0]);
                            int nb = Math.max(0, b - f[digit][1]);
                            int nc = Math.max(0, c - f[digit][2]);
                            int nd = Math.max(0, d - f[digit][3]);

                            best = Math.min(
                                best,
                                1 + dp[id(na, nb, nc, nd)]
                            );
                        }

                        dp[id(a, b, c, d)] = best;
                    }
                }
            }
        }
    }

    String buildSuffix(int a, int b, int c, int d, int len) {

        StringBuilder res = new StringBuilder();

        for (int pos = 0; pos < len; pos++) {

            int remaining = len - pos - 1;

            for (int digit = 1; digit <= 9; digit++) {

                int na = Math.max(0, a - f[digit][0]);
                int nb = Math.max(0, b - f[digit][1]);
                int nc = Math.max(0, c - f[digit][2]);
                int nd = Math.max(0, d - f[digit][3]);

                if (dp[id(na, nb, nc, nd)] <= remaining) {

                    res.append((char)('0' + digit));

                    a = na;
                    b = nb;
                    c = nc;
                    d = nd;

                    break;
                }
            }
        }

        return res.toString();
    }

    public String smallestNumber(String num, long t) {

        long x = t;

        A = B = C = D = 0;

        while (x % 2 == 0) {
            A++;
            x /= 2;
        }

        while (x % 3 == 0) {
            B++;
            x /= 3;
        }

        while (x % 5 == 0) {
            C++;
            x /= 5;
        }

        while (x % 7 == 0) {
            D++;
            x /= 7;
        }

        // Prime factor > 7
        if (x != 1)
            return "-1";

        buildDP();

        int n = num.length();

        // prefix[i] = factors supplied by num[0 ... i-1]
        int[][] prefix = new int[n + 1][4];

        boolean[] hasZero = new boolean[n + 1];

        for (int i = 0; i < n; i++) {

            int digit = num.charAt(i) - '0';

            for (int k = 0; k < 4; k++)
                prefix[i + 1][k] =
                    prefix[i][k] + f[digit][k];

            hasZero[i + 1] =
                hasZero[i] || digit == 0;
        }

        // --------------------------------------------------
        // Case 1: num itself
        // --------------------------------------------------

        if (!hasZero[n]) {

            int a = Math.max(0, A - prefix[n][0]);
            int b = Math.max(0, B - prefix[n][1]);
            int c = Math.max(0, C - prefix[n][2]);
            int d = Math.max(0, D - prefix[n][3]);

            if (a == 0 && b == 0 && c == 0 && d == 0)
                return num;
        }

        // --------------------------------------------------
        // Case 2: Same length, make num just larger
        // --------------------------------------------------

        for (int i = n - 1; i >= 0; i--) {

            // Prefix must contain no zero
            if (hasZero[i])
                continue;

            int a = Math.max(0, A - prefix[i][0]);
            int b = Math.max(0, B - prefix[i][1]);
            int c = Math.max(0, C - prefix[i][2]);
            int d = Math.max(0, D - prefix[i][3]);

            int original = num.charAt(i) - '0';

            for (int digit = original + 1; digit <= 9; digit++) {

                int na = Math.max(0, a - f[digit][0]);
                int nb = Math.max(0, b - f[digit][1]);
                int nc = Math.max(0, c - f[digit][2]);
                int nd = Math.max(0, d - f[digit][3]);

                int suffixLen = n - i - 1;

                if (dp[id(na, nb, nc, nd)] <= suffixLen) {

                    StringBuilder ans = new StringBuilder();

                    ans.append(num, 0, i);
                    ans.append((char)('0' + digit));

                    ans.append(
                        buildSuffix(
                            na, nb, nc, nd,
                            suffixLen
                        )
                    );

                    return ans.toString();
                }
            }
        }

        // --------------------------------------------------
        // Case 3: Need more digits
        // --------------------------------------------------

        int minDigits = dp[id(A, B, C, D)];

        // A longer answer must have at least n + 1 digits.
        // But it may need MUCH more than n + 1.
        int len = Math.max(n + 1, minDigits);

        return buildSuffix(A, B, C, D, len);
    }
}
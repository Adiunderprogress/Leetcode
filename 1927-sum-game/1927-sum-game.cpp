class Solution {
public:
    bool sumGame(string num) {
        int n = num.length();
        int sumDiff = 0;
        int qDiff = 0;

        for (int i = 0; i < n; i++) {
            int sign = (i < n / 2) ? 1 : -1;
            if (num[i] == '?') {
                qDiff += sign;
            } else {
                sumDiff += sign * (num[i] - '0');
            }
        }

        // If total net question marks are odd, Alice can always force an unequal sum
        if ((qDiff % 2) != 0) {
            return true;
        }

        // Bob can force equality if and only if sumDiff + (qDiff / 2) * 9 == 0
        return sumDiff + (qDiff / 2) * 9 != 0;
    }
};
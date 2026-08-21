class Solution {
    public long findKthSmallest(int[] coins, int k) {
        int n = coins.length;
        int maxMask = 1 << n;
        long[] lcm = new long[maxMask];
        int[] sign = new int[maxMask];
        
        long minCoin = coins[0];
        for (int c : coins) {
            minCoin = Math.min(minCoin, c);
        }
        
        // Precompute LCMs for all possible combinations (subsets)
        for (int i = 1; i < maxMask; i++) {
            int bits = Integer.bitCount(i);
            sign[i] = (bits % 2 == 1) ? 1 : -1;
            
            int firstBit = Integer.numberOfTrailingZeros(i);
            int prevMask = i ^ (1 << firstBit);
            
            if (prevMask == 0) {
                lcm[i] = coins[firstBit];
            } else {
                if (lcm[prevMask] == -1) {
                    lcm[i] = -1;
                } else {
                    lcm[i] = getLcm(lcm[prevMask], coins[firstBit]);
                }
            }
        }
        
        // Binary search for the kth smallest amount
        long left = 1;
        long right = minCoin * k;
        long ans = right;
        
        while (left <= right) {
            long mid = left + (right - left) / 2;
            long count = 0;
            
            for (int i = 1; i < maxMask; i++) {
                if (lcm[i] != -1) {
                    count += sign[i] * (mid / lcm[i]);
                }
            }
            
            if (count >= k) {
                ans = mid;
                right = mid - 1; // Try to find a smaller valid amount
            } else {
                left = mid + 1;
            }
        }
        
        return ans;
    }
    
    private long getLcm(long a, long b) {
        long g = gcd(a, b);
        long res = a / g;
        // Cap at 5 * 10^10 to prevent long overflow, as our max possible search space is 25 * 2 * 10^9
        if (res > 50000000000L / b + 1) return -1;
        return res * b;
    }
    
    private long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
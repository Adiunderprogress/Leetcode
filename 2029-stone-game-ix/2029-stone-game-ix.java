class Solution {
    public boolean stoneGameIX(int[] stones) {
        int c0 = 0, c1 = 0, c2 = 0;
        
        for (int stone : stones) {
            int rem = stone % 3;
            if (rem == 0) c0++;
            else if (rem == 1) c1++;
            else c2++;
        }
        
        // If there are no stones with remainder 1 or remainder 2, Alice can never win.
        if (c1 == 0 && c2 == 0) return false;
        
        // If both c1 and c2 are available
        if (c1 > 0 && c2 > 0) {
            if (Math.abs(c1 - c2) > 2) return true;
            // If the difference is <= 2, Alice wins if c0 is even
            return c0 % 2 == 0;
        }
        
        // If only c1 stones are available (c2 == 0)
        if (c1 > 0) {
            return c1 > 2 && c0 % 2 == 1;
        }
        
        // If only c2 stones are available (c1 == 0)
        return c2 > 2 && c0 % 2 == 1;
    }
}
class Solution {
    public int smallestRepunitDivByK(int k) {
        // If K is even or divisible by 5, no repunit can be divisible by K
        if (k % 2 == 0 || k % 5 == 0) {
            return -1;
        }
        
        int remainder = 0;
        
        // The loop runs at most K times due to the Pigeonhole Principle
        for (int length = 1; length <= k; length++) {
            remainder = (remainder * 10 + 1) % k;
            
            // If the remainder becomes 0, we found the smallest repunit length
            if (remainder == 0) {
                return length;
            }
        }
        
        return -1;
    }
}
class Solution {
    public boolean isUgly(int n) {
        // Edge case: ugly numbers must be positive
        if (n <= 0) {
            return false;
        }
        
        // Repeatedly divide by 2, 3, and 5 as long as it's perfectly divisible
        while (n % 2 == 0) n /= 2;
        while (n % 3 == 0) n /= 3;
        while (n % 5 == 0) n /= 5;
        
        // If the remaining number is 1, it means it only had 2, 3, and 5 as prime factors
        return n == 1;
    }
}
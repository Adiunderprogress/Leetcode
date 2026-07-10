class Solution {
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        
        // Right to left (piche se aage) traverse karenge
        for (int i = n - 1; i >= 0; i--) {
            // Agar digit 9 se kam hai, toh bas +1 karo aur return kar do
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            
            // Agar digit 9 hai, toh wo 0 ban jayegi aur carry aage chali jayegi
            digits[i] = 0;
        }
        
        // Agar loop poora khatam ho gaya, iska matlab saare digits 9 the (e.g., [9, 9, 9])
        // Ab hum ek naya array banayenge jiska size n + 1 hoga
        int[] newDigits = new int[n + 1];
        
        // Sabse pehle index par 1 daal denge, baaki saare automatic 0 rahenge (e.g., [1, 0, 0, 0])
        newDigits[0] = 1;
        
        return newDigits;
    }
}
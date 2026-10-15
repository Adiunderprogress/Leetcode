class Solution {
    public String maxSumOfSquares(int num, int sum) {
        // A valid positive integer of length 'num' cannot have a leading digit of 0 
        // unless num == 1 and sum == 0 (though problem says sum is positive integers).
        // Let's build the number digit by digit greedily from left to right.
        
        StringBuilder sb = new StringBuilder();
        int remSum = sum;
        
        for (int i = 0; i < num; i++) {
            int remainingPositions = num - 1 - i;
            boolean placed = false;
            
            // Try digits from 9 down to 0 to make the number as large as possible
            for (int d = 9; d >= 0; d--) {
                // Special check: the first digit (i == 0) cannot be 0 if num > 1
                if (i == 0 && d == 0 && num > 1) {
                    continue;
                }
                
                // Check if taking digit 'd' leaves a sum that can be formed by the remaining positions
                if (remSum - d >= 0 && remSum - d <= remainingPositions * 9) {
                    sb.append(d);
                    remSum -= d;
                    placed = true;
                    break;
                }
            }
            
            if (!placed) {
                return "";
            }
        }
        
        return remSum == 0 ? sb.toString() : "";
    }
}
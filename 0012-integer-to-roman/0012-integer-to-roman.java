class Solution {
    public String intToRoman(int num) {
        // Define arrays for values and their corresponding Roman symbols in descending order
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        
        StringBuilder roman = new StringBuilder();
        
        // Loop through the values array
        for (int i = 0; i < values.length; i++) {
            // While the current value can be subtracted from num
            while (num >= values[i]) {
                roman.append(symbols[i]); // Append the symbol
                num -= values[i];         // Reduce the number
            }
        }
        
        return roman.toString();
    }
}
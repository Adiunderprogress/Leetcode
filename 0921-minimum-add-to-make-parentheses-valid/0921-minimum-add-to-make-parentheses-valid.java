class Solution {
    public int minAddToMakeValid(String s) {
        int openBrackets = 0;
        int minAddsRequired = 0;

        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                openBrackets++;
            } else {
                // If we encounter a closing bracket, check if there's an unmatched opening bracket available
                if (openBrackets > 0) {
                    openBrackets--;
                } else {
                    // No matching opening bracket, so we need to add one
                    minAddsRequired++;
                }
            }
        }

        // Add any remaining unmatched opening brackets that need a closing bracket
        return minAddsRequired + openBrackets;
    }
}
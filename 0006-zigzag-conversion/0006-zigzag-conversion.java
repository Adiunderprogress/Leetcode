class Solution {
    public String convert(String s, int numRows) {
        // Edge cases: If there's only 1 row or the string is too short to zigzag
        if (numRows == 1 || s.length() <= numRows) {
            return s;
        }

        // Initialize row containers using StringBuilder for efficiency
        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }

        int currRow = 0;
        boolean goingDown = false;

        // Simulate the zigzag placement character by character
        for (char c : s.toCharArray()) {
            rows[currRow].append(c);
            
            // Turn around if we hit the top row (0) or bottom row (numRows - 1)
            if (currRow == 0 || currRow == numRows - 1) {
                goingDown = !goingDown;
            }
            
            // Move up or down depending on the direction flag
            currRow += goingDown ? 1 : -1;
        }

        // Combine all row contents into a single final string
        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows) {
            result.append(row);
        }

        return result.toString();
    }
}
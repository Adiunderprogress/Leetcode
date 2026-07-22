class Solution {
    public int lengthOfLastWord(String s) {
        int length = 0;
        int i = s.length() - 1;

        // Step 1: End ke saare extra spaces skip karo
        while (i >= 0 && s.charAt(i) == ' ') {
            i--;
        }

        // Step 2: Last word ke characters count karo
        while (i >= 0 && s.charAt(i) != ' ') {
            length++;
            i--;
        }

        return length;
    }
}
import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int i = 0;
        int n = words.length;

        while (i < n) {
            int j = i + 1;
            int lineLength = words[i].length();

            // Dekho kitne words is line me aa sakte hain
            while (j < n && lineLength + 1 + words[j].length() <= maxWidth) {
                lineLength += 1 + words[j].length();
                j++;
            }

            int numOfWords = j - i;
            StringBuilder sb = new StringBuilder();

            // Agar ye Last Line hai YA line me sirf 1 word hai (Left-justify karein)
            if (j == n || numOfWords == 1) {
                for (int k = i; k < j; k++) {
                    sb.append(words[k]);
                    if (k < j - 1) sb.append(" ");
                }
                // Remaining spaces end me add karo
                while (sb.length() < maxWidth) {
                    sb.append(" ");
                }
            } else {
                // Fully-justify line
                int totalCharLen = 0;
                for (int k = i; k < j; k++) {
                    totalCharLen += words[k].length();
                }

                int totalSpaces = maxWidth - totalCharLen;
                int spacesBetween = totalSpaces / (numOfWords - 1);
                int extraSpaces = totalSpaces % (numOfWords - 1);

                for (int k = i; k < j; k++) {
                    sb.append(words[k]);
                    if (k < j - 1) {
                        // Spaces distribute karo
                        int spacesToApply = spacesBetween + (k - i < extraSpaces ? 1 : 0);
                        for (int s = 0; s < spacesToApply; s++) {
                            sb.append(" ");
                        }
                    }
                }
            }

            result.add(sb.toString());
            i = j; // Agli line ke liye start index
        }

        return result;
    }
}
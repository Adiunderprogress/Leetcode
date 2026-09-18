import java.util.*;

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList);
        if (!set.contains(endWord)) {
            return 0;
        }

        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        
        int level = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                String currentWord = queue.poll();
                
                if (currentWord.equals(endWord)) {
                    return level;
                }

                char[] wordChars = currentWord.toCharArray();
                for (int j = 0; j < wordChars.length; j++) {
                    char originalChar = wordChars[j];

                    for (char c = 'a'; c <= 'z'; c++) {
                        if (wordChars[j] == c) continue;

                        wordChars[j] = c;
                        String nextWord = String.valueOf(wordChars);

                        if (set.contains(nextWord)) {
                            queue.add(nextWord);
                            set.remove(nextWord); // Mark as visited
                        }
                    }

                    wordChars[j] = originalChar;
                }
            }
            level++;
        }

        return 0;
    }
}
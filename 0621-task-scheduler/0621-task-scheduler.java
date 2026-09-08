class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];
        int maxFreq = 0;
        
        // Count frequency of each task and find the max frequency
        for (char task : tasks) {
            freq[task - 'A']++;
            maxFreq = Math.max(maxFreq, freq[task - 'A']);
        }
        
        // Count how many tasks have the maximum frequency
        int maxFreqCount = 0;
        for (int count : freq) {
            if (count == maxFreq) {
                maxFreqCount++;
            }
        }
        
        // Calculate the minimum required intervals
        int ans = (maxFreq - 1) * (n + 1) + maxFreqCount;
        
        // Return the larger of calculated intervals or total tasks count
        return Math.max(ans, tasks.length);
    }
}
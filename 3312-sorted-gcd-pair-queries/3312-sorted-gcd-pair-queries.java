class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int maxVal = 0;
        for (int num : nums) maxVal = Math.max(maxVal, num);

        // 1. Har number ki frequency count karo
        int[] freq = new int[maxVal + 1];
        for (int num : nums) freq[num]++;

        // 2. Inclusion-Exclusion se exact GCD counts nikalna
        long[] exactGcdCounts = new long[maxVal + 1];
        for (int i = maxVal; i >= 1; i--) {
            long countMultiples = 0;
            for (int j = i; j <= maxVal; j += i) {
                countMultiples += freq[j];
            }
            
            long totalPairs = countMultiples * (countMultiples - 1) / 2;
            
            // Strictly bade multiples ko minus karo
            for (int j = 2 * i; j <= maxVal; j += i) {
                totalPairs -= exactGcdCounts[j];
            }
            exactGcdCounts[i] = totalPairs;
        }

        // 3. Prefix Sum array banana
        long[] prefixSums = new long[maxVal + 1];
        for (int i = 1; i <= maxVal; i++) {
            prefixSums[i] = prefixSums[i - 1] + exactGcdCounts[i];
        }

        // 4. Binary Search se queries ka answer dena
        int[] answer = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            long targetIndex = queries[i];
            int low = 1, high = maxVal, res = maxVal;
            
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (prefixSums[mid] > targetIndex) {
                    res = mid; // Candidate mil gaya, chota check karo
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            answer[i] = res;
        }
        return answer;
    }
}
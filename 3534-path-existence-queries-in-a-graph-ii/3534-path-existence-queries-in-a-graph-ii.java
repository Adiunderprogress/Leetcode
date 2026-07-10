import java.util.*;

class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        // Step 1: Sort indices based on their corresponding values in nums
        Integer[] sortedIndices = new Integer[n];
        for (int i = 0; i < n; i++) {
            sortedIndices[i] = i;
        }
        Arrays.sort(sortedIndices, (a, b) -> Integer.compare(nums[a], nums[b]));
        
        // Map original index to its position in the sorted array
        int[] posInSorted = new int[n];
        for (int i = 0; i < n; i++) {
            posInSorted[sortedIndices[i]] = i;
        }
        
        // Step 2: For each sorted position, find the furthest right index it can jump to
        // Using a two-pointer approach since nums[sortedIndices[i]] is monotonically increasing
        int[] nextGreedy = new int[n];
        int right = 0;
        for (int left = 0; left < n; left++) {
            while (right < n && nums[sortedIndices[right]] - nums[sortedIndices[left]] <= maxDiff) {
                right++;
            }
            // right - 1 is the furthest valid element to the right
            nextGreedy[left] = right - 1;
        }
        
// Step 3: Initialize Binary Lifting table
int LOG = 18; // Since 2^17 = 131,072 > 10^5
int[][] up = new int[n][LOG];

for (int i = 0; i < n; i++) {
    up[i][0] = nextGreedy[i];
}

for (int j = 1; j < LOG; j++) {
    for (int i = 0; i < n; i++) {
        // FIX: Transition from up[i][j - 1] instead of up[i][0]
        up[i][j] = up[up[i][j - 1]][j - 1]; 
    }
}
        // Step 4: Process Queries
        int numQueries = queries.length;
        int[] answer = new int[numQueries];
        
        for (int q = 0; q < numQueries; q++) {
            int u = queries[q][0];
            int v = queries[q][1];
            
            if (u == v) {
                answer[q] = 0;
                continue;
            }
            
            // Ensure we are always jumping from the smaller valued node to the larger one
            int startPos = posInSorted[u];
            int targetPos = posInSorted[v];
            if (startPos > targetPos) {
                int temp = startPos;
                startPos = targetPos;
                targetPos = temp;
            }
            
            // Count minimum jumps required to reach or cross targetPos
            int jumps = 0;
            int curr = startPos;
            
            for (int j = LOG - 1; j >= 0; j--) {
                if (up[curr][j] < targetPos) {
                    curr = up[curr][j];
                    jumps += (1 << j);
                }
            }
            
            // curr is now at the furthest position strictly less than targetPos
            // One final jump should reach or cross targetPos if a path exists
            if (nextGreedy[curr] >= targetPos) {
                answer[q] = jumps + 1;
            } else {
                answer[q] = -1; // targetPos is unreachable
            }
        }
        
        return answer;
    }
}
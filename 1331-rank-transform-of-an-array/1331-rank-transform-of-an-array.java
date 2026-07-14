import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] arrayRankTransform(int[] arr) {
        // Clone the original array to preserve the initial order
        int[] sortedArr = arr.clone();
        Arrays.sort(sortedArr);
        
        // Map to store the element to its rank mapping
        Map<Integer, Integer> rankMap = new HashMap<>();
        int rank = 1;
        
        // Assign ranks to unique elements
        for (int num : sortedArr) {
            if (!rankMap.containsKey(num)) {
                rankMap.put(num, rank);
                rank = rank + 1;
            }
        }
        
        // Update the original array with the ranks
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rankMap.get(arr[i]);
        }
        
        return arr;
    }
}
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        // Map to store value -> index
        Map<Integer, Integer> map = new HashMap<>();
        
        // Traverse the array once
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            
            // If the complement is found, return the indices
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            
            // Otherwise, add the current number and its index to the map
            map.put(nums[i], i);
        }
        
        // In case no solution is found (though the problem guarantees exactly one solution)
        return new int[] {};
    }
}
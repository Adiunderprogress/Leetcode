import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        int countLessThan = 0;
        int countEqual = 0;
        
        // Count elements smaller than and equal to the target
        for (int num : nums) {
            if (num < target) {
                countLessThan++;
            } else if (num == target) {
                countEqual++;
            }
        }
        
        // Build the result list with consecutive indices starting from countLessThan
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < countEqual; i++) {
            result.add(countLessThan + i);
        }
        
        return result;
    }
}
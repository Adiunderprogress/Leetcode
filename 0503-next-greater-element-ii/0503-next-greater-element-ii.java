import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();

        // Traverse the array twice in reverse
        for (int i = 2 * n - 1; i >= 0; i--) {
            int currentIndex = i % n;

            // Maintain a strictly decreasing stack
            while (!stack.isEmpty() && stack.peek() <= nums[currentIndex]) {
                stack.pop();
            }

            // Fill the answer only during the first pass (i < n)
            if (i < n) {
                result[i] = stack.isEmpty() ? -1 : stack.peek();
            }

            // Push current element onto the stack
            stack.push(nums[currentIndex]);
        }

        return result;
    }
}
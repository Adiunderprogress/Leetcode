import java.util.Arrays;

class Solution {
    public long gcdSum(int[] nums) {
        int n = nums.length;
        int[] prefixGcd = new int[n];
        int mx = 0;
        
        // Step 1: Construct prefixGcd array
        for (int i = 0; i < n; i++) {
            mx = Math.max(mx, nums[i]);
            prefixGcd[i] = gcd(nums[i], mx);
        }
        
        // Step 2: Sort the prefixGcd array
        Arrays.sort(prefixGcd);
        
        // Step 3: Form pairs and sum their GCDs
        long sum = 0;
        int left = 0;
        int right = n - 1;
        
        while (left < right) {
            sum += gcd(prefixGcd[left], prefixGcd[right]);
            left++;
            right--;
        }
        
        return sum;
    }
    
    // Helper method to compute Greatest Common Divisor
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
class Solution {
    public int maxArea(int[] height) {
        int maxArea = 0;
        int left = 0;
        int right = height.length - 1;
        
        while (left < right) {
            // Calculate the current width and the limiting height
            int width = right - left;
            int currentHeight = Math.min(height[left], height[right]);
            
            // Calculate current area and update maxArea
            int area = width * currentHeight;
            maxArea = Math.max(maxArea, area);
            
            // Move the pointer that points to the shorter line
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        
        return maxArea;
    }
}
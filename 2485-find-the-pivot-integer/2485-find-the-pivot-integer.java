class Solution {
    public int pivotInteger(int n) {
        // Calculate the total sum from 1 to n
        int totalSum = n * (n + 1) / 2;
        
        // Find the integer square root of the total sum
        int pivot = (int) Math.sqrt(totalSum);
        
        // If the square of the pivot equals totalSum, it's valid
        if (pivot * pivot == totalSum) {
            return pivot;
        }
        
        return -1;
    }
}
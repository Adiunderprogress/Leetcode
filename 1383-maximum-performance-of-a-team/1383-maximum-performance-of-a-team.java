import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        // Group speed and efficiency together
        int[][] engineers = new int[n][2];
        for (int i = 0; i < n; i++) {
            engineers[i][0] = efficiency[i];
            engineers[i][1] = speed[i];
        }

        // Sort engineers by efficiency in descending order
        Arrays.sort(engineers, (a, b) -> Integer.compare(b[0], a[0]));

        // Min-heap to keep track of the largest speeds seen so far
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k);
        
        long currentSpeedSum = 0;
        long maxPerformance = 0;

        for (int[] engineer : engineers) {
            int currEfficiency = engineer[0];
            int currSpeed = engineer[1];

            // Add the current engineer's speed to our team
            currentSpeedSum += currSpeed;
            minHeap.add(currSpeed);

            // If the team exceeds size k, remove the slowest engineer
            if (minHeap.size() > k) {
                currentSpeedSum -= minHeap.poll();
            }

            // Calculate performance with current efficiency as the minimum
            long currentPerformance = currentSpeedSum * currEfficiency;
            maxPerformance = Math.max(maxPerformance, currentPerformance);
        }

        // Return the maximum performance modulo 10^9 + 7
        return (int) (maxPerformance % 1_000_000_007);
    }
}
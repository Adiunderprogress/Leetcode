import java.util.*;

class Solution {
    public int minScore(int n, int[][] roads) {
        // Step 1: Build the adjacency list
        // Map each city to a list of pairs: [neighbor_city, road_distance]
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        
        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int dist = road[2];
            adj.get(u).add(new int[]{v, dist});
            adj.get(v).add(new int[]{u, dist}); // Graph is bidirectional
        }

        // Step 2: BFS to traverse the connected component starting from city 1
        int minScore = Integer.MAX_VALUE;
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(1);
        visited[1] = true;

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            // Check all roads connected to the current city
            for (int[] neighbor : adj.get(curr)) {
                int nextCity = neighbor[0];
                int weight = neighbor[1];

                // Update the minimum weight seen in this component
                minScore = Math.min(minScore, weight);

                // If the next city hasn't been visited, push it to the queue
                if (!visited[nextCity]) {
                    visited[nextCity] = true;
                    queue.offer(nextCity);
                }
            }
        }

        return minScore;
    }
}
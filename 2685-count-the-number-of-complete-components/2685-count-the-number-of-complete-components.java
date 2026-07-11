import java.util.*;

class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
        // Build the adjacency list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        int completeComponentsCount = 0;

        // Traverse all nodes to find all connected components
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                int[] counts = new int[2]; // counts[0] = vertex count, counts[1] = total edge degrees
                dfs(i, adj, visited, counts);

                int vCount = counts[0];
                int totalEdgesCount = counts[1];

                // For a complete component, total edges counted via DFS (bidirectional) 
                // must equal vCount * (vCount - 1)
                if (totalEdgesCount == vCount * (vCount - 1)) {
                    completeComponentsCount++;
                }
            }
        }

        return completeComponentsCount;
    }

    private void dfs(int node, List<List<Integer>> adj, boolean[] visited, int[] counts) {
        visited[node] = true;
        counts[0]++; // Increment vertex count
        counts[1] += adj.get(node).size(); // Add the degree of the current node

        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, adj, visited, counts);
            }
        }
    }
}
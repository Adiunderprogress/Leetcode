#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

class Solution {
public:
    int findMaxPathScore(vector<vector<int>>& edges, vector<bool>& online, long long k) {
        int n = online.size();
        
        // Step 1: Build the adjacency list
        vector<vector<pair<int, int>>> adj(n);
        vector<int> globalInDegree(n, 0);
        for (const auto& edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int cost = edge[2];
            adj[u].push_back({v, cost});
            globalInDegree[v]++;
        }
        
        // Step 2: Find the global Topological Sort order of the DAG
        queue<int> q;
        for (int i = 0; i < n; ++i) {
            if (globalInDegree[i] == 0) {
                q.push(i);
            }
        }
        
        vector<int> topoOrder;
        while (!q.empty()) {
            int u = q.front();
            q.pop();
            topoOrder.push_back(u);
            
            for (const auto& edge : adj[u]) {
                int v = edge.first;
                globalInDegree[v]--;
                if (globalInDegree[v] == 0) {
                    q.push(v);
                }
            }
        }
        
        // Find the maximum edge cost for the binary search upper bound
        int low = 0, high = 0;
        for (const auto& edge : edges) {
            high = max(high, edge[2]);
        }
        
        int ans = -1;
        
        // Step 3: Helper function to check feasibility using the precalculated topo order
        auto check = [&](int mid) -> bool {
            if (!online[0] || !online[n - 1]) return false;
            
            vector<long long> dp(n, 1e18); 
            dp[0] = 0;
            
            // Iterate through nodes in the precomputed topological order
            for (int u : topoOrder) {
                if (dp[u] == 1e18 || !online[u]) continue;
                
                for (const auto& edge : adj[u]) {
                    int v = edge.first;
                    int cost = edge.second;
                    
                    if (online[v] && cost >= mid) {
                        if (dp[u] + cost < dp[v]) {
                            dp[v] = dp[u] + cost;
                        }
                    }
                }
            }
            
            return dp[n - 1] <= k;
        };
        
        // Step 4: Binary Search on the minimum edge cost
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (check(mid)) {
                ans = mid;     
                low = mid + 1; // Try to find a larger minimum edge cost
            } else {
                high = mid - 1; 
            }
        }
        
        return ans;
    }
};
#include <vector>
#include <deque>

using namespace std;

class Solution {
public:
    bool findSafeWalk(vector<vector<int>>& grid, int health) {
        int m = grid.size();
        int n = grid[0].size();
        
        // Fixed: Inner vector type corrected to vector<int> and size type matched
        vector<vector<int>> dist(m, vector<int>(n, 1e9));
        deque<pair<int, int>> dq;
        
        // Initialize the starting cell
        dist[0][0] = grid[0][0];
        dq.push_front({0, 0});
        
        // ... rest of your code remains the same ...
        
        // Direction vectors for moving up, down, left, right
        int dr[] = {-1, 1, 0, 0};
        int dc[] = {0, 0, -1, 1};
        
        while (!dq.empty()) {
            auto [r, c] = dq.front();
            dq.pop_front();
            
            // If we reached the destination, we can stop early
            if (r == m - 1 && c == n - 1) break;
            
            for (int i = 0; i < 4; ++i) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                
                // Check boundaries
                if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                    int weight = grid[nr][nc];
                    
                    // If a strictly better path to (nr, nc) is found
                    if (dist[r][c] + weight < dist[nr][nc]) {
                        dist[nr][nc] = dist[r][c] + weight;
                        
                        // 0-1 BFS optimization
                        if (weight == 0) {
                            dq.push_front({nr, nc});
                        } else {
                            dq.push_back({nr, nc});
                        }
                    }
                }
            }
        }
        
        // Total health remaining = initial health - minimum health lost
        int min_loss = dist[m - 1][n - 1];
        return (health - min_loss) >= 1;
    }
};
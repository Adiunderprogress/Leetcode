import heapq
from typing import List

class Solution:
    def shortestPath(self, n: int, edges: List[List[int]], labels: str, k: int) -> int:
        # Build adjacency list: u -> list of (v, w)
        adj = [[] for _ in range(n)]
        for u, v, w in edges:
            adj[u].append((v, w))
            
        # Min-heap priority queue storing (distance, node, consecutive_count)
        # Start at node 0 with weight 0 and initial count 1
        pq = [(0, 0, 1)]
        
        # Distance dictionary to track the minimum weight to reach (node, count)
        # Using a dict to handle sparse states efficiently
        min_dist = {}
        min_dist[(0, 1)] = 0
        
        while pq:
            dist, u, count = heapq.heappop(pq)
            
            # If we reached the target node, return the distance
            if u == n - 1:
                return dist
                
            # If a shorter path to this state has already been processed, skip
            if dist > min_dist.get((u, count), float('inf')):
                continue
                
            for v, w in adj[u]:
                # Determine the new consecutive count for node v
                if labels[u] == labels[v]:
                    next_count = count + 1
                else:
                    next_count = 1
                    
                # If the count exceeds k, this path is invalid
                if next_count > k:
                    continue
                    
                next_dist = dist + w
                
                # If we found a strictly shorter path to state (v, next_count), update and push
                if next_dist < min_dist.get((v, next_count), float('inf')):
                    min_dist[(v, next_count)] = next_dist
                    heapq.heappush(pq, (next_dist, v, next_count))
                    
        return -1
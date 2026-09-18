import java.util.*;

class Solution {
    // Map to store mapping from original node to cloned node
    private Map<Node, Node> visited = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }

        // If the node was already cloned, return the cloned instance
        if (visited.containsKey(node)) {
            return visited.get(node);
        }

        // Create a new clone for the current node
        Node cloneNode = new Node(node.val, new ArrayList<>());
        visited.put(node, cloneNode);

        // Recursively clone all neighbors
        for (Node neighbor : node.neighbors) {
            cloneNode.neighbors.add(cloneGraph(neighbor));
        }

        return cloneNode;
    }
}
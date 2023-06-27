class Solution {
    public Node cloneGraph(Node node) {
        if (node == null) return node;
        return clone(node, new HashMap<>());
    }


    public Node clone(Node original, Map<Node, Node> visited) {
        if (visited.containsKey(original)) return visited.get(original);
        Node clone = new Node(original.val);
        visited.put(original, clone);
        List<Node> neighborClones = new ArrayList<>();
        for (Node neighbor : original.neighbors) {
            Node neighborClone = clone(neighbor, visited);
            neighborClones.add(neighborClone);
        }
        clone.neighbors = neighborClones;
        return clone;
    }
}

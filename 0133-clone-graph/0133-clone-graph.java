class Solution {
    public Node cloneGraph(Node node) {
        if (node == null) return null;
        return cloneGraph(node, new HashMap<>());
    }


    public Node cloneGraph(Node orignalNode, Map<Node, Node> clonedNodes) {
        if (clonedNodes.containsKey(orignalNode)) return clonedNodes.get(orignalNode);
        Node clonedNode = new Node(orignalNode.val);
        clonedNodes.put(orignalNode, clonedNode);
        List<Node> clonedNeighbours = new ArrayList<>();
        for (Node originalNeighbour : orignalNode.neighbors) {
            Node clonedNeighbour = cloneGraph(originalNeighbour, clonedNodes);
            clonedNeighbours.add(clonedNeighbour);
        }
        clonedNode.neighbors = clonedNeighbours;
        return clonedNode;
    }
}



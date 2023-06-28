// DFS
class SolutionV1 {
    public int countComponents(int n, int[][] edges) {
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for (int[] edge : edges) {
            adjList.computeIfAbsent(edge[0], key -> new ArrayList<>()).add(edge[1]);
            adjList.computeIfAbsent(edge[1], key -> new ArrayList<>()).add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        int count = 0;
        for (int i = 0; i < n; i++)
            count += dfs(i, adjList, visited);
        return count;
    }

    private int dfs(int i, Map<Integer, List<Integer>> adjList, boolean[] visited) {
        if (visited[i]) return 0;
        visited[i] = true;
        for (int neighbor : adjList.getOrDefault(i, Collections.emptyList()))
            dfs(neighbor, adjList, visited);
        return 1;
    }
}

// ----------------------------------------------------------------------------------
// Union Find
class Solution {
    public int countComponents(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
        for (int[] edge : edges) {
            if (uf.union(edge[0], edge[1])) n--;
        }
        return n;
    }
}


class UnionFind {
    private final int[] parent;
    private final int[] rank;

    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
    }

    private int find(int node) {
        if (parent[node] != node) parent[node] = find(parent[node]);
        return parent[node];
    }

    public boolean union(int node1, int node2) {
        int p1 = find(node1);
        int p2 = find(node2);
        if (p1 == p2) return false;

        if (rank[p1] > rank[p2]) parent[p2] = p1;
        else if (rank[p2] > rank[p1]) parent[p1] = p2;
        else {
            parent[p2] = p1;
            rank[p1]++;
        }
        return true;
    }
}
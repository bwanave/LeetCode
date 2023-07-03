class Solution {
    public boolean validTree(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
        for (int[] edge : edges) {
            if (!uf.union(edge[0], edge[1])) return false;
            n--;
        }
        return n == 1;
    }
}

class UnionFind {
    private int[] parent;
    private int[] rank;

    UnionFind(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
        rank = new int[n];
    }

    private int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    public boolean union(int x, int y) {
        int p1 = find(x);
        int p2 = find(y);
        if (p1 == p2) return false;

        if (rank[p1] > rank[p2]) 
            parent[p2] = p1;
        else if (rank[p1] < rank[p2]) 
            parent[p1] = p2;
        else {
            parent[p2] = p1;
            rank[p1] += rank[p2] + 1;
        }
        return true;
    }
}
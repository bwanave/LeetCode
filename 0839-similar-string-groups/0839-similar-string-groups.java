class Solution {
    public int numSimilarGroups(String[] strs) {
        int groups = strs.length;
        UnionFind uf = new UnionFind(strs.length);
        for (int i = 0; i < strs.length; i++) {
            for (int j = i + 1; j < strs.length; j++) {
                if (isSimilar(strs[i], strs[j]) && uf.union(i, j)) 
                    groups--;
            }
        }
        return groups;
    }
    
    private boolean isSimilar(String s1, String s2) {
        int mismatch = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) mismatch++;
            if (mismatch > 2) return false;
        }
        return true;
    }
}

class UnionFind {
    private int[] parent;
    private int[] rank;
    
    public UnionFind(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
        rank = new int[n];
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
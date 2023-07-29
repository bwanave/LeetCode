class Solution {
    public int numSimilarGroups(String[] strs) {
        int n = strs.length;
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < strs.length; i++) {
            for (int j = i + 1; j < strs.length; j++) {
                if (hasEdge(strs[i], strs[j]) && uf.union(i, j))
                    n--;
            }
        }
        return n;
    }

    private boolean hasEdge(String str1, String str2) {
        int mismatch = 0;
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) mismatch++;
            if (mismatch > 2) return false;
        }
        return true;
    }
}

class UnionFind {
    private final int[] parent;
    private final int[] rank;

    UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
    }

    private int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    public boolean union(int x, int y) {
        int p1 = find(x);
        int p2 = find(y);
        if (p1 == p2) return false;

        if (rank[p1] > rank[p2]) parent[p2] = p1;
        else if (rank[p1] < rank[p2]) parent[p1] = p2;
        else {
            parent[p2] = p1;
            rank[p1] += rank[p2] + 1;
        }
        return true;
    }
}
class Solution {
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
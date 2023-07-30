class Solution {
    public int maximumDetonation(int[][] bombs) {
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for (int i = 0; i < bombs.length; i++) {
            for (int j = i + 1; j < bombs.length; j++) {
                if (isReachable(i, j, bombs)) adjList.computeIfAbsent(i, key -> new ArrayList<>()).add(j);
                if (isReachable(j, i, bombs)) adjList.computeIfAbsent(j, key -> new ArrayList<>()).add(i);
            }
        }
        
        int max = 0;
        for (int i = 0; i < bombs.length; i++) {
            int count = dfs(i, adjList, new boolean[bombs.length]);
            max = Math.max(max, count);
        }
        return max;
    }
    
    private int dfs(int i, Map<Integer, List<Integer>> adjList, boolean[] visited) {
        if (visited[i]) return 0;
        visited[i] = true;
        int count = 1;
        for (int neighbor : adjList.getOrDefault(i, Collections.emptyList()))
            count += dfs(neighbor, adjList, visited);
        return count;
    }
    
    private boolean isReachable(int i, int j, int[][] bombs) {
        long r = bombs[i][2];
        long x1 = bombs[i][0], y1 = bombs[i][1], x2 = bombs[j][0], y2 = bombs[j][1];
        long euclideanDistance = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
        return r * r >= euclideanDistance;
    }
}